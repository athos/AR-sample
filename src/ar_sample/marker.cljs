(ns ar-sample.marker
  (:require ar-sample.context
            ar-sample.clock
            ar-sample.mesh
            [integrant.core :as ig]))

(defmethod ig/init-key :marker [_ {:keys [context clock mesh]}]
  (let [marker (js/THREE.Group.)
        opts #js{:type "pattern", :patternUrl "/data/hiro.patt"}]
    (js/THREEx.ArMarkerControls. context marker opts)
    (.set (.-position clock) 0 0 0)
    (.add marker clock)
    (.set (.-position mesh) 0 0.5 0)
    (.add marker mesh)
    marker))
