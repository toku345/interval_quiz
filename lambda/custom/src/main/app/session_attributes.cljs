(ns app.session-attributes)

(defn get-session-attributes [handler-input]
  (-> handler-input
      .-attributesManager
      .getSessionAttributes))

(defn set-base-note! [base-note attributes]
  (set! (.-baseNote attributes) base-note))

(defn- set-interval! [interval attributes]
  (set! (.-interval attributes) interval))
