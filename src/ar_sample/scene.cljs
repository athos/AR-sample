(ns ar-sample.scene
  (:require ar-sample.camera
            ar-sample.light
            ar-sample.marker
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :scene [_ opts]
  (let [{:keys [^js/THREE.Camera camera
                ^js/THREE.DirectionalLight light
                ^js/THREE.Group marker]} opts]
    (doto (js/THREE.Scene.)
      (.add camera)
      (.add light)
      (.add marker))))
