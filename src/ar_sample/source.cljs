(ns ar-sample.source
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :source [_ _]
  (doto (js/THREEx.ArToolkitSource. #js{:sourceType "webcam"})
    (.init)))
