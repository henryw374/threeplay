(ns threeplay.view
  (:require [clojure.string :as string]
            [threeplay.play.play1 :as play1]
            [threeplay.play.play2-no-react]
            [uix.core :as uix :refer [defui $]]
            [uix.hooks.alpha :as hooks]
            [reitit.core]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(defui home-page [_props]
  ($ :div
    ($ :a {:href (rfe/href ::play1)} "Play - Box")))

(def routes
  (rf/router
    ["/"
     ["" {:name ::frontpage}]
     ["play" {:name ::play1}]]
    ))

(defn start-router [^js cb-holder] ;
  (rfe/start!
    routes
    (fn [new-match]
      (cb-holder new-match)
      #_(swap! match (fn [old-match]
                       (if new-match
                         (assoc new-match :controllers (rfc/apply-controllers (:controllers old-match) new-match))))))
    {:use-fragment true})
  (fn stop []
    ;according to reitit docs doesnt need to do anything
    ))

(defui app-view [_props]
  (let [match-val
        (hooks/use-sync-external-store 
          start-router
          (fn snapshot []
            (reitit.core/match-by-path routes (some-> (str (.. js/window -location -hash))
                                                (string/replace "#" "")))))]
    ($ :div
      (if match-val
          (let [view (:name (:data match-val))]
            (case view
              ::frontpage ($ home-page {:match match-val})
              ::play1 ($ play1/view)
              ))
          ($ home-page)))))
