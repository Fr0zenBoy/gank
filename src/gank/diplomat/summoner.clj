(ns gank.diplomat.summoner
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.logic.commons :as logic.commons]))

(defn endpoint-summoner [nick-name]
  (str diplomat.commons/api-url
       "/lol/summoner/v4/summoners/by-name/"
       (logic.commons/formated-nick nick-name)))

(defn endpoint-summoner-maestry [summoner-id]
  (str diplomat.commons/api-url
       "/lol/champion-mastery/v4/champion-masteries/by-summoner/"
       summoner-id))

(defn get-summoner [nick]
  (->> (endpoint-summoner nick) 
       diplomat.commons/get-riot-api))

(def summoner (memoize get-summoner))

(defn summoner-maestry [nick]
  (let [summonerid ((summoner nick) :id)]
    (->> (endpoint-summoner-maestry summonerid) 
         diplomat.commons/get-riot-api)))