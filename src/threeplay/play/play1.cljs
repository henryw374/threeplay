(ns threeplay.play.play1
  (:refer-clojure :exclude [Box])
  (:require [uix.core :as uix :refer [defui $]]
            ["@react-three/fiber" :refer [Canvas useFrame]]))

(defui Box [props]
  (let [mesh-ref (uix/use-ref)
        [hovered set-hover] (uix/use-state false)
        [active set-active] (uix/use-state false)]
    (useFrame (fn [_state clock-delta]
                (let [curr (.. mesh-ref -current -rotation -x)]
                  ;(js/console.log "delta" clock-delta)
                  (set! (.. mesh-ref -current -rotation -x) (+ clock-delta curr)))))
    ($ :mesh
      (assoc props
        :ref mesh-ref
        :scale (if active 1.5 1)
        :on-click (fn [_event] (set-active (not active)))
        :on-pointer-over (fn [_event] (set-hover true))
        :on-pointer-out (fn [_event] (set-hover false)))
      ($ :boxGeometry
        {:args #js[1 1 1]})
      ($ :meshStandardMaterial
        {:color (if hovered "hotpink" "orange")}))))

(defui view [_props]
  ($ :div
    ($ Canvas
      ($ :ambientLight {:intensity 1})
      ($ :spotLight {:position  #js[50 10 10]
                     :angle     0.15
                     :penumbra  1
                     :decay     0
                     :intensity js/Math.PI})
      ($ :pointLight {:position  #js[-10 -10 -10]
                      :decay     0
                      :intensity js/Math.PI})
      ; where is the box in the screen. x y z
      ($ Box {:position #js[1.2 0 2]}))))
