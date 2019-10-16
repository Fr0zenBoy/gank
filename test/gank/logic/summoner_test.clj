(ns test.logic.summoner-test
  (:require [gank.logic.summoner :as summoner]
            [midje.sweet :refer :all]))

(def mastery {:championId                   "427"
              :championPointsSinceLastLevel 333821
              :championPoints               355421
              :chestGranted                 true
              :championPointsUntilNextLevel 0
              :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
              :lastPlayTime                 1570937680000
              :tokensEarned                 0
              :championLevel                7})

(def champion {:key  "427"
               :name "Ivern"})

(def masterys [{:championId                   122
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
                :championLevel                7}
               {:championId                   11
                :championPointsSinceLastLevel 211821
                :championPoints               233421
                :chestGranted                 true
                :championPointsUntilNextLevel 0
                :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                :lastPlayTime                 1569806752000
                :tokensEarned                 0
                :championLevel                7}])

(def champions {:type    "champion"
                :format  "standAloneComplex"
                :version "9.20.1"
                :data    {:Ivern        {:tags    ["Support" "Mage"]
                                         :partype "Mana"
                                         :key     "427"
                                         :name    "Ivern"
                                         :title   "the Green Father"
                                         :id      "Ivern"
                                         :info    {:attack     3
                                                   :defense    5
                                                   :magic      7
                                                   :difficulty 7}
                                         :image   {:full   "Ivern.png"
                                                   :sprite "champion1.png"
                                                   :group  "champion"
                                                   :x      0
                                                   :y      48
                                                   :w      48
                                                   :h      48}
                                         :version "9.20.1"
                                         :blurb   "Ivern Bramblefoot, known to many as the Green Father, is a peculiar half man, half tree who roams Runeterra's forests, cultivating life everywhere he goes. He knows the secrets of the natural world, and holds deep friendships with all things that grow..."
                                         :stats   {:attackrange          475
                                                   :attackspeed          0.644
                                                   :mpregenperlevel      0.75
                                                   :mpperlevel           60
                                                   :mpregen              6
                                                   :hpregenperlevel      0.85
                                                   :attackdamageperlevel 3
                                                   :attackspeedperlevel  3.4
                                                   :crit                 0
                                                   :hpperlevel           95
                                                   :movespeed            330
                                                   :spellblockperlevel   1.25
                                                   :armorperlevel        3.5
                                                   :hpregen              7
                                                   :armor                27
                                                   :spellblock           32.1
                                                   :attackdamage         50
                                                   :hp                   585
                                                   :mp                   450
                                                   :critperlevel         0}}
                          :Pantheon     {:tags    ["Fighter" "Assassin"]
                                         :partype "Mana"
                                         :key     "80"
                                         :name    "Pantheon"
                                         :title   "the Unbreakable Spear"
                                         :id      "Pantheon"
                                         :info    {:attack     9
                                                   :defense    4
                                                   :magic      3
                                                   :difficulty 4}
                                         :image   {:full   "Pantheon.png"
                                                   :sprite "champion2.png"
                                                   :group  "champion"
                                                   :x      240
                                                   :y      96
                                                   :w      48
                                                   :h      48}
                                         :version "9.20.1"
                                         :blurb   "Once an unwilling host to the Aspect of War, Atreus survived when the celestial power within him was slain, refusing to succumb to a blow that tore stars from the heavens. In time, he learned to embrace the power of his own mortality, and the stubborn..."
                                         :stats   {:attackrange          175
                                                   :attackspeed          0.644
                                                   :mpregenperlevel      0.45
                                                   :mpperlevel           31
                                                   :mpregen              7.356
                                                   :hpregenperlevel      0.65
                                                   :attackdamageperlevel 3.3
                                                   :attackspeedperlevel  2.95
                                                   :crit                 0
                                                   :hpperlevel           95
                                                   :movespeed            355
                                                   :spellblockperlevel   1.25
                                                   :armorperlevel        3.75
                                                   :hpregen              10
                                                   :armor                40
                                                   :spellblock           28
                                                   :attackdamage         64
                                                   :hp                   580
                                                   :mp                   317.12
                                                   :critperlevel         0}}
                          :Taric        {:tags    ["Support" "Fighter"]
                                         :partype "Mana"
                                         :key     "44"
                                         :name    "Taric"
                                         :title   "the Shield of Valoran"
                                         :id      "Taric"
                                         :info    {:attack     4
                                                   :defense    8
                                                   :magic      5
                                                   :difficulty 3}
                                         :image   {:full   "Taric.png"
                                                   :sprite "champion3.png"
                                                   :group  "champion"
                                                   :x      192
                                                   :y      96
                                                   :w      48
                                                   :h      48}
                                         :version "9.20.1"
                                         :blurb   "Taric is the Aspect of the Protector, wielding incredible power as Runeterra's guardian of life, love, and beauty. Shamed by a dereliction of duty and exiled from his homeland Demacia, Taric ascended Mount Targon to find redemption, only to discover a..."
                                         :stats   {:attackrange          150
                                                   :attackspeed          0.625
                                                   :mpregenperlevel      0.8
                                                   :mpperlevel           60
                                                   :mpregen              8.5
                                                   :hpregenperlevel      0.5
                                                   :attackdamageperlevel 3.5
                                                   :attackspeedperlevel  2
                                                   :crit                 0
                                                   :hpperlevel           90
                                                   :movespeed            340
                                                   :spellblockperlevel   1.25
                                                   :armorperlevel        3.4
                                                   :hpregen              6
                                                   :armor                40
                                                   :spellblock           32.1
                                                   :attackdamage         55
                                                   :hp                   575
                                                   :mp                   300
                                                   :critperlevel         0}}
                          :Heimerdinger {:tags    ["Mage" "Support"]
                                         :partype "Mana"
                                         :key     "74"
                                         :name    "Heimerdinger"
                                         :title   "the Revered Inventor"
                                         :id      "Heimerdinger"
                                         :info    {:attack     2
                                                   :defense    6
                                                   :magic      8
                                                   :difficulty 8}
                                         :image   {:full   "Heimerdinger.png"
                                                   :sprite "champion1.png"
                                                   :group  "champion"
                                                   :x      336
                                                   :y      0
                                                   :w      48
                                                   :h      48}
                                         :version "9.20.1"
                                         :blurb   "A brilliant yet eccentric yordle scientist, Professor Cecil B. Heimerdinger is one of the most innovative and esteemed inventors Piltover has ever known. Relentless in his work to the point of neurotic obsession, he thrives on answering the universe's..."
                                         :stats   {:attackrange          550
                                                   :attackspeed          0.625
                                                   :mpregenperlevel      0.8
                                                   :mpperlevel           20
                                                   :mpregen              8
                                                   :hpregenperlevel      0.55
                                                   :attackdamageperlevel 2.7
                                                   :attackspeedperlevel  1.36
                                                   :crit                 0
                                                   :hpperlevel           87
                                                   :movespeed            340
                                                   :spellblockperlevel   0.5
                                                   :armorperlevel        3
                                                   :hpregen              7
                                                   :armor                19.04
                                                   :spellblock           30
                                                   :attackdamage         55.536
                                                   :hp                   488
                                                   :mp                   385
                                                   :critperlevel         0}}}})

