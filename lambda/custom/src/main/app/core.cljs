(ns app.core
  (:require ["ask-sdk-core" :as alexa]
            [app.handler.launch-request :as launch-request]
            [app.handler.base-note-intent :as base-note-intent]
            [app.handler.checking-answer-intent :as checking-answer-intent]))

(def HelpIntentHandler
  #js {:canHandle
       (fn [handler-input]
         (and (= (-> handler-input
                     .-requestEnvelope
                     .-request
                     .-type)
                 "IntentRequest")
              (= (-> handler-input
                     .-requestEnvelope
                     .-request
                     .-intent
                     .-name)
                 "AMAZON.HelpIntent")))
       :handle
       (fn [handler-input]
         (let [speech-text (str "音程クイズは音程トレーニングのためのスキルです。"
                                "私が問題を出すので、再生された音程を答えてください。"
                                "どの音から始めますか？")]
           (-> handler-input
             .-responseBuilder
             (.speak speech-text)
             (.reprompt speech-text)
             .getResponse)))})

(def CancelAndStopIntentHandler
  #js {:canHandle
       (fn [handler-input]
         (and (= (-> handler-input
                     .-requestEnvelope
                     .-request
                     .-type)
                 "IntentRequest")
              (or (= (-> handler-input
                         .-requestEnvelope
                         .-request
                         .-intent
                         .-name)
                     "AMAZON.CancelIntent")
                  (= (-> handler-input
                         .-requestEnvelope
                         .-request
                         .-intent
                         .-name)
                     "AMAZON.StopIntent"))))
       :handle
       (fn [handler-input]
         (let [speech-text "また挑戦してくださいね！"]
           (-> handler-input
             .-responseBuilder
             (.speak speech-text)
             (.reprompt speech-text)
             (.withSimpleCard "Hello World" speech-text)
             .getResponse)))})

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
                           base-note-intent/handler
                           checking-answer-intent/handler
                           HelpIntentHandler
                           CancelAndStopIntentHandler
                           SessionEndedRequestHandler)
      (.addErrorHandlers ErrorHandler)
      .lambda))
