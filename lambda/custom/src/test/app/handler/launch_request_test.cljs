(ns app.handler.launch-request-test
  (:require [cljs.test :refer [deftest is]]
            [app.handler.launch-request :as launch-request]))

(deftest can-handle?-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "LaunchRequest"}}})]
    (is (= true (launch-request/can-handle? handler-input)))))
