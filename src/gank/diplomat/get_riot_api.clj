(ns gank.diplomat.get-riot-api
  (:require [clj-http.client :as http]
            [cheshire.core :refer :all]
            [gank.igredients.ranks :as ranks]))

(def API-KEY (System/getenv "RIOT_KEY"))

(def api-url "https://br1.api.riotgames.com")

(def input-key (str "?api_key=" API-KEY))

(defn get-riot-api [url]
  (-> (str url)
      (http/get {:accept :json})
      :body
      (parse-string true)))

(defn summoner [api-url nick key]
  (get-riot-api (str api-url
                     "/lol/summoner/v4/summoners/by-name/" nick key)))

(defn summoner-maestry [api-url nick key]
  (let [summonerid ((summoner api-url nick key) :id)]
    (get-riot-api (str api-url
                       "/lol/champion-mastery/v4/champion-masteries/by-summoner/"
                       summonerid key))))

(defn summoner-rank [api-url nick key]
  (let [encrypted-summoner-id ((summoner api-url nick key) :id)]
    (get-riot-api (str api-url
                       "/lol/league/v4/entries/by-summoner/"
                       encrypted-summoner-id
                       key))))

(defn ranked-queue [api-url queue tier division key]
  (get-riot-api (str api-url
                     "/lol/league-exp/v4/entries/"
                     queue "/"
                     tier "/"
                     division
                     "?page=1"
                     "&api_key="
                     key)))