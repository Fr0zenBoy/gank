(ns gank.adapters.summoner
  (:require [gank.logic.commons :as logic.commons]
            [schema.core :as s]
            [gank.schemata.riot :as sc.riot]
            [gank.schemata.champions :as sc.champions]
            [gank.schemata.summoner :as sc.summoner]))

(s/defn champions->mastery :- sc.summoner/champions-mastery
  [mastery   :- sc.riot/mastery-all-champions
   champions :- sc.champions/champions]
  (let [key          (:championId champions)
        matching-map (logic.commons/find-first #(-> % :key (= key)) mastery)]
    (map #(merge % matching-map) champions)))

(defn champion-main [champions-mastery]
  (apply max-key :championPoints champions-mastery))

(defn summoner->matches [matches]
  (map :gameId (-> matches :matches)))

;TODO filter the result of a matchid