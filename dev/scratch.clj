(ns scratch)













; 1 + 1 = .... 
(+ 1 1)




(- 5 4)



(str "one plus one is " (+ 1 1 (* 6 5)))

(str (+ 1 1))


(greet "Park Mead")



(defn greet [who]
  (str "hello " who " and everybody else"))












{:date            "2024-12-17"
 :hourly-forecast [
                   {:time "06:00" :temperature -5 :precipitation "light-snow"}
                   {:time "07:00" :temperature -4 :precipitation "heavy-snow"}
                   {:time "08:00" :temperature -3 :precipitation "none"}
                   ]}






(if (todays-weather-is-bad)
  (tell-everyone-the-school-is-closed))
  
  
  
  
