(ns gank.schemata.summoner
  (:require [schema.core :as s]))

(def champions-mastery-skeleton
  [{:championId                   s/Int
    :tags                         [s/Str]
    :championPointsSinceLastLevel s/Int
    :championPoints               s/Int
    :key                          s/Num
    :chestGranted                 s/Bool
    :championPointsUntilNextLevel s/Int
    :name                         s/Str
    :summonerId                   s/Str
    :lastPlayTime                 s/Int
    :tokensEarned                 s/Int
    :championLevel                s/Int}])

(s/defschema champions-mastery champions-mastery-skeleton)

(def identity-skeleton
  {:participantId s/Int
   :player {:platformId s/Str
            :accountId s/Str
            :summonerName s/Str
            :summonerId s/Str
            :currentPlatformId s/Str
            :currentAccountId s/Str
            :matchHistoryUri s/Str
            :profileIcon s/Int}})

(s/defschema identity identity-skeleton)

(def perform-skeleton
  ;TODO Improve this schema
  {s/Keyword s/Any})

(s/defschema perform perform-skeleton)

(def team-skeleton
  {:inhibitorKills  s/Int
   :teamId          s/Int
   :firstBaron      s/Bool
   :vilemawKills    s/Int
   :riftHeraldKills s/Int
   :bans            [s/Int]
   :firstBlood      s/Bool
   :firstTower      s/Bool
   :dominionVictoryScore s/Int
   :baronKills      s/Int
   :towerKills      s/Int
   :firstDragon     s/Bool
   :dragonKills     s/Int
   :firstInhibitor  s/Bool
   :firstRiftHerald s/Bool
   :win             s/Int})

(s/defschema team team-skeleton)