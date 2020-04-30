(ns gank.diplomat.ddragon
  (:require [gank.diplomat.http.discovery :as discovery]
            [gank.diplomat.http.out :as http-out]))

(defn- filter-version []
  (-> (discovery/ddragon-endpoints :versions)
      (http-out/get-ddgragon-api nil)
      :n))

(defn- version [type]
  (get (filter-version) type))

(defn- ddragon-resources [type]
  (let [resource-version [(version type)]
        endpoints         (discovery/ddragon-endpoints (keyword type))]
    (http-out/get-ddgragon-api endpoints resource-version)))

(def ^:private ddragon (memoize ddragon-resources))
(def champion (ddragon :champion))
(def item (ddragon :item))
(def summoner (ddragon :summoner))