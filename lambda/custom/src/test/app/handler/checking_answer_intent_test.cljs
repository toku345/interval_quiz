(ns app.handler.checking-answer-intent-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.handler.checking-answer-intent :as checking-answer-intent]))

(deftest correct-answer?-test
  (let [user-interval-ja    "メジャーセカンド"
        answer-interval-key "M2"]
    (is (= true (checking-answer-intent/correct-answer? user-interval-ja answer-interval-key))))
  (let [user-interval-ja    "メジャーセカンド"
        answer-interval-key "M3"]
    (is (= false (checking-answer-intent/correct-answer? user-interval-ja answer-interval-key)))))

(deftest get-interval-ja-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:intent {:slots {:Interval {:value "メジャーセカンド"}}}}}})]
    (is (= "メジャーセカンド" (checking-answer-intent/get-interval-ja handler-input)))))
