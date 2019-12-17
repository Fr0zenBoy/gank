(ns gank.diplomat.ranked-queue
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.diplomat.summoner :as diplomat.summoner]))

(defn endpoint-summoner-rank [summoner-id]
  (str diplomat.commons/api-url
       "/lol/league/v4/entries/by-summoner/"
       summoner-id))

(defn endpoint-ranked-queue [queue tier division page]
  (str diplomat.commons/api-url
       "/lol/league-exp/v4/entries/"
       queue "/"
       tier "/"
       division 
       "?page="
       page))

(defn ranked-queue [queue tier division page]
  (->> (endpoint-ranked-queue queue tier division page) 
       diplomat.commons/get-riot-api))

(defn summoner-rank [nick]
  (let [encrypted-summoner-id ((diplomat.summoner/summoner nick) :id)]
    (->> (endpoint-summoner-rank encrypted-summoner-id) 
         diplomat.commons/get-riot-api)))
