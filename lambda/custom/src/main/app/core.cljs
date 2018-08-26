(ns app.core
  (:require ["ask-sdk-core" :as alexa]
            [app.handler.launch-request :as launch-request]))

(def ChaossuIntentHandler
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
                 "HelloWorldIntent")))
       :handle
       (fn [handler-input]
         (let [speech-text "チャオっす！"]
           (-> handler-input
             .-responseBuilder
             (.speak speech-text)
             (.reprompt speech-text)
             (.withSimpleCard "Hello World" speech-text)
             .getResponse)))})

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
         (let [speech-text "You can say hello to me!"]
           (-> handler-input
             .-responseBuilder
             (.speak speech-text)
             (.reprompt speech-text)
             (.withSimpleCard "Hello World" speech-text)
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
         (let [speech-text "Goodbye!"]
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
         (-> handler-input
             .-responseBuilder
             (.speak "Sorry, I can't understand the command. Please say again.")
             (.reprompt "Sorry, I can't understand the command. Please say again.")
             .getResponse))})



(defonce skill-builder
  (-> alexa
      .-SkillBuilders
      .custom))

(def ^:export handler
  (-> skill-builder
      (.addRequestHandlers launch-request/handler
                           ChaossuIntentHandler
                           HelpIntentHandler
                           CancelAndStopIntentHandler
                           SessionEndedRequestHandler)
      (.addErrorHandlers ErrorHandler)
      .lambda))
