(ns gank.diplomat.match
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.diplomat.summoner :as diplomat.summoner]))

(defn- endpoint-match-list
  ([account-id]
   (str "/lol/match/v4/matchlists/by-account/"
        account-id))
  ([account-id champion-id]
   (str "/lol/match/v4/matchlists/by-account/"
        account-id 
        "?champion="
        champion-id)))

(defn summoner-matchs [summoner]
  (let [account-id (get (diplomat.summoner/summoner summoner) :accountId)]
    (diplomat.commons/get-riot-api (endpoint-match-list account-id))))

(defn- match-data [matchId]
  (let [endpint-match-by-id (str "/lol/match/v4/matches/" matchId)]
    (diplomat.commons/get-riot-api endpint-match-by-id)))

(def match-data-memo (memoize match-data))