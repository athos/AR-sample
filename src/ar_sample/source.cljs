(ns ar-sample.source
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :source [_ _]
  (js/THREEx.ArToolkitSource. #js{:sourceType "webcam"}))
