(ns test.diplomat.summoner-data-test
  (:require [gank.diplomat.summoner-data :as s.data]
            [midje.sweet :refer :all]))

(facts "Generate a url compatible with get in riot api"
       (fact "Url for player data based on nickname"
             (s.data/endpoint-summoner "KottoNette") => (str "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/KottoNette?api_key=" s.data/API-KEY))
       (fact "Url for id based player data"
             (s.data/endpoint-summoner-maestry "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow") => (str "https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow?api_key=" s.data/API-KEY))
       (fact "Url for id based player rank data"
             (s.data/endpoint-summoner-rank "pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow") => (str "https://br1.api.riotgames.com/lol/league/v4/entries/by-summoner/pspyxm-GoK9qGIZHFVkg2NAxvWMitxhIHsVf_MgkkHJ6Ow?api_key=" s.data/API-KEY))
       (fact "Url for ranked queue"
             (s.data/endpoint-ranked-queue "RANKED_SOLO_5x5" "CHALLENGER" "I" "1") => (str "https://br1.api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/CHALLENGER/I?page=1&api_key=" s.data/API-KEY)))

(fact "Tests a get for a url and gets a json"
      (s.data/get-riot-api "https://ddragon.leagueoflegends.com/realms/na.json") => {:dd             "9.20.1"
                                                                                     :v              "9.20.1"
                                                                                     :lg             "9.20.1"
                                                                                     :store          nil
                                                                                     :n              {:sticker     "9.20.1"
                                                                                                      :profileicon "9.20.1"
                                                                                                      :item        "9.20.1"
                                                                                                      :champion    "9.20.1"
                                                                                                      :summoner    "9.20.1"
                                                                                                      :language    "9.20.1"
                                                                                                      :rune        "7.23.1"
                                                                                                      :mastery     "7.23.1"
                                                                                                      :map         "9.20.1"}
                                                                                     :css            "9.20.1"
                                                                                     :l              "en_US"
                                                                                     :profileiconmax 28
                                                                                     :cdn            "https://ddragon.leagueoflegends.com/cdn"})

