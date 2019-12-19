(ns gank.diplomat.summoner
  (:require [gank.diplomat.commons :as diplomat.commons]
            [gank.logic.commons :as logic.commons]))

(defn- get-summoner [nick-name]
  (let [endpoint-summoner (str "/lol/summoner/v4/summoners/by-name/" (logic.commons/formated-nick nick-name))]
    (diplomat.commons/get-riot-api endpoint-summoner)))

(def summoner (memoize get-summoner))

(defn summoner-maestry [nick]
  (let [summoner-id               ((summoner nick) :id)
        endpoint-summoner-maestry (str "/lol/champion-mastery/v4/champion-masteries/by-summoner/" summoner-id)]
    (diplomat.commons/get-riot-api endpoint-summoner-maestry)))