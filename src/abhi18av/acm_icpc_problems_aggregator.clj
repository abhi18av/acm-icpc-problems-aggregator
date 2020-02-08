(ns abhi18av.acm-icpc-problems-aggregator
  (:use etaoin.api)
  (:require [clojure.edn :as edn]))

;;;;;;;;;


;;=========== Setup the basic configs  =============

(def base-problem-set-url "https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8")

(def config
  (edn/read-string
    (slurp "src/abhi18av/config.edn")))

(def driver (firefox {:path-driver  (:path-driver config)
                      :path-browser (:path-browser config)}))


;;=========== Code Body  =============

(go driver base-problem-set-url)

(def archives (rest
                (children driver
                          (query driver {:css "html body table tbody tr td.main table tbody tr td.maincontent table tbody"})
                          {:css "tr"})))



(comment
  (map
    (fn [el] (get-element-text-el driver el))
    archives)


  (get-element-text-el driver
                       (first (children driver
                                        (first archives)
                                        {:tag "a"})))

  (click-el driver
            (first (children driver
                             (first archives)
                             {:tag "a"})))



  '())





;;=========== Main function  =============

(defn -main
  "This function is the main entry point for the code.
  Just fire up the repl, move to this namespace and execute `(main)`."
  []
  (do
    (go driver base-problem-set-url)
    ))







