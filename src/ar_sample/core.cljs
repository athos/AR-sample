(ns ar-sample.core
  (:require ar-sample.app
            [goog.events :as events]
            [integrant.core :as ig]))

(enable-console-print!)

(println "This text is printed from src/ar-sample/core.cljs. Go ahead and edit it and see reloading in action.")

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

(defmethod ig/init-key :system [_ {:keys [app]}]
  {:app app})

(def system (atom nil))

(events/listen js/window events/EventType.LOAD
               #(reset! system (ig/init +config+)))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
