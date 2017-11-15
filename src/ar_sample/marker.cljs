(ns ar-sample.marker
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :marker [_ {:keys [scene context]}]
  (let [marker (js/THREE.Group.)
        opts #js{:type "pattern", :patternUrl "/data/hiro.patt"}]
    (js/THREEx.ArMarkerControls. context marker opts)
    (.add scene marker)
    marker))
