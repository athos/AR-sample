(ns ar-sample.light
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :light [_ {:keys [scene]}]
  (let [light (js/THREE.DirectionalLight. 0xffffff)]
    (.set (.-position light) 0 0 2)
    (.add scene light)
    light))
