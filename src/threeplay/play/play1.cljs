(ns threeplay.play.play1
  (:refer-clojure :exclude [Box])
  (:require [uix.core :as uix :refer [defui $]]
            ["@react-three/fiber" :refer [Canvas useFrame]]))

(def fps 60)

(defui Box [props]
  (let [mesh-ref (uix/use-ref)
        [hovered set-hover] (uix/use-state false)
        [active set-active] (uix/use-state false)
        [pos set-pos] (uix/use-state false)]
    (useFrame (fn [_state _clock-delta]
                (set-pos
                  (mod
                    (+ pos (/ (* 2 js/Math.PI) (* fps 10)))
                    (* 2 js/Math.PI)))
                (set! (.. mesh-ref -current -position -y) (js/Math.sin pos))
                (set! (.. mesh-ref -current -position -x) (js/Math.cos pos))
                (set! (.. mesh-ref -current -rotation -z) (* pos (/ 180 js/Math.PI 75)))))
    ($ :mesh
      (assoc props
        :ref mesh-ref
        :scale (if active 1.5 1)
        :on-click (fn [_event] (set-active (not active)))
        :on-pointer-over (fn [_event] (set-hover true))
        :on-pointer-out (fn [_event] (set-hover false)))
      ($ :boxGeometry
        ; size of box
        {:args #js[0.2 0.2 0.2]})
      ($ :meshStandardMaterial
        {:color (if hovered "hotpink" "orange")}))))

(defui view [_props]
  ($ :div {:style { ;:border "solid"
                   :height "400px"}}
    ($ Canvas 
      ($ :ambientLight {:intensity 1})
      ($ :spotLight {:position  #js[50 10 10]
                     :angle     0.15
                     :penumbra  1
                     :decay     0
                     :intensity js/Math.PI})
      ($ :pointLight {:position  #js[-10 -10 -10]
                      :decay     0
                      :intensity 2})
      ($ Box {:position #js[1 0 2]
              :rotation #js[0 1 0]})
      ; ($ Box {:position #js[2.2 0 2]})
      ;($ Box {:position #js[2.2 0 3]})
      ;($ Box {:position #js[2.2 0 2]})
      )))