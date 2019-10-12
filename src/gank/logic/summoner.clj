(ns gank.logic.summoner
  (:require [gank.igredients.champions :as data]))


(defn character-id [characters]
  (let [names (->> characters
                   :data
                   (map first))]
    (map #(select-keys (-> characters :data %) [:key :name]) names)))


(character-id data/characters)

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
        matching-map (find-first #(-> % :key (= key)) (character-id data/characters))]
    (merge summoner-mastery matching-map)))

(map match-keys maestria)




