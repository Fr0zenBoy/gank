(ns test.diplomat.champion-data-test
  (:require [gank.diplomat.champion-data :as c.data]
            [gank.diplomat.summoner-data :as s.data]
            [midje.sweet :refer :all]))

(fact "Generate a url compatible with get in riot-data api with the version of resources"
      (c.data/url-champion-data "9.20.1") => (str "http://ddragon.leagueoflegends.com/cdn/9.20.1/data/en_US/champion.json?api_key=" s.data/API-KEY))

(facts "get the current version of each game resource"
       (fact "Gerete current version for Itens"
             (c.data/version "item" c.data/riot-data-version) => "9.20.1")
       (fact "Gerete current version for Champions"
             (c.data/version "champion" c.data/riot-data-version) => "9.20.1")
       (fact "Gerete current version for Summoner"
             (c.data/version "summoner" c.data/riot-data-version) => "9.20.1")
       (fact "Gerete current version for Mastery"
             (c.data/version "mastery" c.data/riot-data-version) => "7.23.1")
       (fact "Gerete current version for rune"
             (c.data/version "rune" c.data/riot-data-version) => "7.23.1")
       (fact "when the input passed does not match the supported values ​​it will return a stable version for all resources"
             (c.data/version "iitem" c.data/riot-data-version) => "6.24.1"))

(fact "receive a json with the champions data"
      (contains? (c.data/champion-data "champion" c.data/riot-data-version) :data) => true)
