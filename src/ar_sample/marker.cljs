(ns ar-sample.marker
  (:require ar-sample.context
            [ar-sample.clock :as clock]
            ar-sample.mesh
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :marker [_ {:keys [context clock mesh]}]
  (let [marker (js/THREE.Group.)
        opts #js{:type "pattern", :patternUrl "data/hiro.patt"}]
    (js/THREEx.ArMarkerControls. context marker opts)
    (clock/attach-clock clock marker
      (fn [^js/THREE.Mesh c]
        (set! (.. c -rotation -x) (- (/ js/Math.PI 2)))
        (.set (.-position c) -0.46 0.2 0.1)))
    (.set (.-position mesh) 0 0.1 0)
    (.add marker mesh)
    marker))
