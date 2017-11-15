(ns ar-sample.scene
  (:require ar-sample.camera
            ar-sample.light
            ar-sample.marker
            [integrant.core :as ig]))

(defmethod ig/init-key :scene [_ {:keys [camera light marker]}]
  (doto (js/THREE.Scene.)
    (.add camera)
    (.add light)
    (.add marker)))
