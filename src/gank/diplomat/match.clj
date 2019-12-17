(ns gank.diplomat.match
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.diplomat.summoner :as diplomat.summoner]))

(defn endpoint-match-list
  ([account-id]
   (str diplomat.commons/api-url
        "/lol/match/v4/matchlists/by-account/"
        account-id))
  ([account-id champion-id]
   (str "/lol/match/v4/matchlists/by-account/"
        account-id 
        "?champion="
        champion-id)))

(defn endpint-match-by-id [matchId]
  (str diplomat.commons/api-url
       "/lol/match/v4/matches/"
       matchId))

(defn summoner-matchs [summoner]
  (let [account-id (get (diplomat.summoner/summoner summoner) :accountId)]
    (->> (endpoint-match-list account-id)
         diplomat.commons/get-riot-api)))

(defn match-data [matchId]
  (->> (endpint-match-by-id matchId) 
       diplomat.commons/get-riot-api))

(def match-data-memo (memoize match-data))