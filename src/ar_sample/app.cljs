(ns ar-sample.app
  (:require ar-sample.app-state
            ar-sample.camera
            ar-sample.context
            ar-sample.renderer
            ar-sample.scene
            ar-sample.source
            [goog.dom :as dom]
            [goog.events :as events]
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defn- setup-renderer [^js/THREE.WebGLRenderer renderer]
  (.setSize renderer 640 480)
  (dom/appendChild (dom/getElement "app")
                   (.-domElement renderer)))

(defn- resize [app]
  (let [{:keys [^js/THREEx.ArToolkitSource source
                ^js/THREEx.ArToolkitContext context
                ^js/THREE.WebGLRenderer renderer]} app]
    (.onResizeElement source)
    (.copyElementSizeTo source (.-domElement renderer))
    (when (.-arController context)
      (.copyElementSizeTo source (.. context -arController -canvas)))))

(defn start [app]
  (let [{:keys [^js/THREEx.ArToolkitContext context
                ^js/THREE.WebGLRenderer renderer
                ^js/THREEx.ArToolkitSource source]} app]
    (letfn [(render []
              (when (:app/running? @(:app-state app))
                (js/requestAnimationFrame render)
                (when (.-ready source)
                  (.update ^js/THREEx.ArToolkitContext context
                           (.-domElement source))
                  (.render renderer (:scene app) (:camera app)))))]
      (.init source #(resize app))
      (events/listen js/window "resize" #(resize app))
      (setup-renderer renderer)
      (swap! (:app-state app) assoc :app/running? true)
      (render))))

(defmethod ig/init-key :app [_ opts]
  (start opts)
  opts)

(defmethod ig/halt-key! :app [_ app]
  (dom/removeChildren (dom/getElement "app"))
  (events/removeAll js/window "resize")
  (swap! (:app-state app) assoc :app/running? false))
