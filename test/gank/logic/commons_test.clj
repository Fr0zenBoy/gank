(ns test.logic.commons-test
  (:require [gank.logic.commons :as commons]
            [midje.sweet :refer :all]))

(def player-mastery [{:championId                   122
                      :championPointsSinceLastLevel 333821
                      :championPoints               355421
                      :chestGranted                 true
                      :championPointsUntilNextLevel 0
                      :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                      :lastPlayTime                 1570937680000
                      :tokensEarned                 0
                      :championLevel                7}
                     {:championId                   98
                      :championPointsSinceLastLevel 326638
                      :championPoints               348238
                      :chestGranted                 true
                      :championPointsUntilNextLevel 0
                      :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                      :lastPlayTime                 1568424677000
                      :tokensEarned                 0
                      :championLevel                7}
                     {:championId                   412
                      :championPointsSinceLastLevel 270183
                      :championPoints               291783
                      :chestGranted                 true
                      :championPointsUntilNextLevel 0
                      :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                      :lastPlayTime                 1569556500000
                      :tokensEarned                 0
                      :championLevel                7}])

(fact "Return the first match of the predicate"
      (commons/find-first even? [1 2 3 4]) => 2)

(fact "Convert data into within a to string"
      (commons/convert-int-str :championId player-mastery) => [{:championId                   "122"
                                                                :championPointsSinceLastLevel 333821
                                                                :championPoints               355421
                                                                :chestGranted                 true
                                                                :championPointsUntilNextLevel 0
                                                                :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                                                                :lastPlayTime                 1570937680000
                                                                :tokensEarned                 0
                                                                :championLevel                7}
                                                               {:championId                   "98"
                                                                :championPointsSinceLastLevel 326638
                                                                :championPoints               348238
                                                                :chestGranted                 true
                                                                :championPointsUntilNextLevel 0
                                                                :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                                                                :lastPlayTime                 1568424677000
                                                                :tokensEarned                 0
                                                                :championLevel                7}
                                                               {:championId                   "412"
                                                                :championPointsSinceLastLevel 270183
                                                                :championPoints               291783
                                                                :chestGranted                 true
                                                                :championPointsUntilNextLevel 0
                                                                :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                                                                :lastPlayTime                 1569556500000
                                                                :tokensEarned                 0
                                                                :championLevel                7}])

(fact "Sum all values ​​of a map and return a single value"
      (commons/sum-key-value :championPoints player-mastery) => 995442)

(facts "formatting a string for searches using summoner name"
       (fact "formatting a string for webrequest"
             (commons/formated-nick "      DROGA É O BRAIÀ ") => "DROGA%20É%20O%20BRAIÀ")
       
       (fact "correct spaces at the end and beginning of a joint string"
             (commons/formated-nick " KottoNette") => "KottoNette")
       
       (fact "doesn't format a string that is already in the ideal pattern"
             (commons/formated-nick "SkyFaII") => "SkyFaII"))