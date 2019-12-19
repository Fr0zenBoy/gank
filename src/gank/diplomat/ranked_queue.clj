(ns gank.diplomat.ranked-queue
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.diplomat.summoner :as diplomat.summoner]))

(defn ranked-queue [queue tier division page]
  (let [endpoint-ranked-queue (str "/lol/league-exp/v4/entries/"
                                   queue "/"
                                   tier "/"
                                   division
                                   "?page="
                                   page)]
    (diplomat.commons/get-riot-api endpoint-ranked-queue)))

(defn summoner-rank [nick]
  (let [encrypted-summoner-id (-> (diplomat.summoner/summoner nick) :id)
        endpoint-summoner-rank (str "/lol/league/v4/entries/by-summoner/" encrypted-summoner-id)]
    (diplomat.commons/get-riot-api endpoint-summoner-rank)))