(ns ar-sample.app-state
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :app-state [_ _]
  (atom {}))
