(ns app.handler.no-and-cancel-and-stop-intent-test
  (:require [cljs.test :refer [deftest is]]
            [app.handler.no-and-cancel-and-stop-intent :as no-and-cancel-and-stop-intent]))

(deftest can-handle?-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "AMAZON.NoIntent"}}}})]
    (is (= true (no-and-cancel-and-stop-intent/can-handle? handler-input))))

  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "AMAZON.CancelIntent"}}}})]
    (is (= true (no-and-cancel-and-stop-intent/can-handle? handler-input))))

  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "AMAZON.StopIntent"}}}})]
    (is (= true (no-and-cancel-and-stop-intent/can-handle? handler-input)))))
