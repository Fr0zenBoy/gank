(ns gank.logic.commons)

(defn find-first [pred coll]
  "Return the first match of the predicate.
  Example:
    (find-first even? [1 2 3 4]) => 2
  "
  (first (filter pred coll)))

(defn contains-many? [m & ks]
  (every? #(contains? m %) ks))

#_(deftest a-test
    (testing "Basic test cases"
      (let [m {:a 1
               :b 1
               :c 2}]
        (is (contains-many? m :a))
        (is (contains-many? m :a :b))
        (is (contains-many? m :a :b :c))
        (is (not (contains-many? m :a :d)))
        (is (not (contains-many? m :a :b :d))))))

(defn convert-int-str [key m]
  (map #(assoc % key (-> % (get key) str)) m))

(defn sum-key-value [key m]
  (reduce + (map #(get % key) m)))

(defn formated-nick [nick]
  (-> nick
      (clojure.string/trim)
      (clojure.string/replace #" " "%20")))