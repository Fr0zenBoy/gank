(ns gank.adapters.champions
  (:require [gank.logic.commons :as logic.commons]
            [gank.diplomat.ddragon :as ddragon]
            [schema.core :as s]))

(s/defn ^:private champion-names :- [s/Keyword]
  [champions] ;Todo create champions map schema
  (-> champions :data keys))

(s/defn ^:private select-data
  [champions]
  (map #(select-keys (-> champions :data %) [:key :name :tags]) (champion-names champions)))

(s/defn champions-resume [champions]
  (logic.commons/convert-int-keyval :key (select-data champions)))
