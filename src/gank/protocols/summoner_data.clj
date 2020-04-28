(ns gank.protocols.summoner-data)

(defprotocol SummonerData
  (mastery-all-champions!
    [this resource filter]
    "Get all champion mastery entries sorted by number of champion points descending")
  (mastery-by-champion-id!
    [this champion-id resource filter]
    "Get a champion mastery by player ID and champion ID")
  (mastery-total!
    [this resource filter]
    "Get a player's total champion mastery score, which is the sum of individual champion mastery levels")
  (league-position!
    [this resource filter]
    "Get league entries in all queues for a given summoner ID")
  (matchs!
    [this resource filter]
    "Get matchlist for games played on given account ID and platform ID and filtered using given filter parameters, if any")
  (spectator-current-game! [this resource filter]))

(def ISummonerData (:on-interface SummonerData))
