(ns gank.logic.date-time
  (:require [clj-time.coerce :as c]
            [clj-time.core :as t]
            [clj-time.format :as f]))

(defn convert-data-timestemp [yy mm dd]
  (c/to-long (t/date-time yy mm dd)))

(defn convert-timestemp-data [long]
  (f/unparse (f/formatters :date) (clj-time.coerce/from-long long)))

(defn convert-timestemp-time [long]
  (f/unparse (f/formatters :date-hour-minute-second) (clj-time.coerce/from-long long)))
