(ns app.handler-input-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.handler-input :refer [gen-handler-input request-type intent-name]]))

(deftest gen-handler-input-test
  (is (= (js->clj (gen-handler-input "LaunchRequest") :keywordize-keys true)
         {:requestEnvelope {:request {:type "LaunchRequest"}}})))

(deftest request-type-test
  (is (= (request-type (clj->js {:requestEnvelope {:request {:type "LaunchRequest"}}}))
         "LaunchRequest")))

(deftest intent-name-test
  (is (= (intent-name (clj->js {:requestEnvelope {:request {:intent {:name "CheckingAnswerIntent"}}}}))
         "CheckingAnswerIntent")))
