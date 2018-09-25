(ns app.handler.no-and-cancel-and-stop-intent
  (:require [app.handler-input :as handler-input]))


(defonce speech-message "また挑戦してくださいね！")

(defonce intents #{"AMAZON.NoIntent" "AMAZON.CancelIntent" "AMAZON.StopIntent"})


(defn- can-handle? [handler-input]
  (let [request-type (handler-input/request-type handler-input)
        intent-name  (handler-input/intent-name handler-input)]
    (and (= request-type
            "IntentRequest")
         (contains? intents intent-name))))


(defn- handle [handler-input]
  (-> handler-input
      .-responseBuilder
      (.speak speech-message)
      .getResponse))


(def handler
  #js {:canHandle can-handle?
       :handle    handle})
