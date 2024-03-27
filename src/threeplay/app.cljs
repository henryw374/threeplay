(ns threeplay.app
  (:require ;[threeplay.view :as view]
            [uix.core :as uix :refer [defui $]]
            ["react-dom/client" :as rdom-client]))

(defn app-container [id]
  (js/document.getElementById id))

(defn create-root
  "Create a React root for the supplied container and return the root.

  See: https://reactjs.org/docs/react-dom-client.html#createroot"
  ([node]
   (rdom-client/createRoot node))
  ([node {:keys [on-recoverable-error identifier-prefix] :as options}]
   (rdom-client/createRoot node #js {:onRecoverableError on-recoverable-error
                                     :identifierPrefix identifier-prefix})))

(defonce root
  (create-root (app-container "app")))

(defn render-root
  "Renders React root into the DOM node."
  [element ^js/ReactDOMRoot root]
  (.render root element))


(defn mount-components [props]
  (render-root
    ($ :div "hel")
    ;($ view/app-view props)
    root))

(def match
  (uix.core/create-context nil))

(defn ^:export  init []
  (js/console.log "Initializing")
  #_(let [initial-match (atom nil)
        route-cb #js{:cb (fn [match]
                           (reset! initial-match match))}]
    (view/start-router route-cb))
  (mount-components {} #_{:route-cb      route-cb
                     :initial-match @initial-match}))




