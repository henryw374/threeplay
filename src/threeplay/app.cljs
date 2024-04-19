(ns threeplay.app
  (:require 
    [threeplay.view :as view]
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))

;(set! (.-x js/window) rdom-client)

(defn app-container [id]
  (js/document.getElementById id))

(defonce root
  (uix.dom/create-root (app-container "app")))

(defn render-root
  "Renders React root into the DOM node."
  [element ^js/ReactDOMRoot root]
  (.render root element))


(defn mount-components [props]
  (render-root
    ($ view/app-view props)
    root))

(def match
  (uix.core/create-context nil))

(defn ^:export  init []
  (js/console.log "Initializing ")
  (mount-components {}))


(.addEventListener
  js/window
  "load"
  (fn []
    (js/console.log "loading...")
    (init)))


(comment
  (def root (js/ReactDOM.createRoot (app-container "app")))
  (.render root (.createElement js/React "div" nil "bye now"))
  )


