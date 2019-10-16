(ns gank.diplomat.summoner-data
  (:require [clj-http.client :as http]
            [cheshire.core :refer :all]
            [gank.igredients.ranks :as ranks]))

(def API-KEY (System/getenv "RIOT_KEY"))

(def api-url "https://br1.api.riotgames.com")

(def input-key (str "?api_key=" API-KEY))

(defn endpoint-summoner [nick-name]
  (str api-url "/lol/summoner/v4/summoners/by-name/" nick-name input-key))

(defn endpoint-summoner-maestry [summoner-id]
  (str api-url "/lol/champion-mastery/v4/champion-masteries/by-summoner/" summoner-id input-key))

(defn endpoint-summoner-rank [summoner-id]
  (str api-url "/lol/league/v4/entries/by-summoner/" summoner-id input-key))

(defn endpoint-ranked-queue [queue tier division page]
  (str api-url "/lol/league-exp/v4/entries/" queue "/" tier "/" division "?page=" page "&api_key=" API-KEY))

(defn get-riot-api [url]
  (-> (str url)
      (http/get {:accept :json})
      :body
      (parse-string true)))

(defn summoner [nick]
  (get-riot-api (endpoint-summoner nick)))

(defn summoner-maestry [nick]
  (let [summonerid ((summoner nick) :id)]
    (get-riot-api (endpoint-summoner-maestry summonerid))))

(defn summoner-rank [nick]
  (let [encrypted-summoner-id ((summoner nick) :id)]
    (get-riot-api (endpoint-summoner-rank encrypted-summoner-id))))

(defn ranked-queue [queue tier division page]
  (get-riot-api (endpoint-ranked-queue queue tier division page)))
