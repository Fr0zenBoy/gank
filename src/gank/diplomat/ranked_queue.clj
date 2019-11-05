(ns gank.diplomat.ranked-queue
  (:require [gank.diplomat.commons :as d.commons]
            [gank.diplomat.summoner :as d.summoner]))

(defn endpoint-summoner-rank [summoner-id]
  (str d.commons/api-url
       "/lol/league/v4/entries/by-summoner/"
       summoner-id
       d.commons/input-key))

(defn endpoint-ranked-queue [queue tier division page]
  (str d.commons/api-url
       "/lol/league-exp/v4/entries/"
       queue "/"
       tier "/"
       division "?page="
       page "&api_key="
       d.commons/API-KEY))

(defn ranked-queue [queue tier division page]
  (d.commons/get-riot-api
   (endpoint-ranked-queue queue tier division page)))

(defn summoner-rank [nick]
  (let [encrypted-summoner-id ((d.summoner/summoner-memo nick) :id)]
    (d.commons/get-riot-api
     (endpoint-summoner-rank encrypted-summoner-id))))
