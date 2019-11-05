(ns gank.diplomat.summoner
  (:require [gank.diplomat.commons :as d.commons]
            [gank.diplomat.summoner :as d.summoner]
            [gank.logic.commons :as l.commons]))

(defn endpoint-summoner [nick-name]
  (str d.commons/api-url
       "/lol/summoner/v4/summoners/by-name/"
       (l.commons/formated-nick nick-name)
       d.commons/input-key))

(defn endpoint-summoner-maestry [summoner-id]
  (str d.commons/api-url
       "/lol/champion-mastery/v4/champion-masteries/by-summoner/"
       summoner-id
       d.commons/input-key))

(defn summoner [nick]
  (d.commons/get-riot-api (endpoint-summoner nick)))

(def summoner-memo (memoize summoner))

(defn summoner-maestry [nick]
  (let [summonerid ((summoner-memo nick) :id)]
    (d.commons/get-riot-api
     (endpoint-summoner-maestry summonerid))))