(ns gank.diplomat.http.out
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as http]
            [clojure.string :as string]
            [schema.core :as s]))

(def ^:private API-KEY (System/getenv "RIOT_KEY"))
(def ^:private riot-api-url "https://br1.api.riotgames.com")
(def ^:private riot-data-url "http://ddragon.leagueoflegends.com")
(def ^:private api-headers {:headers {"Accept-Charset" "application/x-www-form-urlencoded; charset=UTF-8"
                                      "Accept-Language" "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"
                                      "X-Riot-Token" API-KEY}})

(s/defn ^:private preparete-url 
  [base-url  :- s/Str 
   endpoints :- s/Str]
  (str base-url endpoints))

(s/defn ^:private make-endpoints 
  [endpoints :- s/Str
   args      :- [s/Str]]
  (cond
    (not (clojure.string/includes? endpoints "?"))
    endpoints
    :else
    (recur (clojure.string/replace-first endpoints "?" (first args)) (rest args))))

(defn- simple-api-get! [api-url endpoints args]
  (let [endpoints (make-endpoints endpoints args)
        url (preparete-url api-url endpoints)]
  (-> url
      (http/get api-headers)
      :body
      (cheshire/parse-string true))))

(def get-riot-api     (partial simple-api-get! riot-api-url))
(def get-ddgragon-api (partial simple-api-get! riot-data-url))

