(ns gank.diplomat.match
  (:require [gank.diplomat.commons :as d.commons]
            [gank.diplomat.summoner :as d.summoner]))

(defn endpoint-match-list
  ([account-id]
   (str d.commons/api-url
        "/lol/match/v4/matchlists/by-account/"
        account-id
        d.commons/input-key))
  ([account-id champion-id]
   (str "/lol/match/v4/matchlists/by-account/"
        account-id "?champion="
        champion-id
        d.commons/and-key)))

(defn endpint-match-by-id [matchId]
  (str d.commons/api-url
       "/lol/match/v4/matches/"
       matchId
       d.commons/input-key))

(defn summoner-matchs [summoner]
  (let [account-id (get (d.summoner/summoner-memo summoner) :accountId)]
    (d.commons/get-riot-api
     (endpoint-match-list account-id))))

(defn match-data [matchId]
  (d.commons/get-riot-api
   (endpint-match-by-id matchId)))

(def match-data-memo (memoize match-data))