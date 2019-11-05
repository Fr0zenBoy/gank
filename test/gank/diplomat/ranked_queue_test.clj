(ns test.gank.diplomat.ranked-queue-test
  (:require [gank.diplomat.commons :as d.commons]
            [gank.diplomat.ranked-queue :as d.ranked]
            [midje.sweet :refer :all]
            [test.gank.diplomat.helpers :as help]))

(fact "Receive summoner ranked data in list by nick-name"
  (help/data-received (d.ranked/summoner-rank "KottoNette")) => "class clojure.lang.LazySeq")