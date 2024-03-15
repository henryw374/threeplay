(ns cljs
  (:require [com.widdindustries.tiado-cljs2 :as util]
            [clojure.java.shell :as sh]))

(defn lint []
  (sh/sh "clj-kondo" "--lint" "src"))

(def server-config {:npm-deps {:install false}
                    })


(defn test-watch []
  (util/browser-test-build :watch server-config))

(def out-config
  { :asset-path "/threeplay/cljs-out"
   })

(defn app-cfg [prod?]
  (cond->
    (merge (util/browser-app-config out-config)
      {:target     :esm
       :js-options {:js-provider :import}
       :modules    {:main {:exports {'default 'threeplay.app/init}}}})
    (not prod?)   (update-in [:devtools :preloads] conj 'threeplay.dev-preload)
    prod?         (dissoc :devtools)))

(defn watch-app [prod?]
  (util/watch
    (app-cfg prod?)
    server-config))

(comment

  (watch-app false)
  (util/prod-build  (app-cfg true))
  
(util/stop-server)
  (util/clean-build)
  (util/npm-i {})
  ; start up live-compilation of tests
  (test-watch)
  ; run cljs tests, having opened browser at test page (see print output of above "for tests, open...")
  (util/run-tests)
  ; start a cljs repl session in the test build. :cljs/quit to exit
  (util/repl :browser-test-build)
  (util/repl)
  
  ; run tests in headless browser
  ;(util/compile-and-run-tests-headless* :release)

  (util/stop-server)

  (util/clean-build out-config)
  (util/clean-shadow-cache)

  )
