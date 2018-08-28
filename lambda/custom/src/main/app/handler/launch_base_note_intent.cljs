(ns app.handler.launch-base-note-intent)

(def ^:private note-ja-key-map
  {"ド" "c"})

(def ^:private intervals
  ["M2" "M3" "P4" "P5" "M6" "M7"])


(defn- can-handle? [handler-input]
  (= (-> handler-input
         .-requestEnvelope
         .-request
         .-type)
     "LaunchRequest"))


(defn- get-base-note-ja [handler-input]
  (-> handler-input
      .-requestEnvelope
      .-request
      .-intent
      .-slots
      .-Note
      .-value))

(defn- get-session-attributes [handler-input]
  (-> handler-input
      .-attributesManager
      .getSessionAttributes))

(defn- note-ja->note-key [note-ja]
  (get note-ja-key-map note-ja))

(defn- choose-interval! []
  (rand-nth intervals))


(defn- set-base-note-key-on-attributes! [base-note-key attributes]
  (set! (.-baseNoteKey attributes) base-note-key))

(defn- set-interval-on-attributes! [interval attributes]
  (set! (.-interval attributes) interval))


(defn- handle [handler-input]
  (let [base-note-key "c"
        interval      (choose-interval!)
        attributes    (get-session-attributes handler-input)
        audio-tag     (str "<audio src=\"https://s3-ap-northeast-1.amazonaws.com/toku345-interval-master/"
                           base-note-key "_" interval ".mp3\" />")
        speech-text   (str "音程クイズへようこそ。サウンドを再生するので、その音程を答えてください！"
                           "問題！"
                           audio-tag
                           "音程はなんでしょう？")]
    (set-base-note-key-on-attributes! base-note-key attributes)
    (set-interval-on-attributes! interval attributes)
    (-> handler-input
        .-responseBuilder
        (.speak speech-text)
        (.reprompt speech-text)
        .getResponse)))


(def handler
  #js {:canHandle can-handle?
       :handle    handle})
