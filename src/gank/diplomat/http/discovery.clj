(ns gank.diplomat.http.discovery
  (:require [schema.core :as s]))

(def ^:private lol-endpoints
  {:mastery    {:all-champions       "/lol/champion-mastery/v4/champion-masteries/by-summoner/?"
                :champion-id         "/lol/champion-mastery/v4/champion-masteries/by-summoner/?/by-champion/?"
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
   :spectator  {:active-game         "/lol/spectator/v4/active-games/by-summoner/?"
                :featured-games      "/lol/spectator/v4/featured-games"}
   :summoner   {:account-id          "/lol/summoner/v4/summoners/by-account/?"
                :name                "/lol/summoner/v4/summoners/by-name/?"
                :puuid               "/lol/summoner/v4/summoners/by-puuid/?"
                :summoner-id         "/lol/summoner/v4/summoners/?"}})

(def ^:private tft-endpoints
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

(def ^:private ddragon-endpoints
  {:versions "/realms/na.json"
   :champion "/cdn/?/data/en_US/champion.json"
   :item     "/cdn/?/data/en_US/item.json"
   :summoner "/cdn/?/data/en_US/summoner.json"})

(s/defn ^:private get-api-endpoints :- s/Str
  [endpoints-map
   resource :- s/Keyword
   filter   :- s/Keyword]
  (get-in endpoints-map [resource filter]))

(def get-lol-endpoints (partial get-api-endpoints lol-endpoints))

(def get-tft-endpoints (partial get-api-endpoints tft-endpoints))

(s/defn get-ddragon-endpoints
  [resource :- s/Keyword]
  (get ddragon-endpoints resource))

