(ns ar-sample.core
  (:require ar-sample.app
            [goog.events :as events]
            [integrant.core :as ig]))

(enable-console-print!)

(def +config+
  {:app-state {}
   :source    {}
   :renderer  {}
   :camera    {}
   :light     {}
   :mesh      {}
   :context   {:source (ig/ref :source)
               :camera (ig/ref :camera)}
   :marker    {:context (ig/ref :context)
               :mesh (ig/ref :mesh)}
   :scene     {:camera (ig/ref :camera)
               :light (ig/ref :light)
               :marker (ig/ref :marker)}
   :app       {:app-state (ig/ref :app-state)
               :source (ig/ref :source)
               :renderer (ig/ref :renderer)
               :scene (ig/ref :scene)
               :camera (ig/ref :camera)
               :context (ig/ref :context)}})

(defonce system (atom nil))

(defn init []
  (reset! system (ig/init +config+)))

(events/listen js/window events/EventType.LOAD init)

(defn on-js-reload []
  (print "reloading system ... ")
  (ig/halt! @system)
  (init)
  (println "reloading done."))
