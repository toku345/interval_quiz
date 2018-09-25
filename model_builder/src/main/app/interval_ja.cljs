(ns app.interval-ja)

(defonce synonyms-parts
  {:M2
   {:prefixs ["長" "ちょう"]
    :numbers ["二" "に" "2" "２"]
    :degrees ["度" "ど"]}
   :M3
   {:prefixs ["長" "ちょう"]
    :numbers ["三" "さん" "3" "３"]
    :degrees ["度" "ど"]}
   :P4
   {:prefixs ["完全" "かんぜん"]
    :numbers ["四" "よん" "4" "４"]
    :degrees ["度" "ど"]}
   :P5
   {:prefixs ["完全" "かんぜん"]
    :numbers ["五" "ご" "5" "５"]
    :degrees ["度" "ど"]}
   :M6
   {:prefixs ["長" "ちょう"]
    :numbers ["六" "ろく" "6" "６"]
    :degrees ["度" "ど"]}
   :M7
   {:prefixs ["長" "ちょう"]
    :numbers ["七" "しち" "7" "７"]
    :degrees ["度" "ど"]}})


(defn- gen-interval-ja-list [{:keys [prefixs numbers degrees]}]
  (for [prefix prefixs
        number numbers
        degree degrees]
    (str prefix number degree)))

(defn- gen-name-value-map [interval-ja]
  {:name {:value interval-ja}})


(defn parts->interval-ja-list [parts-map]
  (flatten
   (map (fn [[_interval parts]]
          (gen-interval-ja-list parts))
        parts-map)))

(defn gen-list-of-interval-values [interval-ja-list]
  {:values
   (map #'gen-name-value-map interval-ja-list)})
