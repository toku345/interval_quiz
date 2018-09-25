(ns app.core
  (:require [app.interval-ja :refer [synonyms-parts
                                     parts->interval-ja-list
                                     gen-list-of-interval-values]]
            [cljs-node-io.core :as io :refer [spit]]))

(def ^:private list-of-interval-values-file-path
  "./interval_values.json")


(defn output-list-of-interval-values []
  (let [interval-ja-list               (parts->interval-ja-list synonyms-parts)
        list-of-interval-values        (gen-list-of-interval-values interval-ja-list)
        js-list-of-interval-values-str (.stringify js/JSON (clj->js list-of-interval-values))]
    (spit list-of-interval-values-file-path js-list-of-interval-values-str)))


(defn main [& cli-args]
  (prn "app running.")
  (output-list-of-interval-values))
