(ns ar-sample.app
  (:require ar-sample.app-state
            ar-sample.camera
            ar-sample.context
            ar-sample.renderer
            ar-sample.scene
            ar-sample.source
            [integrant.core :as ig]))

(defn- setup-renderer [renderer]
  (.setSize renderer 640 480)
  (.appendChild (js/document.getElementById "app")
                (.-domElement renderer)))

(defn start [{:keys [app-state renderer source] :as app}]
  (letfn [(render []
            (when (:running? @app-state)
              (js/requestAnimationFrame render)
              (when (.-ready source)
                (.update (:context app) (.-domElement source))
                (.render renderer (:scene app) (:camera app)))))]
    (setup-renderer renderer)
    (swap! app-state assoc :running? true)
    (render)))

(defmethod ig/init-key :app [_ opts]
  (start opts)
  opts)

(defmethod ig/halt-key! :app [_ app]
  (swap! (:app-state app) assoc :running? false))
