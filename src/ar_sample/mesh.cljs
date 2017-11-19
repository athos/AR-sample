(ns ar-sample.mesh
  (:require [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :mesh [_ _]
  (let [geo (js/THREE.CubeGeometry. 1 0.2 1)
        opts #js{:transparent true
                 :opacity 0.5
                 :side js/THREE.DoubleSide}
        mat (js/THREE.MeshNormalMaterial. opts)]
    (js/THREE.Mesh. geo mat)))
