(ns gank.adapters.summoner
  (:require [gank.diplomat.riot :as riot]
            [gank.logic.summoner :as logic.summoner]
            [gank.schemata.summoner :as sc.summoner]
            [schema.core :as s]
            [schema.coerce :as schema]))

(def ^:private number-of-matchs 50)

(s/defn match-list :- [s/Int]
  [player-nickname :- s/Str]
  (logic.summoner/matches (riot/summoner-matchs! player-nickname)))

(s/defn ^:private wins :- [sc.summoner/team]
  [player-nickname :- s/Str
  account-id      :- s/Str]
  (for [match-id-list (take number-of-matchs (match-list player-nickname))
        :let [matchs  (riot/match-by-id match-id-list)
              teams   (logic.summoner/team account-id matchs)]
        :when (-> teams :win (= "Win"))]
  teams))

(s/defn count-wins
  [player-nickname :- s/Str]
  (let [account-id (get (riot/summoner-profile player-nickname) :account-id)
        team-wins  (wins player-nickname account-id)]
    (count team-wins)))