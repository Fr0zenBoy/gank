
(ns gank.diplomat.http-out
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as http]
            [clojure.string :as string]
            [gank.logic.commons :as logic.commons]
            [schema.core :as s]))

(def ^:private API-KEY (System/getenv "RIOT_KEY"))

(def ^:private riot-api-url "https://br1.api.riotgames.com")

(def ^:private riot-data-url "http://ddragon.leagueoflegends.com")

(def ^:private api-headers {:headers {"Accept-Charset" "application/x-www-form-urlencoded; charset=UTF-8"
                                      "Accept-Language" "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh;q=0.5"
                                      "X-Riot-Token" API-KEY}})

(defn- preparete-url [base-url path]
  (str base-url path))

(defn- make-path [path args]
  (cond
    (not (clojure.string/includes? path "?"))
      path
    :else
    (recur (clojure.string/replace-first path "?" (first args)) (rest args))))

(defn- api-get [api-url path args]
  (let [path (make-path path args)
        url (preparete-url api-url path)]
  (-> url
      (http/get api-headers)
      :body
      (cheshire/parse-string true))))

(def get-riot-api (partial api-get riot-api-url))

(def ^:private lol-paths
  {:mastery    {:all-champions       "/lol/champion-mastery/v4/champion-masteries/by-summoner/?"
                :spefic-champion     "/lol/champion-mastery/v4/champion-masteries/by-summoner/?/by-champion/?"
                :total-points        "/lol/champion-mastery/v4/scores/by-summoner/?"}
   :champions  {:rotation            "/lol/platform/v3/champion-rotations"}
   :clash      {:summoner            "/lol/clash/v1/players/by-summoner/?"
                :team                "/lol/clash/v1/teams/?"
                :tournaments         "/lol/clash/v1/tournaments"
                :tournaments-team    "/lol/clash/v1/tournaments/by-team/?"
                :tournaments-id      "/lol/clash/v1/tournaments/?"}
   :leagues    {:challenger          "/lol/league/v4/challengerleagues/by-queue/?"
                :summoner            "/lol/league/v4/entries/by-summoner/?"
                :queue-tier-division "/lol/league/v4/entries/?/?/?"
                :gran-master         "/lol/league/v4/grandmasterleagues/by-queue/?"
                :master              "/lol/league/v4/masterleagues/by-queue/?"}
   :lol-status {:shards              "/lol/status/v3/shard-data"}
   :match      {:match-data          "/lol/match/v4/matches/?"
                :account-id          "/lol/match/v4/matchlists/by-account/?"
                :time-line           "/lol/match/v4/timelines/by-match/?"
                :tournaments         "/lol/match/v4/matches/by-tournament-code/?/ids"
                :tournament-match    "/lol/match/v4/matches/?/by-tournament-code/?"}
   :spectator  {:ative-game          "/lol/spectator/v4/active-games/by-summoner/?"
                :featured-games      "/lol/spectator/v4/featured-games"}
   :summoner   {:account-id          "/lol/summoner/v4/summoners/by-account/?"
                :name                "/lol/summoner/v4/summoners/by-name/?"
                :puuid               "/lol/summoner/v4/summoners/by-puuid/?"
                :summoner-id         "/lol/summoner/v4/summoners/?"}})

(def ^:private tft-paths
  {:leagues    {:challenger          "/tft/league/v1/challenger"
                :summoner-id         "/tft/league/v1/entries/by-summoner/?"
                :tier-division       "/tft/league/v1/entries/?/?"
                :gran-master         "/tft/league/v1/grandmaster"
                :league              "/tft/league/v1/leagues/?"
                :master              "/tft/league/v1/master"}
   :match      {:puuid               "/tft/match/v1/matches/by-puuid/?/ids"
                :match-data          "/tft/match/v1/matches/?"}
   :summoner   {:account-id          "/tft/summoner/v1/summoners/by-account/?"
                :name                "/tft/summoner/v1/summoners/by-name/?"
                :puuid               "/tft/summoner/v1/summoners/by-puuid/?"
                :summoner-id         "/tft/summoner/v1/summoners/?"}})

(s/defn ^:private get-api-path :- s/Str
  [path-map
   resource :- s/Keyword
   type     :- s/Keyword]
  (get-in path-map [resource type]))

(def ^:private get-lol-paths (partial get-api-path lol-paths))

(def ^:private get-tft-paths (partial get-api-path tft-paths))

(s/defn ^:private prep-summoner [player-nickname :- s/Str]
  (let [prep-nick (logic.commons/formated-nick player-nickname)
        path (get-lol-paths :summoner :name)]
    (get-riot-api path [prep-nick])))

(def summoner-profile (memoize prep-summoner))

(s/defn basic-get-summoner
  [id                    :- s/Keyword
   player-nickname       :- [s/Str]
   api-path-category :- s/Keyword
   filter                :- s/Keyword]
  (let [sumonner-id (get (summoner-profile player-nickname) id)
        path    (get-lol-paths api-path-category filter)]
    (get-riot-api path sumonner-id)))

(def get-summoner-with-summoner-id (partial basic-get-summoner :id))
(def get-summoner-with-account-id  (partial basic-get-summoner :accountId))


(def get-ddgragon-api (partial api-get riot-data-url))

(def ^:private ddragon-paths
   {:versions "/realms/na.json"
    :champion "/cdn/?/data/en_US/champion.json"
    :item     "/cdn/?/data/en_US/item.json"
    :summoner "/cdn/?/data/en_US/summoner.json"})

(defn- filter-version []
   (-> (ddragon-paths :versions)
       (get-ddgragon-api nil)
       :n))

(defn- version [type]
   (get (filter-version) type))

(defn- ddragon-resources [type]
   (let [resource-version [(version type)]
         path         (ddragon-paths (keyword type))]
     (get-ddgragon-api path resource-version)))

(def ^:private ddragon (memoize ddragon-resources))

(def champion (ddragon :champion))

(def item (ddragon :item))

(def summoner (ddragon :summoner))