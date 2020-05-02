(ns gank.logic.commons
  (:require [clojure.string :as string]))

(defn find-first 
  "Return the first match of the predicate. 
  Example: (find-first even? [1 2 3 4]) => 2" 
  [pred coll]
  (first (filter pred coll)))

(defn contains-many? [m & ks]
  (every? #(contains? m %) ks))

(defn- parse-int [x]
  (Integer/parseInt x))

(defn convert-int-keyval [k m]
  (map #(update % k parse-int) m))

(defn sum-key-value [k m]
  (reduce + (map #(get % k) m)))

(defn format-nick [s]
  (-> s
      (string/trim)
      (string/replace #" " "%20")))