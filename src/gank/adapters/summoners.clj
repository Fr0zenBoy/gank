(ns gank.adapters.summoners
  (:require [gank.logic.commons :as logic.commons]))

(defn champions-resume [chars]
  (let [names   (->> chars :data keys)
        filters (map #(select-keys (-> chars :data %) [:key :name :tags]) names)]
    (logic.commons/convert-int-keyval :key filters)))

(defn match-keys [champions-data summoner-mastery]
  (let [key          (:championId summoner-mastery)
        matching-map (logic.commons/find-first #(-> % :key (= key)) champions-data)]
    (map #(merge % matching-map) summoner-mastery)))