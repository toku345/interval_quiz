(ns app.core-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.core :refer []]
            [app.handler.launch-request :as launch-request]
            [app.handler.base-note-intent :as base-note-intent]))

(deftest launch-request-handler-canHandle-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "LaunchRequest"}}})]
    (is (= true (.canHandle launch-request/handler handler-input))))
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"}}})]
    (is (= false (.canHandle launch-request/handler handler-input)))))

(deftest base-note-intent-canHandle-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "BaseNoteIntent"}}}})]
    (is (= true (.canHandle base-note-intent/handler handler-input))))
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "LaunchRequest"
                                                            :intent {:name "BaseNoteIntent"}}}})]
    (is (= false (.canHandle base-note-intent/handler handler-input)))))
