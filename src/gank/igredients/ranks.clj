(ns gank.igredients.ranks)

(defn queues [name]
 (let [nickname (clojure.string/lower-case name)]
   (cond
     (= nickname "soloduo") "RANKED_SOLO_5x5"
     (= nickname "tft") "RANKED_TFT"
     (= nickname "flex") "RANKED_FLEX_SR"
     (= nickname "tftflex") "RANKED_FLEX_TT")))

(defn division [number]
  (cond
    (= number 1) "I"
    (= number 2) "II"
    (= number 3) "III"
    (= number 4) "IV"))

(defn tier [player-tyer]
  (->> player-tyer
       clojure.string/upper-case))


