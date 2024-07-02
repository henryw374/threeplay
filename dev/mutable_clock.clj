(ns mutable-clock
  (:import (java.time Clock Duration Instant ZoneId)))

(definterface MutableClock
  (setZone [zone])
  (setInstant [instant]))

(defn mutable-clock [_instant _zone]
  (let [instant-atom (atom _instant) 
        zone-atom (atom _zone)]
    (proxy [Clock mutable_clock.MutableClock] []
      (getZone [] @zone-atom)
      (instant [] @instant-atom)
      (setZone [zone] (reset! zone-atom zone))
      (setInstant [instant] (reset! instant-atom instant)))))

(defn set-zone [mutable-clock zone]
  (.setZone mutable-clock zone))

(defn set-instant [mutable-clock instant]
  (.setInstant mutable-clock instant))

(defn advance-clock [mutable-clock ^Duration duration]
  (set-instant mutable-clock 
    (.plus ^Instant (.instant mutable-clock)  duration)))

(comment
  
  (require '[clojure.reflect :as r])
  (r/reflect MutableClock)

  (def x (-> (mutable-clock (Instant/now)
               (ZoneId/systemDefault))))
  (.instant x)
  (.getZone x)
  (set-zone x (ZoneId/of "Europe/Paris"))
  (set-instant x (Instant/now))
  (advance-clock x (Duration/ofDays 1))
  


  (definterface Foo
    (bar []))

  (-> (proxy [Clock mutable_clock.Foo] []
        (bar [])
        (getZone []))
      (.getZone))
  )