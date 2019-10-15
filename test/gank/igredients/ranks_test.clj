(ns gank.test.ingredients.ranks-test
  (:require [gank.igredients.ranks :as ranks]
            [midje.sweet :refer :all]))

(facts "Generates a string compatible with riot api for queries in ranks"
       (fact "generate a string for queue: Ranked 5x5 solo/duo"
             (ranks/queues "soloduo") => "RANKED_SOLO_5x5")
       (fact "generate a string for queue: Ranked TFT"
             (ranks/queues "tft") => "RANKED_TFT")
       (fact "generate a string for queue: Ranked 5x5 flex"
             (ranks/queues "flex") => "RANKED_FLEX_SR")
       (fact "generate a string for queue: Ranked TFT FLEX"
             (ranks/queues "tftflex") => "RANKED_FLEX_TFT"))

(facts "Generates a string compatible with riot api for queries in division"
       (fact "generate a string for division: I"
             (ranks/division 1) => "I")
       (fact "generate a string for division: II"
             (ranks/division 2) => "II")
       (fact "generate a string for division: III"
             (ranks/division 3) => "III")
       (fact "generate a string for division: IV"
             (ranks/division 4) => "IV"))

(fact "change string to capital letters"
      (ranks/tier "gold") => "GOLD")