(ns app.handler.yes-intent
  (:require [app.handler-input :as handler-input]
            [app.question-master :as question-master]
            [app.session-attributes :as session-attributes]))


(defonce reprompt-message "音程を「メジャーセカンド」や「長３度」のように答えてください。")


(defn- can-handle? [handler-input]
  (and (= (handler-input/request-type handler-input)
          "IntentRequest")
       (= (handler-input/intent-name handler-input)
          "AMAZON.YesIntent")))

(defn- handle [handler-input]
  (let [base-note          (question-master/choose-base-note!)
        interval           (question-master/choose-interval!)
        speech-message     (question-master/gen-question-message {:base-note base-note :interval interval})
        session-attributes (session-attributes/get-session-attributes handler-input)]
    (session-attributes/set-base-note! base-note session-attributes)
    (session-attributes/set-interval! interval session-attributes)
    (-> handler-input
        .-responseBuilder
        (.speak speech-message)
        (.reprompt reprompt-message)
        .getResponse)))


(def handler
  #js {:canHandle can-handle?
       :handle    handle})
