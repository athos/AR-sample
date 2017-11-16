(ns ar-sample.clock
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :clock [_ {:keys [fonts]}]
  (let [opts #js{:font (:helvetiker fonts)
                 :size 0.175
                 :height 0.05
                 :curveSegments 12
                 :bevelEnabled false}
        geo (js/THREE.TextGeometry. "00:00:00" opts)
        mat (js/THREE.MeshNormalMaterial. #js{:transparent false
                                              :side js/THREE.DoubleSide})]
    (js/THREE.Mesh. geo mat)))
