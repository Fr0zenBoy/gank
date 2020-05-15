(ns gank.riot
  (:require [gank.diplomat.http.discovery :as http.discovery]
            [cheshire.core :as cheshire]
            [clj-http.client :as http]
            [schema.core :as s]))

(s/defn ^:private riot-client :- {s/Keyword s/Any}
  []
  (let [api-key (System/getenv "RIOT_KEY")]
    {:riot-url     "https://br1.api.riotgames.com"
     :ddragron-url "http://ddragon.leagueoflegends.com"
     :header       {:headers {"Accept-Charset" "application/x-www-form-urlencoded; charset=UTF-8"
                              "Accept-Language" "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"
                              "X-Riot-Token" api-key}}}))

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

(s/defn ^:private api-get :- {s/Keyword s/Any}
  [api-url   :- s/Str
   header    :- {s/Keyword s/Any}
   endpoints :- s/Str
   & args]
  (let [endpoints (make-endpoints endpoints args)
        url (make-url api-url endpoints)]
    (-> url
        (http/get header)
        :body
        (cheshire/parse-string true))))

(s/defn get-riot-api [client endpoint & args]
  (let [url    (:riot-url client)
        header (:header client)]
    (api-get url header endpoint args)))

