(ns gank.diplomat.ddragon
  (:require [gank.diplomat.http.discovery :as discovery]
            [gank.diplomat.http.out :as http-out]
            [gank.schemata.ddragon :as sc.ddragon]
            [schema.core :as s]))

(s/defn ^:private version :- sc.ddragon/ddragron-versions
  []
  (-> (discovery/get-ddragon-endpoints :versions)
      (http-out/get-ddgragon-api nil)
      :n))

(s/defn ^:private filter-version :- s/Str
  [resource :- sc.ddragon/ddragon-resource]
  (get (version) resource))

(s/defn ^:private ddragon-resources
  [resource :- sc.ddragon/ddragon-resource]
  (let [resource-version  (filter-version resource)
        endpoints         (discovery/get-ddragon-endpoints resource)]
    (http-out/get-ddgragon-api endpoints resource-version)))

(def ^:private ddragon (memoize ddragon-resources))

(def champion (ddragon :champion))
(def item (ddragon :item))
(def summoner (ddragon :summoner))