(ns gank.diplomat.champion-data
  (:require [gank.diplomat.summoner-data :as s.data]))

(def riot-data-url "http://ddragon.leagueoflegends.com/")

(def riot-data-version-url "http://ddragon.leagueoflegends.com/realms/na.json")

(def riot-data-version (-> (s.data/get-riot-api (str riot-data-version-url s.data/input-key)) (get :n)))

(defn url-champion-data [version]
  (str riot-data-url "cdn/" version "/data/en_US/champion.json" s.data/input-key))

(defn version [type {:keys [item champion summoner mastery rune]}]
  (let [input (clojure.string/lower-case type)]
    (cond
      (= input "item") item
      (= input "champion") champion
      (= input "summoner") summoner
      (= input "mastery") mastery
      (= input "rune") rune
      :default "9.20.1")))

(defn champion-data [resource-version riot-version]
  (s.data/get-riot-api (url-champion-data (version resource-version riot-version))))

(def champion-data-memo (memoize champion-data))