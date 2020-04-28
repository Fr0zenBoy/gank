(ns gank.records.summoner-data
  (:require [gank.diplomat.http-out :as http-out]
            [com.stuartsierra.component :as component]
            [gank.protocols.summoner-data :as sd-pro]
            [schema.core :as s]
            [clojure.tools.logging :as log]))


(s/defrecord SummonerData []
  component/Lifecycle
  (start [this]
    (log/debug "start SummonerData component")
    this)

  (stop [this]
    (log/debug "stop SummonerData component")
   this)

  sd-pro/SummonerData
  (mastery-all-champions! [_ player-nickname :- [s/Str]]
    (http-out/get-summoner-with-account-id player-nickname :mastery :all-champions))

  (mastery-by-champion-id! [_]
    (http-out/get-summoner-with-account-id)))
