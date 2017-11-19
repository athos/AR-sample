(ns ar-sample.light
  (:require [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :light [_ _]
  (let [light (js/THREE.DirectionalLight. 0xffffff)]
    (.set (.-position light) 0 0 2)
    light))
