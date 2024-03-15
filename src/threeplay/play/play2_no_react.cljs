(ns threeplay.play.play2-no-react
  (:require ["three" :as three :refer [Scene PerspectiveCamera WebGLRenderer BoxGeometry MeshBasicMaterial Mesh MeshStandardMaterial]]
            ["@react-three/fiber" :as f]))

(comment
  (do
    (def scene (Scene.))
    (def camera (PerspectiveCamera. 75 (/ js/window.innerWidth js/window.innerHeight),
                  0.1, 1000))
    (def renderer (WebGLRenderer.))

    (.setSize renderer js/window.innerWidth, js/window.innerHeight )
    (.appendChild js/document.body renderer.domElement)

    (def geometry (BoxGeometry. 1 1 1))
    (def material (MeshStandardMaterial. #js{ :color  "orange" }))

    (def cube (Mesh. geometry, material ))
    (.add scene cube )
    (.add scene (new (.-AmbientLight three) 0x404040))
    (def spotlight (new (.-SpotLight three)))
    (.set (.-position spotlight) 50 10 10)
    (set! (.-angle spotlight) 0.15)
    (set! (.-penumbra spotlight) 1)
    (set! (.-decay spotlight) 0)
    (set! (.-intensity spotlight) js/Math.PI)
    
    (.add scene spotlight)
    
    (def pointlight (new (.-PointLight three)))
    (set! (.-decay pointlight) 0)
    (.set (.-position pointlight) -10 -10 -10)
    (set! (.-intensity pointlight) js/Math.PI)
    (.add scene pointlight)

    ;how far away is the camera
    (set! camera.position.z 5)
    (set! cube.rotation.x  1)
    (set! cube.rotation.y  1)
    (.render renderer scene, camera )
    )
  

  
  
  

  ) 
