(ns ar-sample.app
  (:require ar-sample.app-state
            ar-sample.camera
            ar-sample.context
            ar-sample.renderer
            ar-sample.scene
            ar-sample.source
            [goog.dom :as dom]
            [integrant.core :as ig]))

(defn- setup-renderer [renderer]
  (.setSize renderer 640 480)
  (dom/appendChild (dom/getElement "app")
                   (.-domElement renderer)))

(defn- resize [{:keys [source context renderer]}]
  (.onResizeElement source)
  (.copyElementSizeTo source (.-domElement renderer))
  (when (.-arController context)
    (.copyElementSizeTo source (.. context -arController -canvas))))

(defn animate [app])

(defn start [{:keys [app-state renderer source] :as app}]
  (letfn [(render []
            (when (:running? @app-state)
              (js/requestAnimationFrame render)
              (when (.-ready source)
                (animate app)
                (.update (:context app) (.-domElement source))
                (.render renderer (:scene app) (:camera app)))))]
    (.init source #(resize app))
    (setup-renderer renderer)
    (swap! app-state assoc :running? true)
    (render)))

(defmethod ig/init-key :app [_ opts]
  (start opts)
  opts)

(defmethod ig/halt-key! :app [_ app]
  (dom/removeChildren (dom/getElement "app"))
  (swap! (:app-state app) assoc :running? false))
