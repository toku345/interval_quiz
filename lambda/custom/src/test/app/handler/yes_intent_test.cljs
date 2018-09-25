(ns app.handler.yes-intent-test
  (:require [cljs.test :refer [deftest is]]
            [app.handler.yes-intent :as yes-intent]))

(deftest can-handle?-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "AMAZON.YesIntent"}}}})]
    (is (= true (yes-intent/can-handle? handler-input)))))
