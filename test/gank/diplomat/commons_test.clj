(ns test.gank.diplomat.commons-test
  (:require [gank.diplomat.commons :as d.commons]
            [gank.logic.commons :as l.commons]
            [midje.sweet :refer :all]))

(def endpoint (str "https://br1.api.riotgames.com/lol/platform/v3/champion-rotations" d.commons/input-key))

(fact "Simple get in Riot API"
  (let [response (d.commons/get-riot-api endpoint)]
    (l.commons/contains-many? response :freeChampionIds :freeChampionIdsForNewPlayers :maxNewPlayerLevel)))