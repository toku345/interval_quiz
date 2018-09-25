(ns app.interval-ja-test
  (:require [cljs.test :refer [deftest is]]
            [app.interval-ja :as interval-ja]))

(deftest gen-interval-ja-list-test
  (let [prefixs ["長" "ちょう"]
        numbers ["三" "さん" "3" "３"]
        degrees ["度" "ど"]]
    (is (= (interval-ja/gen-interval-ja-list {:prefixs prefixs :numbers numbers :degrees degrees})
           '("長三度" "長三ど" "長さん度" "長さんど" "長3度" "長3ど" "長３度" "長３ど"
             "ちょう三度" "ちょう三ど" "ちょうさん度" "ちょうさんど" "ちょう3度" "ちょう3ど" "ちょう３度" "ちょう３ど"))))

  (let [prefixs (-> interval-ja/synonyms-parts :M2 :prefixs)
        numbers (-> interval-ja/synonyms-parts :M2 :numbers)
        degrees (-> interval-ja/synonyms-parts :M2 :degrees)]
    (is (= (interval-ja/gen-interval-ja-list {:prefixs prefixs :numbers numbers :degrees degrees})
           '("長二度" "長二ど" "長に度" "長にど" "長2度" "長2ど" "長２度" "長２ど"
             "ちょう二度" "ちょう二ど" "ちょうに度" "ちょうにど" "ちょう2度" "ちょう2ど" "ちょう２度" "ちょう２ど")))))


(deftest parts->interval-ja-list-test
  (let [synonyms-parts {:M2
                        {:prefixs ["長" "ちょう"]
                         :numbers ["二"]
                         :degrees ["度"]}
                        :M3
                        {:prefixs ["長" "ちょう"]
                         :numbers ["三"]
                         :degrees ["度"]}}]
    (is (= (interval-ja/parts->interval-ja-list synonyms-parts)
           '("長二度" "ちょう二度" "長三度" "ちょう三度")))))


(deftest gen-name-value-map-test
  (is (= (interval-ja/gen-name-value-map "メジャーセカンド")
         {:name
          {:value "メジャーセカンド"}})))

(deftest gen-list-of-interval-values-test
  (is (= (interval-ja/gen-list-of-interval-values ["メジャーセカンド" "長二度" "長二ど"])
         {:values
          [{:name
            {:value "メジャーセカンド"}}
           {:name
            {:value "長二度"}}
           {:name
            {:value "長二ど"}}]})))
