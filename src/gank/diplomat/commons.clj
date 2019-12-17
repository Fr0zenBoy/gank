(ns gank.diplomat.commons
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as http]))

(def ^:private API-KEY (System/getenv "RIOT_KEY"))

(def api-url "https://br1.api.riotgames.com")

(def ^:private api-headers {:headers {"Accept-Charset" "application/x-www-form-urlencoded; charset=UTF-8"
                                      "Accept-Language" "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"
                                      "X-Riot-Token" API-KEY}})

(defn get-riot-api [url]
  (-> (str url)
      (http/get api-headers)
      :body
      (cheshire/parse-string true)))