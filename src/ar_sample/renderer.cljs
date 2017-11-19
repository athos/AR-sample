(ns ar-sample.renderer
  (:require [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :renderer [_ _]
  (let [opts #js{:antialias true, :alpha true}
        renderer (js/THREE.WebGLRenderer. opts)]
   (doto renderer
     (.setClearColor (js/THREE.Color. "black") 0))
   (set! (.. renderer -domElement -style -position) "absolute")
   (set! (.. renderer -domElement -style -top) "0px")
   (set! (.. renderer -domElement -style -left) "0px")
   renderer))
