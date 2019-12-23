(ns gank.logic.commons)

(defn find-first [pred coll]
  "Return the first match of the predicate.
  Example:
    (find-first even? [1 2 3 4]) => 2
  "
  (first (filter pred coll)))

(defn contains-many? [m & ks]
  (every? #(contains? m %) ks))

(defn- parse-int [x]
  (Integer/parseInt x))

(defn convert-int-keyval [k m]
  (map #(update % k parse-int) m))

(defn sum-key-value [k m]
  (reduce + (map #(get % k) m)))

(defn formated-nick [nick]
  (-> nick
      (clojure.string/trim)
      (clojure.string/replace #" " "%20")))