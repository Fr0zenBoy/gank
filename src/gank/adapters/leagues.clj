(ns gank.adapters.leagues
  (:require [gank.diplomat.riot :as riot]
            [schema.core :as s]))

(s/defn resume-rank :- {s/Keyword s/Any}
  [player-nickname :- s/Str]
  (select-keys (riot/summoner-league-position! player-nickname) [:tier :rank :summonerName :leagueId]))
