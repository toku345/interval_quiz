(ns app.handler.help-intent-test
  (:require [cljs.test :refer [deftest is]]
            [app.handler.help-intent :as help-intent]))

(deftest can-handle?-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:type "IntentRequest"
                                                            :intent {:name "AMAZON.HelpIntent"}}}})]
    (is (= true (help-intent/can-handle? handler-input)))))
