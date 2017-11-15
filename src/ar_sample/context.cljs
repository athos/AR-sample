(ns ar-sample.context
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :context [_ {:keys [source camera]}]
  (let [opts #js{:debug false
                 :cameraParametersUrl "/data/camera_para.dat"
                 :detectionMode "mono"
                 :imageSmoothingEnabled true
                 :maxDetectionRate 60
                 :canvasWidth (.. source -parameters -sourceWidth)
                 :canvasHeight (.. source -parameters -sourceHeight)}
        context (js/THREEx.ArToolkitContext. opts)]
    (doto context
      (.init #(.copy (.-projectionMatrix camera)
                     (.getProjectionMatrix context))))))
