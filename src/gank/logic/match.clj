(ns gank.logic.match
  (:require [gank.diplomat.match :as diplomat.match]
            [gank.logic.commons :as logic.commons]))

(defn participantIdentities [match-id]
  )

(defn player-identit [accountId match-id]
  (logic.commons/find-first #(= (-> % :player :accountId) accountId) (participantIdentities match-id)))

(defn participant-perform [accountId match-id]
  (let [identit            (get (player-identit accountId match-id) :participantId)
        participants (get (diplomat.match/match-data-memo match-id) :participants)]
    (logic.commons/find-first #(= (-> % :participantId) identit) participants)))

(defn players-perform [match-id]
  (let [participant-identities (get (diplomat.match/match-data-memo match-id) :participantIdentities)
        players (map #(get-in % [:player :accountId]) participant-identities)]
    (map #(participant-perform % match-id) players)))

(defn win-match? [accountId match-id]
  (-> (participant-perform accountId match-id)
      (get-in [:stats :win])
      (true?)))

(defn result-match-list [accountId nick]
  (let [matches    (-> nick diplomat.match/summoner-matchs (get :matches))
        match-list (map :gameId matches)]
    (map #(win-match? accountId %) match-list)))

(defn calc-win-rate [accountId nick]
  (let [m-list (result-match-list accountId nick)
        total (count m-list)
        win-rate (->> m-list (filter true?) count)
        loss (- total win-rate)]
    {:win-rate win-rate
     :loss loss
     :total total}))