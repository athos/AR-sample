(ns ar-sample.context
  (:require ar-sample.camera
            ar-sample.source
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defmethod ig/init-key :context [_ {:keys [^js/THREEx.ArToolkitSource source
                                           ^js/THREE.Camera camera]}]
  (let [opts #js{:debug false
                 :cameraParametersUrl "data/camera_para.dat"
                 :detectionMode "mono"
                 :imageSmoothingEnabled true
                 :maxDetectionRate 60
                 :canvasWidth (.. source -parameters -sourceWidth)
                 :canvasHeight (.. source -parameters -sourceHeight)}
        context (js/THREEx.ArToolkitContext. opts)]
    (doto context
      (.init #(.copy (.-projectionMatrix camera)
                     (.getProjectionMatrix context))))))
