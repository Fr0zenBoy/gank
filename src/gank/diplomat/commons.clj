(ns gank.diplomat.commons
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as http]))

(def API-KEY (System/getenv "RIOT_KEY"))

(def api-url "https://br1.api.riotgames.com")

(def input-key (str "?api_key=" API-KEY))

(def and-key (str "&api_key=" API-KEY))

(defn get-riot-api [url]
  (-> (str url)
      (http/get {:accept :json})
      :body
      (cheshire/parse-string true)))