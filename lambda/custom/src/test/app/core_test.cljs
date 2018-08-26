(ns app.core-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.core :refer []]
            [app.handler.launch-request :as launch-request]))

(deftest launch-request-handler-canHandle-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "LaunchRequest"}}})]
    (is (= true (.canHandle launch-request/handler handler-input))))
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"}}})]
    (is (= false (.canHandle launch-request/handler handler-input)))))
