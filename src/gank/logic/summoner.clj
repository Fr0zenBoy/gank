(ns gank.logic.summoner
  (:require [schema.core :as s]
            [gank.logic.commons :as logic.commons]
            [gank.schemata.summoner :as sc.summoner]
            [gank.schemata.champions :as sc.champions]
            [gank.schemata.riot :as sc.riot]))

(s/defn champions->mastery :- sc.summoner/champions-mastery
  [mastery   :- sc.riot/mastery-all-champions
   champions :- sc.champions/champions]
  (let [key          (:championId champions)
        matching-map (logic.commons/find-first #(-> % :key (= key)) mastery)]
    (map #(merge % matching-map) champions)))

(defn champion-main [champions-mastery]
  (apply max-key :championPoints champions-mastery))

(defn matches [matches]
  (map :gameId (-> matches :matches)))

(s/defn identity :- sc.summoner/identity
  [account-id :- s/Str
   match]
  (let [participants (get match :participantIdentities)]
    (logic.commons/find-first #(-> % :player :accountId (identical? account-id)) participants)))

(s/defn perform :- sc.summoner/perform
  [account-id :- s/Str
   match]
  (let [identity (get (identity account-id match) :participantId)
        participants (get match :participants)]
    (logic.commons/find-first #(-> % :participantId (identical? identity)) participants)))

(s/defn team :- sc.summoner/team
  [account-id :- s/Str
   match]
  (let [perform    (get (perform account-id match) :teamId)
        teams      (get match :teams)]
    (logic.commons/find-first #(-> % :teamId (= perform)) teams)))
