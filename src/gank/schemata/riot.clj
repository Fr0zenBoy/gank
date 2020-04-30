(ns gank.schemata.riot
  (:require [schema.core :as s]))

(def summoner-ids-skeleton (s/enum :accountId :id :puuid))

(s/defschema summoner-ids summoner-ids-skeleton)

(def summoner-filters-skeleton (s/enum :all-champions :total-points :summoner :active-game :account-id))

(s/defschema summoner-filters summoner-filters-skeleton)

(def summoner-data-skeleton
  {:id            s/Str
   :account-id    s/Str
   :puuid         s/Str
   :name          s/Str
   :profileIconID s/Int
   :revisionDate  Long
   :summonerLevel Long})

(s/defschema summoner-data summoner-data-skeleton)

(def mastery-all-champions-skeleton
  [{:championId                   Long
    :championLevel                s/Int
    :championPoints               s/Int
    :lastPlayTime                 Long
    :championPointsSinceLastLevel Long
    :championPointsUntilNextLevel Long
    :chestGranted                 s/Bool
    :tokensEarned                 s/Int
    :summonerId                   s/Str}])

(s/defschema mastery-all-champions mastery-all-champions-skeleton)

(def mastery-by-champion-id-skeleton
  {:championId                   Long
   :championPointsSinceLastLevel Long
   :championPoints               s/Int
   :chestGranted                 s/Bool
   :championPointsUntilNextLevel Long
   :summonerId                   s/Str
   :lastPlayTime                 Long
   :tokensEarned                 s/Int
   :championLevel                s/Int})

(s/defschema mastery-by-champion-id mastery-by-champion-id-skeleton)

(def league-position-skeleton
  [{:leagueId     s/Str
    :queueType    s/Str
    :tier         s/Str
    :rank         s/Str
    :summonerId   s/Int
    :summonerName s/Str
    :leaguePoints s/Int
    :wins         s/Int
    :losses       s/Int
    :veteran      s/Bool
    :inactive     s/Bool
    :freshBlood   s/Bool
    :hotStreak    s/Bool
    (s/optional-key :miniSeries) {:losses   s/Int
                                  :progress s/Str
                                  :target   s/Int
                                  :wins     s/Int}}])

(s/defschema league-position league-position-skeleton)

(def matchs-skeleton
  {(s/required-key :matches) [{:platformId s/Str
                               :gameId     Long
                               :champion   s/Int
                               :queue      s/Int
                               :season     s/Int
                               :timestamp  Long
                               :role       s/Str
                               :lane       s/Str}]})

(s/defschema matchs matchs-skeleton)