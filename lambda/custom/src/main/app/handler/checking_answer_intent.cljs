(ns app.handler.checking-answer-intent)

(def ^:private interval-key-ja-map
  {"M2" #{"メジャーセカンド" "ちょうにど"}
   "M3" #{"メジャーサード" "ちょうさんど"}
   "P4" #{"パーフェクトフォース" "かんぜんよんど" "かんぜんしど"}
   "P5" #{"パーフェクトフィフス" "かんぜんごど"},
   "M6" #{"メジャーシックス" "ちょうろくど"},
   "M7" #{"メジャーセブンス" "ちょうななど" "ちょうしちど"}})


(defn- can-handle? [handler-input]
  (and (= (-> handler-input
              .-requestEnvelope
              .-request
              .-type)
          "IntentRequest")
       (= (-> handler-input
              .-requestEnvelope
              .-request
              .-intent
              .-name)
          "CheckingAnswerIntent")))


(defn- get-session-attributes [handler-input]
  (-> handler-input
      .-attributesManager
      .getSessionAttributes))

(defn- get-interval-ja [handler-input]
  (-> handler-input
      .-requestEnvelope
      .-request
      .-intent
      .-slots
      .-Interval
      .-value))

(defn- correct-answer? [user-interval-ja answer-interval-key]
  (contains? (get interval-key-ja-map answer-interval-key) user-interval-ja))

(defn- make-speech-text [user-interval-ja answer-interval-key]
  (if (correct-answer? user-interval-ja answer-interval-key)
    (str "正解！" user-interval-ja "です。")
    (do
      (let [inteval-ja (first (get interval-key-ja-map answer-interval-key))]
        (str "不正解。答えは" inteval-ja "です。")))))

(defn- handle [handler-input]
  (let [user-interval-ja    (get-interval-ja handler-input)
        attributes          (get-session-attributes handler-input)
        answer-interval-key (.-interval attributes)
        speech-text         (make-speech-text user-interval-ja answer-interval-key)]
    (-> handler-input
        .-responseBuilder
        (.speak speech-text)
        (.reprompt speech-text)
        .getResponse)))

(def handler
  #js {:canHandle can-handle?
       :handle    handle})
