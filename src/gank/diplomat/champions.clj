(ns gank.diplomat.champions
  (:require [gank.diplomat.commons :as diplomat.commons]))

(def ^:private riot-data-version ((diplomat.commons/get-ddgragon-api "/realms/na.json") :n))

(defn- version [type]
  (let [resource (clojure.string/lower-case type)]
    (->> resource
         keyword
         riot-data-version)))

(defn- get-data [type]
  (let [resource-version (version type)
        endpoint (str "/cdn/" resource-version "/data/en_US/champion.json")]
    (diplomat.commons/get-ddgragon-api endpoint)))

(def champion-data (memoize get-data))