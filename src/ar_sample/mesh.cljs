(ns ar-sample.mesh
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :mesh [_ _]
  (let [geo (js/THREE.CubeGeometry. 1 1 1)
        mat (js/THREE.MeshNormalMaterial. #js{:transparent true
                                              :opacity 0.5
                                              :side js/THREE.DoubleSide})
        mesh (js/THREE.Mesh. geo mat)]
    (.set (.-position mesh) 0 0.5 0)
    mesh))
