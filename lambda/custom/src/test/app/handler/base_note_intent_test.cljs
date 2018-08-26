(ns app.handler.base-note-intent-test
  (:require [cljs.test :refer [deftest is testing]]
            [app.handler.base-note-intent :as base-note-intent]))

(deftest get-base-note-ja-test
  (let [handler-input (clj->js {:requestEnvelope {:request {:intent {:slots {:Note {:value "base note"}}}}}})]
    (is (= "base note" (base-note-intent/get-base-note-ja handler-input)))))

(deftest set-base-note-key-on-attributes!-test
  (let [base-note "base-note"
        attributes #js {}
        result-attributes (base-note-intent/set-base-note-key-on-attributes! base-note attributes)]
    (is (= {"baseNoteKey" base-note} (js->clj attributes)))))

(deftest note-ja->note-key-test
  (is (= "c" (base-note-intent/note-ja->note-key "ド"))))
