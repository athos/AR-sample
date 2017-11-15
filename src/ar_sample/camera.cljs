(ns ar-sample.camera
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :camera [_ {:keys [scene]}]
  (let [camera (js/THREE.Camera.)]
    (.add scene camera)
    camera))
