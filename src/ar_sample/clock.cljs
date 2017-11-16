(ns ar-sample.clock
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :clock [_ {:keys [fonts]}]
  (let [opts #js{:font (:helvetiker fonts)
                 :size 1
                 :height 0.5
                 :curveSegments 12
                 :bevelEnabled false}
        geo (js/THREE.TextGeometry. "ABC" opts)
        mat (js/THREE.MeshNormalMaterial. #js{:transparent false
                                              :side js/THREE.DoubleSide})
        clock (js/THREE.Mesh. geo mat)]
    (.set (.-position clock) 0 0 0)
    clock))
