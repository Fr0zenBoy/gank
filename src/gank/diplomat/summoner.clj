(ns gank.diplomat.summoner
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.logic.commons :as logic.commons]))

(defn get-summoner [nick-name]
  (let [endpoint-summoner (str "/lol/summoner/v4/summoners/by-name/"
                          (logic.commons/formated-nick nick-name))]
    (->> endpoint-summoner
         diplomat.commons/get-riot-api)))

(def summoner (memoize get-summoner))

(defn summoner-maestry [nick]
  (let [summoner-id               (-> (summoner nick) :id)
        endpoint-summoner-maestry (str diplomat.commons/api-url
                                   "/lol/champion-mastery/v4/champion-masteries/by-summoner/"
                                   summoner-id)]
    (->> endpoint-summoner-maestry
         diplomat.commons/get-riot-api)))