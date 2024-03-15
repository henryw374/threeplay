(ns threeplay.play.play1
  (:require [uix.core :as uix :refer [defui $]]))

(defui view [_props]
  ($ :div {:style {:display        "flex"
                   :flex-direction "column"
                   :align-items    "center"}}
    "play1"))
