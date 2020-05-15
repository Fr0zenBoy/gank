(ns gank.components.client
  (:require [com.stuartsierra.component :as component]
            [gank.diplomat.http.out :as http.out])
  (:use [clojure.tools.logging]))

(defrecord Client [secret-key]
  component/Lifecycle
  (start [this]
    (info "starting the client")
   (let [binding value]
     )))



