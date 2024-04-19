(ns threeplay.dev-preload
  (:require [uix.dev])
  )


(uix.dev/init-fast-refresh!)
(js/console.log "inited fast refresh")

(defn ^:dev/after-load on-reload []
  (.clear js/console)
  (js/console.log "refreshed")
   (uix.dev/refresh!)
  )

(comment 
  
  )