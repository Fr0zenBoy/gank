(ns gank.adapters.summoner
  (:require [gank.diplomat.riot :as riot]
            [gank.logic.summoner :as logic.summoner]
            [schema.core :as s]))

(s/defn match-list :- [s/Int]
  [player-nickname :- s/Str]
  (logic.summoner/matches (riot/summoner-matchs! player-nickname)))