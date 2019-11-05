(ns test.diplomat.champion-test
  (:require [gank.diplomat.champions :as d.champions]
            [gank.diplomat.commons :as d.commons]
            [midje.sweet :refer :all]
            [test.gank.diplomat.helpers :as help]))

(facts "get the current version of each game resource"
  (fact "Gerete current version for Itens"
    (help/data-received (d.champions/version "item" d.champions/riot-data-version)) => "class java.lang.String")

  (fact "Gerete current version for Champions"
    (help/data-received (d.champions/version "champion" d.champions/riot-data-version)) => "class java.lang.String")

  (fact "Gerete current version for Summoner"
    (help/data-received (d.champions/version "summoner" d.champions/riot-data-version)) => "class java.lang.String")

  (fact "Gerete current version for Mastery"
    (help/data-received (d.champions/version "mastery" d.champions/riot-data-version)) => "class java.lang.String")

  (fact "Gerete current version for rune"
    (help/data-received (d.champions/version "rune" d.champions/riot-data-version)) => "class java.lang.String")

  (fact "when the input passed does not match the supported values ​​it will return a stable version for all resources"
    (help/data-received (d.champions/version "iitem" d.champions/riot-data-version)) => "class java.lang.String"))

(fact "receive a json with the champions data"
  (d.champions/champion-data "champion" d.champions/riot-data-version) #(contains? % :data))
