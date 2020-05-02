(ns gank.diplomat.riot
  (:require [gank.diplomat.http.out :as http-out]
            [gank.diplomat.http.discovery :as discovery]
            [schema.core :as s]
            [gank.logic.commons :as logic.commons]
            [gank.schemata.riot :as sc.riot]
            [gank.schemata.http.discovery :as sc.discovery]))

(s/defn ^:private prep-summoner-profile! :- sc.riot/summoner-data
  [player-nickname :- s/Str]
  (let [formated-nick (logic.commons/format-nick player-nickname)
        endpoints (discovery/get-lol-endpoints :summoner :name)]
    (http-out/get-riot-api endpoints formated-nick)))

(def summoner-profile (memoize prep-summoner-profile!))

(s/defn basic-get-summoner! :- sc.riot/summoner-data
        [id                     :- sc.riot/summoner-ids
         api-endpoints-category :- sc.discovery/lol-resources
         filter                 :- sc.riot/summoner-filters
         player-nickname        :- s/Str
         args                   :- s/Any]
        (let [summoner-id (get (summoner-profile player-nickname) id)
              endpoints   (discovery/get-lol-endpoints api-endpoints-category filter)]
          (http-out/get-riot-api endpoints summoner-id args)))

(def get-summoner-with-summoner-id! (partial basic-get-summoner! :id))
(def get-summoner-with-account-id!  (partial basic-get-summoner! :accountId))

(s/defn summoner-mastery-all-champions! :- sc.riot/mastery-all-champions
  "Get all champion mastery entries sorted by number of champion points descending"
  [player-nickname :- s/Str]
  (get-summoner-with-summoner-id! :mastery :all-champions player-nickname nil))

(s/defn summoner-mastery-by-champion-id :- sc.riot/mastery-by-champion-id
  "Get a champion mastery by player ID and champion ID"
  [player-nickname :- s/Str
   champion-id     :- Long]
  (get-summoner-with-summoner-id! :mastery :spefic-champion player-nickname champion-id))

(s/defn summoner-mastery-total :- s/Int
  "Get a player's total champion mastery score, which is the sum of individual champion mastery levels"
  [player-nickname :- s/Str]
  (get-summoner-with-summoner-id! :mastery :total-points player-nickname nil))

(s/defn summoner-league-position! :- sc.riot/league-position
  "Get league entries in all queues for a given summoner ID"
  [player-nickname :- s/Str]
  (get-summoner-with-summoner-id! :leagues :summoner player-nickname nil))

(s/defn summoner-matchs! :- sc.riot/matchs
  "Get match list for games played on given account ID and platform ID and filtered using given filter parameters, if any"
  [player-nickname :- s/Str]
  (get-summoner-with-account-id! :match :account-id player-nickname nil))
