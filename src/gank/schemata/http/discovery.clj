(ns gank.schemata.http.discovery
  (:require [schema.core :as s]))

(def lol-resources-skeleton (s/enum :mastery :champions :clash :leagues :lol-status :match :summoner))

(s/defschema lol-resources lol-resources-skeleton)

(def tft-resources-skleton (s/enum :leagues :match :summoner))

(s/defschema tft-resources tft-resources-skleton)

(def ddragon-resources-skeleton (s/enum :versions :champion :item :summoner))

(s/defschema ddragon-resources ddragon-resources-skeleton)