(ns gank.diplomat.summoner-data
  (:require [clj-http.client :as http]
            [cheshire.core :refer :all]
            [gank.igredients.ranks :as ranks]
            [gank.logic.commons :as commons]))

(def API-KEY (System/getenv "RIOT_KEY"))

(def api-url "https://br1.api.riotgames.com")

(def input-key (str "?api_key=" API-KEY))

(defn endpoint-summoner [nick-name]
  (str api-url "/lol/summoner/v4/summoners/by-name/" (commons/formated-nick nick-name) input-key))

(defn endpoint-summoner-maestry [summoner-id]
  (str api-url "/lol/champion-mastery/v4/champion-masteries/by-summoner/" summoner-id input-key))

(defn endpoint-summoner-rank [summoner-id]
  (str api-url "/lol/league/v4/entries/by-summoner/" summoner-id input-key))

(defn endpoint-ranked-queue [queue tier division page]
  (str api-url "/lol/league-exp/v4/entries/" queue "/" tier "/" division "?page=" page "&api_key=" API-KEY))

(defn endpoint-matchid-summoner [summoner-id]
  (str api-url "/lol/match/v4/matchlists/by-account/" summoner-id))

(defn get-riot-api [url]
  (-> (str url)
      (http/get {:accept :json})
      :body
      (parse-string true)))

(defn summoner [nick]
  (get-riot-api (endpoint-summoner nick)))

(def summoner-memo (memoize summoner))

(defn summoner-maestry [nick]
  (let [summonerid ((summoner-memo nick) :id)]
    (get-riot-api (endpoint-summoner-maestry summonerid))))

(defn summoner-rank [nick]
  (let [encrypted-summoner-id ((summoner-memo nick) :id)]
    (get-riot-api (endpoint-summoner-rank encrypted-summoner-id))))

(defn summoner-matchs [summoner]
  (let [account-id (get summoner :accountId)]
    (get-riot-api (endpoint-matchid-summoner account-id))))

(defn ranked-queue [queue tier division page]
  (get-riot-api (endpoint-ranked-queue queue tier division page)))
