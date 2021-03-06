(ns app.handler.launch-request
  (:require [app.handler-input :as handler-input]
            [app.question-master :as question-master]
            [app.session-attributes :as session-attributes]))


(defonce welcome-message "音程クイズへようこそ。これから２つの音を再生するので、その音程を答えてください。")
(defonce reprompt-message "音程を「メジャーセカンド」や「長３度」のように答えてください。")


(defn- can-handle? [handler-input]
  (= "LaunchRequest"
     (handler-input/request-type handler-input)))

(defn- handle [handler-input]
  (let [base-note          (question-master/choose-base-note!)
        interval           (question-master/choose-interval!)
        speech-message     (str welcome-message
                                (question-master/gen-question-message {:base-note base-note :interval interval}))
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
