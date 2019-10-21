(ns gank.logic.commons)

(defn find-first [pred coll]
  "Return the first match of the predicate.
  Example:
    (find-first even? [1 2 3 4]) => 2
  "
  (first (filter pred coll)))

(defn convert-int-str [key m]
  (map #(assoc % key (-> % (get key) str)) m))

(defn sum-key-value [key m]
  (reduce + (map #(get % key) m)))

(defn formated-nick [nick]
  (-> nick
      (clojure.string/trim)
      (clojure.string/replace #" " "%20")))