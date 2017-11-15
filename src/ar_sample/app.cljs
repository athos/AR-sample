(ns ar-sample.app
  (:require [integrant.core :as ig]))

(defn start [app]
  (let [state (:app-state app)]
    (letfn [(render []
              (when (:running? @state)
                (js/requestAnimationFrame render)
                (when (.-ready (:source app))
                  (.update (:context app) (.-domElement (:source app)))
                  (.render (:renderer app) (:scene app) (:camera app)))))]
      (swap! state assoc :running? true)
      (render))))

(defmethod ig/init-key :app [_ opts]
  (start opts)
  opts)

(defmethod ig/halt-key! :app [_ app]
  (swap! (:app-state app) assoc :running? false))
