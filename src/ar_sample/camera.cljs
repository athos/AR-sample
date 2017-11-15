(ns ar-sample.camera
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :camera [_ _]
  (js/THREE.Camera.))
