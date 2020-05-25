(ns gank.riot
  (:require [gank.diplomat.http.discovery :as http.discovery]
            [cheshire.core :as cheshire]
            [clj-http.client :as http]
            [schema.core :as s]
            [gank.logic.commons :as logic.commons]))

(s/defn ^:private client! :- {s/Keyword s/Any}
  []
  {:riot-url     "https://br1.api.riotgames.com"
   :ddragron-url "http://ddragon.leagueoflegends.com"
   :header       {:headers {"Accept-Charset" "application/x-www-form-urlencoded; charset=UTF-8"
                            "Accept-Language" "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"
                            "X-Riot-Token" (System/getenv "RIOT_KEY")}}})

(s/defn ^:private make-url :- s/Str
  [base-url  :- s/Str 
   endpoints :- s/Str]
  (str base-url endpoints))

(s/defn ^:private make-endpoints :- s/Str
  [endpoints :- s/Str
   args      :- [s/Str]]
  (cond
    (not (clojure.string/includes? endpoints "?"))
    endpoints
    :else
    (recur (clojure.string/replace-first endpoints "?" (first args)) (rest args))))

(s/defn ^:private api-get! :- {s/Keyword s/Any}
  [api-url   :- s/Str
   header    :- {s/Keyword s/Any}
   endpoint :- s/Str
   & args]
  (let [endpoint (make-endpoints endpoint args)
        url (make-url api-url endpoint)]
    (-> url
        (http/get header)
        :body
        (cheshire/parse-string true))))

(defn lol-api
  [{:keys [riot-url header] :as _client}
   resource
   filter
   & args]
  (let [endpoint (http.discovery/get-lol-endpoints resource filter)]
    (api-get! riot-url header endpoint args)))

(defn memoize-player-identity [client summoner-nickname]
  (->> summoner-nickname
       logic.commons/format-nick
       (lol-api client :summoner :name)))

(def player-identity (memoize memoize-player-identity))

(defn prep-match-data [client match-id]
  (->> match-id (lol-api client :match :match-data)))

(def match-data (memoize prep-match-data))
