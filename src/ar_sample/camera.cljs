(ns ar-sample.camera
  (:require [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :camera [_ _]
  (js/THREE.Camera.))
