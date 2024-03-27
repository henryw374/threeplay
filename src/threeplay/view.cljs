(ns threeplay.view
  (:require ;[threeplay.play.play1 :as play1]
            [threeplay.play.play2-no-react]
            [uix.core :as uix :refer [defui $]]
            [uix.hooks.alpha :as hooks]
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

(defui app-view [{:keys [route-cb initial-match]}]
  (let [[match-val set-val] (uix/use-state initial-match)]
    (hooks/use-effect (fn []
                        (set! (.-cb route-cb) set-val))
      [route-cb])
    ($ :div
      ($ :span {} "hellos")
      (if match-val
        (let [view (:name (:data match-val))]
          (case view
            ::frontpage ($ home-page {:match match-val})
            ;::play1 ($ play1/view)
            ))
        ($ home-page)))))

(defn start-router [^js cb-holder] ;
  (rfe/start!
    routes
    (fn [new-match]
      (.cb cb-holder new-match)
      #_(swap! match (fn [old-match]
                       (if new-match
                         (assoc new-match :controllers (rfc/apply-controllers (:controllers old-match) new-match))))))
    {:use-fragment true})) ;

(comment

  (start-router #js{:cb (fn [x]
                          (js/console.log x))})
  )