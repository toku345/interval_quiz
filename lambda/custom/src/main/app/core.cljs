(ns app.core
  (:require ["ask-sdk-core" :as alexa]
            [app.handler.launch-request :as launch-request]
            [app.handler.checking-answer-intent :as checking-answer-intent]
            [app.handler.help-intent :as help-intent]
            [app.handler.yes-intent :as yes-intent]
            [app.handler.no-and-cancel-and-stop-intent :as no-and-cancel-and-stop-intent]))


(def SessionEndedRequestHandler
  #js {:canHandle
       (fn [handler-input]
         (= (-> handler-input
                .-requestEnvelope
                .-request
                .-type)
            "SessionEndedRequest"))
       :handle
       (fn [handler-input]
         (println "Session ended with reason: "
                 (-> handler-input
                     .-requestEnvelope
                     .-request
                     .-reason))
         (-> handler-input
             .-responseBuilder
             .getResponse))})

(def ErrorHandler
  #js {:canHandle
       (fn []
         true)

       :handle
       (fn [handler-input error]
         (println "Error handeled: " (.-message error))
         (let [speech-text "ごめんなさい。聞き取れませんでした。もう一度おっしゃってください。"]
             (-> handler-input
              .-responseBuilder
              (.speak speech-text)
              (.reprompt speech-text)
              .getResponse)))})



(defonce skill-builder
  (-> alexa
      .-SkillBuilders
      .custom))

(def ^:export handler
  (-> skill-builder
      (.addRequestHandlers launch-request/handler
                           checking-answer-intent/handler
                           help-intent/handler
                           yes-intent/handler
                           no-and-cancel-and-stop-intent/handler
                           SessionEndedRequestHandler)
      (.addErrorHandlers ErrorHandler)
      .lambda))
