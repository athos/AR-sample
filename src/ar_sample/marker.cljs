(ns ar-sample.marker
  (:require ar-sample.context
            ar-sample.clock
            ar-sample.mesh
            [integrant.core :as ig]))

(defmethod ig/init-key :marker [_ {:keys [context clock mesh]}]
  (let [marker (js/THREE.Group.)
        opts #js{:type "pattern", :patternUrl "/data/hiro.patt"}]
    (js/THREEx.ArMarkerControls. context marker opts)
    (set! (.. clock -rotation -x) (- (/ js/Math.PI 2)))
    (.set (.-position clock) -0.46 0.2 0.1)
    (.add marker clock)
    (.set (.-position mesh) 0 0.1 0)
    (.add marker mesh)
    marker))
