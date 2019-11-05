(ns test.gank.diplomat.helpers)

(defn data-received [function]
  (-> function
      type
      str))