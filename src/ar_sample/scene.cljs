(ns ar-sample.scene
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :scene [_ _]
  (js/THREE.Scene.))
