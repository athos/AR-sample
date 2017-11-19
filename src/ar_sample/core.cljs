(ns ar-sample.core
  (:require ar-sample.app
            [goog.events :as events]
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(enable-console-print!)

(def ^:private HELVETIKER_FONT_PATH
  "data/helvetiker_regular.typeface.json")

(def +config+
  {:app-state {}
   :fonts     {} ;; loaded dynamically
   :source    {}
   :renderer  {}
   :camera    {}
   :light     {}
   :mesh      {}
   :context   {:source (ig/ref :source)
               :camera (ig/ref :camera)}
   :clock     {:app-state (ig/ref :app-state)
               :fonts (ig/ref :fonts)}
   :marker    {:context (ig/ref :context)
               :clock (ig/ref :clock)
               :mesh (ig/ref :mesh)}
   :scene     {:camera (ig/ref :camera)
               :light (ig/ref :light)
               :marker (ig/ref :marker)}
   :app       {:app-state (ig/ref :app-state)
               :source (ig/ref :source)
               :renderer (ig/ref :renderer)
               :mesh (ig/ref :mesh)
               :scene (ig/ref :scene)
               :camera (ig/ref :camera)
               :context (ig/ref :context)}})

(defmethod ig/init-key :fonts [_ fonts]
  fonts)

(defonce system (atom nil))

(defn start [fonts]
  (let [config (assoc +config+ :fonts fonts)]
    (reset! system (ig/init config))))

(defn stop [system]
  (ig/halt! @system))

(defn init []
  (.load (js/THREE.FontLoader.) HELVETIKER_FONT_PATH
    (fn [font]
      (start {:helvetiker font}))))

(events/listen js/window events/EventType.LOAD init)

(defn on-js-reload []
  (print "reloading system ... ")
  (let [{:keys [fonts]} @system]
    (stop system)
    (start fonts))
  (println "reloading done."))
