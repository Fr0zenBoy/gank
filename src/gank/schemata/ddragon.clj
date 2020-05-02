(ns gank.schemata.ddragon
  (:require [schema.core :as s]))

(def ddragron-resources-skeleton (s/enum :champion :item :summoner))

(s/defschema ddragon-resource ddragron-resources-skeleton)

(def ddragron-versions-skeleton
  {:sticker     s/Str
   :profileicon s/Str
   :item        s/Str
   :champion    s/Str
   :summoner    s/Str
   :language    s/Str
   :rune        s/Str
   :mastery     s/Str
   :map         s/Str})

(s/defschema ddragron-versions ddragron-versions-skeleton)

#_(def ddragron-chmapions-skeleton
  {:type    s/Str
   :format  s/Str
   :version s/Num
   :data {s/Keyword {:tags    [s/Num]
                     :partype s/Str
                     :key     s/Num
                     :name    s/Str
                     :title   s/Str
                     :id      s/Str
                     :info {:attack     s/Int
                         :defense    s/Int
                         :magic      s/Int
                         :difficulty s/Int}
                  :image {:full      s/Str
                          :sprite    s/Str
                          :group     s/Str
                          :x         s/Int
                          :y         s/Int
                          :w         s/Int
                          :h         s/Int}
                  :version s/Str
                  :blurb   s/Str
                  :stats {:attackrange          s/Int
                          :attackspeed          s/Int
                          :mpregenperlevel      s/Int
                          :mpperlevel           s/Int
                          :mpregen              s/Int
                          :hpregenperlevel      s/Int
                          :attackdamageperlevel s/Int
                          :attackspeedperlevel  s/Int
                          :crit                 s/Int 
                          :hpperlevel           s/Int
                          :movespeed            s/Int
                          :spellblockperlevel   s/Int
                          :armorperlevel        s/Int
                          :hpregen              s/Int
                          :armor                s/Int
                          :spellblock           s/Int
                          :attackdamage         s/Int
                          :hp                   s/Int
                          :mp                   s/Int
                          :critperlevel         s/Int}}}})

;(s/defschema ddragron-champion ddragron-chmapions-skeleton)