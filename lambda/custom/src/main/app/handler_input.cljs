(ns app.handler-input)

(defn gen-handler-input [type]
  (clj->js {:requestEnvelope
            {:request
             {:type type}}}))

(defn request-type [handler-input]
  (-> (js->clj handler-input :keywordize-keys true)
      :requestEnvelope
      :request
      :type))

(defn intent-name [handler-input]
  (-> (js->clj handler-input :keywordize-keys true)
      :requestEnvelope
      :request
      :intent
      :name))
