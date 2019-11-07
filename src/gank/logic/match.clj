(ns gank.logic.match
  (:require [gank.diplomat.match :as d.match]
            [gank.logic.commons :as l.commons]))

(defn participants [match-id]
  (get (d.match/match-data-memo match-id) :participants))

(defn participantIdentities [match-id]
  (get (d.match/match-data-memo match-id) :participantIdentities))

(defn player-identit [accountId match-id]
  (l.commons/find-first #(= (-> % :player :accountId) accountId) (participantIdentities match-id)))

(defn participant-perform [accountId match-id]
  (let [identit           (get (player-identit accountId match-id) :participantId)]
    (l.commons/find-first #(= (-> % :participantId) identit) (participants match-id))))

(defn win-match? [accountId match-id]
  (-> (participant-perform accountId match-id)
      (get-in [:stats :win])
      (true?)))

(defn result-match-list [accountId nick]
  (let [matches    (-> nick d.match/summoner-matchs (get :matches))
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