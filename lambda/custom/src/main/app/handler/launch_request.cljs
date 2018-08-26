(ns app.handler.launch-request)

(def handler
  #js {:canHandle
       (fn [handler-input]
         (= (-> handler-input
                .-requestEnvelope
                .-request
                .-type)
            "LaunchRequest"))

       :handle
       (fn [handler-input]
         (let [speech-text "音程クイズへようこそ。サウンドを再生するので、その音程を答えてください！開始の音は何にしますか？"]
           (-> handler-input
             .-responseBuilder
             (.speak speech-text)
             (.reprompt speech-text)
             .getResponse)))})
