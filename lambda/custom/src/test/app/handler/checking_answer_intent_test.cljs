(ns app.handler.checking-answer-intent-test
  (:require [cljs.test :refer [deftest is are testing]]
            [app.handler.checking-answer-intent :refer [get-interval-ja]]))

(deftest get-interval-ja-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:intent {:slots {:Interval {:value "メジャーセカンド"}}}}}})]
    (is (= "メジャーセカンド" (get-interval-ja handler-input)))))
