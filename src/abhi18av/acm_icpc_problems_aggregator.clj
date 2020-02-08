(ns abhi18av.acm-icpc-problems-aggregator
  (:use etaoin.api)
  (:require [clojure.edn :as edn]))

;;;;;;;;;


;;=========== Setup the basic configs  =============

(def base-problem-set-url "https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8")

(def config
  (edn/read-string
    (slurp "./config.edn")))

(def driver (firefox {:path-driver  (:path-driver config)
                      :path-browser (:path-browser config)}))


;;=========== Code Body  =============

(go driver base-problem-set-url)


;;=========== Main function  =============

(defn -main
  "This function is the main entry point for the code.
  Just fire up the repl, move to this namespace and execute `(main)`."
  []
  (do
    (go driver base-problem-set-url)
    ))







