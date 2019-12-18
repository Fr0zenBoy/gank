(ns gank.diplomat.champions
  (:require [gank.diplomat.commons :as diplomat.commons]))

(def ^:private riot-data-url "http://ddragon.leagueoflegends.com/")

(def ^:private riot-data-version-url "http://ddragon.leagueoflegends.com/realms/na.json")

(def ^:private riot-data-version (->> riot-data-version-url
                                     diplomat.commons/api-get
                                     :n))

(defn- url-champion-data [version]
  (str riot-data-url "cdn/"
       version "/data/en_US/champion.json"))

(defn version [type]
  (let [resource (clojure.string/lower-case type)]
    (->> resource
         keyword
         riot-data-version)))

(defn- get-data [type]
  (->> (version type)
       url-champion-data
       diplomat.commons/api-get))

(def champion-data (memoize get-data))