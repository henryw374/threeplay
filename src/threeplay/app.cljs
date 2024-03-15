(ns threeplay.app
  (:require [threeplay.view :as view]
            [uix.core :as uix :refer [defui $]]
            [uix.dom]))

(defn app-container [id]
  (js/document.getElementById id))

(defonce root
  (uix.dom/create-root (app-container "app")))

(defn mount-components [props]
  (uix.dom/render-root
    ($ view/app-view props)
    root))

(def match
  (uix.core/create-context nil))

(defn ^:export  init []
  (js/console.log "Initializing")
  (let [initial-match (atom nil)
        route-cb #js{:cb (fn [match]
                           (reset! initial-match match))}]
    (view/start-router route-cb)
    (mount-components {:route-cb route-cb
                       :initial-match @initial-match})))




