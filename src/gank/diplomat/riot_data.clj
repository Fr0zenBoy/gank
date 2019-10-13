(ns gank.diplomat.riot-data
  (:require [gank.diplomat.get-riot-api :as riot]))

(def champion-data-url "http://ddragon.leagueoflegends.com/realms/na.json")

(def riot-data-version (-> (riot/get-riot-api (str champion-data-url riot/input-key)) (get :n)))

(defn version [{:keys [item rune mastery summoner champion] :as riot-data-version} type]
  (let [even? ])
  (cond
    ))

(defn champion-data [api-url version key]
  (riot/get-riot-api (str api-url
                     "/cdn/"
                     version "/"
                     "/data/en_US/champion.json"
                     key)))