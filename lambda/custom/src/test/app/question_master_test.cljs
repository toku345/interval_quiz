(ns app.question-master-test
  (:require [cljs.test :refer [deftest is are testing]]
            [app.question-master :as question-master]))

(deftest gen-audio-tag-test
  (is (= (question-master/gen-audio-tag {:base-note "d" :interval "M3"})
         "<audio src=\"https://s3-ap-northeast-1.amazonaws.com/toku345-interval-master/d_M3.mp3\" />")))

(deftest gen-question-message-test
  (is (= (question-master/gen-question-message {})
         (str "問題。"
              "<audio src=\"https://s3-ap-northeast-1.amazonaws.com/toku345-interval-master/c_M2.mp3\" />"
              "音程は何でしょう？")))

  (is (= (question-master/gen-question-message {:base-note "d" :interval "M3"})
         (str "問題。"
              "<audio src=\"https://s3-ap-northeast-1.amazonaws.com/toku345-interval-master/d_M3.mp3\" />"
              "音程は何でしょう？"))))

(deftest same-interval?-test
  (testing "M2"
    (let [interval-key "M2"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "メジャーセカンド"
        "長二度" "長二ど"
        "長2度" "長2ど"
        "長２度" "長２ど"
        "長に度" "長にど"
        "ちょう二度" "ちょう二ど"
        "ちょう2度" "ちょう2ど"
        "ちょう２度" "ちょう２ど"
        "ちょうに度" "ちょうにど")))
  (testing "M3"
    (let [interval-key "M3"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "メジャーサード"
        "長三度" "長三ど"
        "長3度" "長3ど"
        "長３度" "長３ど"
        "長さん度" "長さんど"
        "ちょう三度" "ちょう三ど"
        "ちょう3度" "ちょう3ど"
        "ちょう３度" "ちょう３ど"
        "ちょうさん度" "ちょうさんど")))
  (testing "P4"
    (let [interval-key "P4"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "パーフェクトフォース"
        "完全四度" "完全四ど"
        "完全4度" "完全4ど"
        "完全４度" "完全４ど"
        "完全よん度" "完全よんど"
        "完全し度" "完全しど"
        "かんぜん四度" "かんぜん四ど"
        "かんぜん4度" "かんぜん4ど"
        "かんぜん４度" "かんぜん４ど"
        "かんぜんよん度" "かんぜんよんど"
        "かんぜんし度" "かんぜんしど")))
  (testing "P5"
    (let [interval-key "P5"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "パーフェクトフィフス"
        "完全五度" "完全五ど"
        "完全5度" "完全5ど"
        "完全５度" "完全５ど"
        "完全ご度" "完全ごど"
        "かんぜん五度" "かんぜん五ど"
        "かんぜん5度" "かんぜん5ど"
        "かんぜん５度" "かんぜん５ど"
        "かんぜんご度" "かんぜんごど")))
  (testing "M6"
    (let [interval-key "M6"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "メジャーシックス"
        "長六度" "長六ど"
        "長6度" "長6ど"
        "長６度" "長６ど"
        "長ろく度" "長ろくど"
        "ちょう六度" "ちょう六ど"
        "ちょう6度" "ちょう6ど"
        "ちょう６度" "ちょう６ど"
        "ちょうろく度" "ちょうろくど")))
  (testing "M7"
    (let [interval-key "M7"]
      (are [interval-ja] (= (question-master/same-interval? interval-key interval-ja)
                            true)
        "メジャーセブンス"
        "長七度" "長七ど"
        "長7度" "長7ど"
        "長７度" "長７ど"
        "長しち度" "長しちど"
        "長なな度" "長ななど"
        "ちょう七度" "ちょう七ど"
        "ちょう7度" "ちょう7ど"
        "ちょう７度" "ちょう７ど"
        "ちょうしち度" "ちょうしちど"
        "ちょうなな度" "ちょうななど"))))
