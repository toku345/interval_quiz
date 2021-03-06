(ns app.core-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.core :refer []]
            [app.handler.launch-request :as launch-request]
            [app.handler.checking-answer-intent :as checking-answer-intent]))

(deftest launch-request-handler-canHandle-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "LaunchRequest"}}})]
    (is (= true (.canHandle launch-request/handler handler-input))))
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"}}})]
    (is (= false (.canHandle launch-request/handler handler-input)))))


(deftest checking-answer-intent-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "CheckingAnswerIntent"}}}})]
    (is (= true (.canHandle checking-answer-intent/handler handler-input)))))
