(ns ar-sample.clock
  (:require [goog.string :as gstr]
            goog.string.format
            [integrant.core :as ig]))

(set! *warn-on-infer* true)

(defn- make-clock [time font]
  (let [opts #js{:font font
                 :size 0.175
                 :height 0.05
                 :curveSegments 12
                 :bevelEnabled false}
        geo (js/THREE.TextGeometry. time opts)
        mat (js/THREE.MeshNormalMaterial. #js{:transparent false
                                              :side js/THREE.DoubleSide})]
    (js/THREE.Mesh. geo mat)))

(defmethod ig/init-key :clock [_ {:keys [app-state fonts]}]
  (letfn [(new-clock []
            (let [now (js/Date.)
                  time (gstr/format "%02d:%02d:%02d"
                                    (.getHours now)
                                    (.getMinutes now)
                                    (.getSeconds now))]
              (make-clock time (:helvetiker fonts))))
          (rec [clock ^js/THREE.Group target on-update]
            (when (:clock/running? @app-state)
              (when clock (.remove target clock))
              (let [clock' (new-clock)]
                (.add target clock')
                (on-update clock')
                (js/setTimeout #(rec clock' target on-update) 200))))]
    {:clock-fn (fn [target on-update]
                 (swap! app-state assoc :clock/running? true)
                 (rec nil target on-update))
     :app-state app-state}))

(defmethod ig/halt-key! :clock [_ {:keys [app-state]}]
  (swap! app-state assoc :clock/running? false))

(defn attach-clock [{:keys [clock-fn]} target on-update]
  (clock-fn target on-update))
