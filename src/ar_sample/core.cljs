(ns ar-sample.core)

(enable-console-print!)

(println "This text is printed from src/ar-sample/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def scene (js/THREE.Scene.))
(def renderer
  (js/THREE.WebGLRenderer. #js{:antialias true, :alpha true}))

(doto renderer
  (.setClearColor (js/THREE.Color. "black") 0)
  (.setSize 640 480))

(set! (.. renderer -domElement -style -position) "absolute")
(set! (.. renderer -domElement -style -top) "0px")
(set! (.. renderer -domElement -style -left) "0px")
(.appendChild js/document.body (.-domElement renderer))

(def camera (js/THREE.Camera.))
(.add scene camera)

(def light (js/THREE.DirectionalLight. 0xffffff))
(.set (.-position light) 0 0 2)
(.add scene light)

(def source
  (js/THREEx.ArToolkitSource. #js{:sourceType "webcam"}))
(.init source)

(def context
  (let [opts #js{:debug false
                 :cameraParametersUrl "/data/camera_para.dat"
                 :detectionMode "mono"
                 :imageSmoothingEnabled true
                 :maxDetectionRate 60
                 :canvasWidth (.. source -parameters -sourceWidth)
                 :canvasHeight (.. source -parameters -sourceHeight)}]
    (js/THREEx.ArToolkitContext. opts)))
(.init context
  (fn []
    (.copy (.-projectionMatrix camera)
           (.getProjectionMatrix context))))

(def marker1 (js/THREE.Group.))
(def controls
  (let [opts #js{:type "pattern", :patternUrl "/data/hiro.patt"}]
    (js/THREEx.ArMarkerControls. context marker1 opts)))
(.add scene marker1)

(def geo (js/THREE.CubeGeometry. 1 1 1))
(def mat
  (let [opts #js{:transparent true
                 :opacity 0.5
                 :side js/THREE.DoubleSide}]
    (js/THREE.MeshNormalMaterial. opts)))
(def mesh1 (js/THREE.Mesh. geo mat))
(.set (.-position mesh1) 0 0.5 0)
(.add marker1 mesh1)

(defn ^:export render []
  (js/requestAnimationFrame render)
  (when (.-ready source)
    (.update context (.-domElement source))
    (.render renderer scene camera)))
(render)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
