(ns gank.diplomat.champions
  (:require [gank.diplomat.commons :as diplomat.commons]))

(def ^:private riot-data-version ((diplomat.commons/get-ddgragon-api "/realms/na.json") :n))

(defn- version [type]
    (->> type
         keyword
         riot-data-version))

(defn- get-data [type]
  (let [resource-version (version type)
        endpoint (str "/cdn/" resource-version "/data/en_US/champion.json")]
    (diplomat.commons/get-ddgragon-api endpoint)))

(def ^:private riot-data (memoize get-data))

(def champion (riot-data "champion"))

(def sticker (riot-data "sticker"))

(def profileicon (riot-data "profileicon"))

(def item (riot-data "item"))

(def summoner (riot-data "summoner"))

(def language (riot-data "language"))

(def rune (riot-data "rune"))

(def mastery (riot-data "mastery"))

(def game-map (riot-data "map"))