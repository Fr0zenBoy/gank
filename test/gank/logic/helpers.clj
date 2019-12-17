(ns test.gank.logic.helpers)

(def champion-data
  {:type "champion",
   :data {:Ivern    {:tags    ["Support" "Mage"],
                     :partype "MP",
                     :key     "427",
                     :name    "Ivern",
                     :title   "the Green Father",
                     :id      "Ivern",
                     :blurb   "Ivern Bramblefoot, known to many as the Green Father, is a peculiar half man, half tree who roams Runeterra's forests, cultivating life everywhere he goes. He knows the secrets of the natural world, and holds deep friendships with all things that grow,..."}
          :Pantheon {:tags    ["Fighter" "Assassin"],
                     :partype "MP",
                     :key     "80",
                     :name    "Pantheon",
                     :title   "the Artisan of War",
                     :id      "Pantheon",
                     :blurb   "''Bring forth one true champion, or a hundred more like you, and then we shall have a battle that will be spoken of until the end of time.''<br><br>The peerless warrior known as Pantheon is a nigh-unstoppable paragon of battle. He was born among the ..."}
          :Taric    {:tags    ["Support" "Fighter"],
                     :partype "MP",
                     :key     "44",
                     :name    "Taric",
                     :title   "the Shield of Valoran",
                     :id      "Taric",
                     :blurb   "''The best weapons are beautiful.''<br><br>Taric is the Aspect of the Protector, wielding incredible power as Runeterra's guardian of life, love, and beauty. Shamed by a dereliction of duty and exiled from his homeland Demacia, Taric ascended Mount ..."}}})

(def summoner-mastery
  [{:championId                   80
    :championPointsSinceLastLevel 338498
    :championPoints               360098
    :chestGranted                 true
    :championPointsUntilNextLevel 0
    :summonerId                   "K5MZTU-aiM6PT9hnixmcJ5PYZQEGZX6FY5cG1rZEMEq_DA"
    :lastPlayTime                 1572718513000
    :tokensEarned                 0
    :championLevel                7}
   {:championId                   44
    :championPointsSinceLastLevel 327547
    :championPoints               349147
    :chestGranted                 true
    :championPointsUntilNextLevel 0
    :summonerId                   "K5MZTU-aiM6PT9hnixmcJ5PYZQEGZX6FY5cG1rZEMEq_DA"
    :lastPlayTime                 1571526713000
    :tokensEarned                 0
    :championLevel                7}
   {:championId                   427
    :championPointsSinceLastLevel 270183
    :championPoints               291783
    :chestGranted                 true
    :championPointsUntilNextLevel 0
    :summonerId                   "K5MZTU-aiM6PT9hnixmcJ5PYZQEGZX6FY5cG1rZEMEq_DA"
    :lastPlayTime                 1569556500000
    :tokensEarned                 0
    :championLevel                7}])

(def champions
  '({:key  "427"
     :name "Ivern"
     :tags ["Support" "Mage"]}
    {:key  "80"
     :name "Pantheon"
     :tags ["Fighter" "Assassin"]}
    {:key  "44"
     :name "Taric"
     :tags ["Support" "Fighter"]}))

