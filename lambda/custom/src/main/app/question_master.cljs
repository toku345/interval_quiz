(ns app.question-master)

(def intervals
  ["M2" "M3" "P4" "P5" "M6" "M7"])

(def intervals-ja->key-check-regexps
  {"M2" #"(メジャーセカンド|(ちょう|長)(二|2|２|に)(度|ど))"
   "M3" #"(メジャーサード|(ちょう|長)(三|3|３|さん)(度|ど))"
   "P4" #"(パーフェクトフォース|(かんぜん|完全)(四|4|４|よん|し)(度|ど))"
   "P5" #"(パーフェクトフィフス|(かんぜん|完全)(五|5|５|ご)(度|ど))"
   "M6" #"(メジャーシックス|(ちょう|長)(六|6|６|ろく)(度|ど))"
   "M7" #"(メジャーセブンス|(ちょう|長)(七|7|７|しち|なな)(度|ど))"})

(defn choose-base-note! []
  "c")

(defn choose-interval! []
  (rand-nth intervals))

(defn gen-audio-tag [{base-note :base-note interval :interval}]
  (str "<audio src=\"https://s3-ap-northeast-1.amazonaws.com/toku345-interval-master/"
       base-note "_" interval ".mp3\" />"))

(defn gen-question-message [{base-note :base-note interval :interval :or {base-note "c" interval "M2"}}]
  (str "問題。"
       (gen-audio-tag {:base-note base-note :interval interval})
       "音程は何でしょう？"))

(defn same-interval? [interval-key interval-ja]
  (not (empty? (re-seq (get intervals-ja->key-check-regexps interval-key) interval-ja))))
