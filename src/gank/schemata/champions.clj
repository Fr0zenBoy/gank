(ns gank.schemata.champions
  (:require [schema.core :as s]))

(def champions-schemata
  [{:key  s/Int
    :name s/Str
    :tags [s/Str]}])

(s/defschema champions champions-schemata)