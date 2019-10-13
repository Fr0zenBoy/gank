(ns gank.logic.summoner
  (:require [gank.diplomat.get-riot-api :as riot]))

(defn character-id [characters]
  (let [names (->> characters
                   :data
                   (map first))]
    (map #(select-keys (-> characters :data %) [:key :name]) names)))

(def maestria '({:championId "427" :level 1}
                {:championId "80" :level 4}))

(defn find-first [pred coll]
  "Return the first match of the predicate.
  Example:
    (find-first even? [1 2 3 4]) => 2
  "
  (first (filter pred coll)))

(defn match-keys [summoner-mastery]
  (let [key (:championId summoner-mastery)
        matching-map (find-first #(-> % :key (= key)) (character-id (riot/champion-data riot/champion-data-url "9.20.1" riot/input-key)))]
    (merge summoner-mastery matching-map)))

(defn player-champions [maestria]
  (map match-keys maestria))