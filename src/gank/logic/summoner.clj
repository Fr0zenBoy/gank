(ns gank.logic.summoner
  (:require [gank.logic.commons :as commons]))

(defn character-id [characters]
  (let [names (->> characters
                   :data
                   (map first))]
    (map #(select-keys (-> characters :data %) [:key :name :tags]) names)))

(defn match-keys [summoner-mastery champions-data]
  (let [key          (:championId summoner-mastery)
        matching-map (commons/find-first #(-> % :key (= key)) champions-data)]
    (merge summoner-mastery matching-map)))

(defn player-champions [maestria champions-data]
  (map match-keys maestria champions-data))
