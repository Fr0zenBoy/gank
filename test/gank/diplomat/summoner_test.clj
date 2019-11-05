(ns test.diplomat.summoner-test
  (:require [gank.diplomat.commons :as d.commons]
            [gank.diplomat.summoner :as d.summoner]
            [midje.sweet :refer :all]))

(facts "Generate a url compatible with get in riot api"
  (fact "Url for player data based on nickname"
    (d.summoner/endpoint-summoner "KottoNette") =>
    (str
     "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/KottoNette?api_key="
     d.commons/API-KEY))

  (fact "create correct url even when nickname has characters not accepted"
    (d.summoner/endpoint-summoner "DROGA É O BRAIÀ") =>
    (str
     "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/DROGA%20É%20O%20BRAIÀ?api_key="
     d.commons/API-KEY)))

