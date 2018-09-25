(ns app.handler.help-intent
  (:require [app.handler-input :as handler-input]))


(defonce help-message (str "音程クイズは音程トレーニング・スキルです。"
                           "これから２つの音を再生するので、その音程を答えてください。"
                           "出題してもよいでしょうか？"))


(defn- can-handle? [handler-input]
  (and (= (handler-input/request-type handler-input)
          "IntentRequest")
       (= (handler-input/intent-name handler-input)
          "AMAZON.HelpIntent")))


(defn- handle [handler-input]
  (-> handler-input
      .-responseBuilder
      (.speak help-message)
      (.reprompt help-message)
      .getResponse))


(def handler
  #js {:canHandle can-handle?
       :handle    handle})
