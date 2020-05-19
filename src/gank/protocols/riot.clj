(ns gank.protocols.riot)

(defprotocol Riot

  (summoner-identity! [this player-nickname]
   "Get summoner ids")

  (summoner-mastery-all-champions! [this player-nickname]
    "Get all champion mastery entries sorted by number of champion points descending")

  (summoner-mastery-by-champion-id!
    [this player-nickname champion-id]
    "Get a champion mastery by player ID and champion ID")

  (summoner-mastery-total!
    [this player-nickname]
    "Get a player's total champion mastery score, which is the sum of individual champion mastery levels")

  (summoner-league-position!
    [this player-nickname]
    "Get league entries in all queues for a given summoner ID")

  (summoner-matchs!
    [this player-nickname]
    "Get match list for games played on given account ID and platform ID and filtered using given filter parameters, if any")

  (match-by-id!
    [this match-id]
    "Get match by match ID"))

(def IRiot (:on-interface Riot))