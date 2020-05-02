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