(ns ar-sample.renderer
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :renderer [_ _]
  (let [opts #js{:antialias true, :alpha true}
        renderer (js/THREE.WebGLRenderer. opts)]
   (doto renderer
     (.setClearColor (js/THREE.Color. "black") 0)
     (.setSize 640 480))
   (set! (.. renderer -domElement -style -position) "absolute")
   (set! (.. renderer -domElement -style -top) "0px")
   (set! (.. renderer -domElement -style -left) "0px")
   (.appendChild js/document.body (.-domElement renderer))
   renderer))
