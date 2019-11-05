(ns test.logic.commons-test
  (:require [gank.logic.commons :as l.commons]
            [midje.sweet :refer :all]))

(fact "Return the first match of the predicate"
  (l.commons/find-first even? [1 2 3 4]) => 2)

(facts "checks if the entered keywords are present within the data structure"
  (fact "Basic test cases"
    (let [m {:a 1
             :b 1
             :c 2}]
      (l.commons/contains-many? m :a) => true
      (l.commons/contains-many? m :a :b) => true
      (l.commons/contains-many? m :a :b :c) => true
      (not (l.commons/contains-many? m :a :d)) => true
      (not (l.commons/contains-many? m :a :b :d)) => true)))

(fact "Change the value of all keywords in a list of integer maps to string"
  (let [m [{:day 1} {:day 2} {:day 3}]]
    (l.commons/convert-int-str :day m)) => '({:day "1"} {:day "2"} {:day "3"}))

(fact "add all keys of the same name within a map and return a numeric value"
  (let [m [{:a 1
            :b 2
            :c 3}
           {:a 1
            :b 2
            :c 3}]]
    (l.commons/sum-key-value :a m) => 2
    (l.commons/sum-key-value :b m) => 4
    (l.commons/sum-key-value :c m) => 6))

(facts "formatting a string for searches using summoner name"
  (fact "formatting a string for webrequest"
    (l.commons/formated-nick "      DROGA É O BRAIÀ ") => "DROGA%20É%20O%20BRAIÀ")

  (fact "correct spaces at the end and beginning of a joint string"
    (l.commons/formated-nick " KottoNette ") => "KottoNette")

  (fact "doesn't format a string that is already in the ideal pattern"
    (l.commons/formated-nick "SkyFaII") => "SkyFaII"))