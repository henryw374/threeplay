(ns threeplay.dev-preload
  (:require [uix.dev])
  )


(uix.dev/init-fast-refresh!)
(js/console.log "inited fast refresh")

(defn ^:dev/after-load on-reload []
  (.clear js/console)
  (js/console.log "refreshed")
   (uix.dev/refresh!)
  ;(re-frame.core/clear-subscription-cache!)
  ;(rdom/unmount-component-at-node (app-container "app"))
  ;(mount-components)
  )

(comment 
  
  )