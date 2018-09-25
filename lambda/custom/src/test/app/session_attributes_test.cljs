(ns app.session-attributes-test
  (:require [cljs.test :refer [deftest is]]
            [app.session-attributes :as session-attributes]))

(defonce handler-input-mock
  (clj->js {:attributesManager
            {:getSessionAttributes
             (fn [] #js {})}}))

(deftest get-session-attributes-test
  (is (js->clj (session-attributes/get-session-attributes handler-input-mock))
      {}))

(deftest set-base-note!-test
  (let [session-attributes #js {}]
    (session-attributes/set-base-note! "c" session-attributes)
    (is (= (.-baseNote session-attributes)
           "c"))))

(deftest set-interval!-test
  (let [session-attributes #js {}]
    (session-attributes/set-interval! "M2" session-attributes)
    (is (= (.-interval session-attributes)
           "M2"))))