(def format-champions '({:key    "427"
                         :chaves "Ivern"}
                        {:key    "80"
                         :chaves "Pantheon"}
                        {:key    "44"
                         :chaves "Taric"}
                        {:key    "74"
                         :chaves "Heimerdinger"}))

(fact "get key and name data from a map"
      (summoner/character-id champions) => '({:key  "427"
                                              :name "Ivern"}
                                             {:key  "80"
                                              :name "Pantheon"}
                                             {:key  "44"
                                              :name "Taric"}
                                             {:key  "74"
                                              :name "Heimerdinger"}))

(fact "when two maps have the same id or championId the map is unified"
      (summoner/match-keys mastery champion) => {:championId                   "427"
                                                 :championPointsSinceLastLevel 333821
                                                 :championPoints               355421
                                                 :key                          "427"
                                                 :chestGranted                 true
                                                 :championPointsUntilNextLevel 0
                                                 :name                         "Ivern"
                                                 :summonerId                   "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow"
                                                 :lastPlayTime                 1570937680000
                                                 :tokensEarned                 0
                                                 :championLevel                7})

(fact "unify multiple maps when two maps have the same id or championId the map is unified"
      (summoner/player-champions masterys format-champions) => '({:championId 122, :championPointsSinceLastLevel 333821, :championPoints 355421, :key "427", :chestGranted true, :chaves "Ivern", :championPointsUntilNextLevel 0, :summonerId "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow", :lastPlayTime 1570937680000, :tokensEarned 0, :championLevel 7} {:championId 98, :championPointsSinceLastLevel 326638, :championPoints 348238, :key "80", :chestGranted true, :chaves "Pantheon", :championPointsUntilNextLevel 0, :summonerId "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow", :lastPlayTime 1568424677000, :tokensEarned 0, :championLevel 7} {:championId 412, :championPointsSinceLastLevel 270183, :championPoints 291783, :key "44", :chestGranted true, :chaves "Taric", :championPointsUntilNextLevel 0, :summonerId "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow", :lastPlayTime 1569556500000, :tokensEarned 0, :championLevel 7} {:championId 11, :championPointsSinceLastLevel 211821, :championPoints 233421, :key "74", :chestGranted true, :chaves "Heimerdinger", :championPointsUntilNextLevel 0, :summonerId "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow", :lastPlayTime 1569806752000, :tokensEarned 0, :championLevel 7}))