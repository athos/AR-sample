(ns ar-sample.core
  (:require ar-sample.app
            ar-sample.app-state
            ar-sample.camera
            ar-sample.context
            ar-sample.light
            ar-sample.marker
            ar-sample.mesh
            ar-sample.renderer
            ar-sample.scene
            ar-sample.source
            [integrant.core :as ig]))

(enable-console-print!)

(println "This text is printed from src/ar-sample/core.cljs. Go ahead and edit it and see reloading in action.")

(def +config+
  {:app-state {}
   :source {}
   :renderer {}
   :scene {}
   :light   {:scene (ig/ref :scene)}
   :camera  {:scene (ig/ref :scene)}
   :context {:source (ig/ref :source)
             :camera (ig/ref :camera)}
   :marker  {:scene (ig/ref :scene)
             :context (ig/ref :context)}
   :mesh    {:marker (ig/ref :marker)}
   :app     {:app-state (ig/ref :app-state)
             :source (ig/ref :source)
             :renderer (ig/ref :renderer)
             :scene (ig/ref :scene)
             :camera (ig/ref :camera)
             :context (ig/ref :context)}})

(defmethod ig/init-key :system [_ {:keys [app]}]
  {:app app})

(def system (atom nil))

(.addEventListener js/window "load"
                   #(reset! system (ig/init +config+)))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
