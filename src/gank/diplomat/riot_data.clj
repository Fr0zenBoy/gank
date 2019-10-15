(ns gank.diplomat.riot-data
  (:require [gank.diplomat.get-riot-api :as riot]))

(def riot-data-url "http://ddragon.leagueoflegends.com/")

(def riot-data-version-url "http://ddragon.leagueoflegends.com/realms/na.json")

(def riot-data-version (-> (riot/get-riot-api (str riot-data-version-url riot/input-key)) (get :n)))

(defn version [type {:keys [item champion summoner mastery rune]}]
  (let [input (clojure.string/lower-case type)]
    (cond
      (= input "item") item
      (= input "champion") champion
      (= input "summoner") summoner
      (= input "mastery") mastery
      (= input "rune") rune
      :else "not")))

(version "champion" riot-data-version)

(defn champion-data [api-url version key]
  (riot/get-riot-api (str api-url
                          "/cdn/"
                          version "/"
                          "/data/en_US/champion.json"
                          key)))

(champion-data riot-data-url "9.20.1" riot/input-key)