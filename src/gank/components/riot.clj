(ns gank.components.riot
  (:require [com.stuartsierra.component :as component]
            [gank.protocols.riot :as riot-protocol]
            [gank.riot :as riot-impl])
  (:use [clojure.tools.logging]))

(defn ^:private get-by-nickname [client nickname id resource filter]
  (->> (riot-impl/player-identity client nickname)
       id
       (riot-impl/lol-api client resource filter)))

(defrecord RIOT []
  component/Lifecycle
  (start [this]
    (info "starting the client")
    (assoc this :riot-client (riot-impl/client!)))

  (stop [{:keys [riot-client] :as this}]
    (dissoc this riot-client))
  
  riot-protocol/Riot
  (summoner-identity! [{:keys [riot-client]} summoner-nickname]
    (riot-impl/player-identity riot-client summoner-nickname))

  (summoner-mastery-all-champions! [{:keys [riot-client]} summoner-nickname]
    (get-by-nickname riot-client summoner-nickname :id :mastery :all-champions))

  (summoner-mastery-by-champion-id! [{:keys [riot-client]} summoner-nickname champion-id]
    (let [summoner-id (get (riot-impl/player-identity riot-client summoner-nickname) :id)]
      (riot-impl/lol-api riot-client :mastery :champion-id summoner-id champion-id)))

  (summoner-mastery-total! [{:keys [riot-client]} summoner-nickname]
    (get-by-nickname riot-client summoner-nickname :id :mastery :total-points))

  (summoner-league-position! [{:keys [riot-client]} summoner-nickname]
    (get-by-nickname riot-client summoner-nickname :id :leagues :summoner))

  (summoner-matchs! [{:keys [riot-client]} summoner-nickname]
    (get-by-nickname riot-client summoner-nickname :accountId :match :account-id))

  (match-by-id! [{:keys [riot-client]} match-id]
    (riot-impl/match-data riot-client match-id)))

(defn new-riot []
  (map->RIOT {}))