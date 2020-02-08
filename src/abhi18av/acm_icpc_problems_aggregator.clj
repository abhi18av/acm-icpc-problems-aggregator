(ns abhi18av.acm-icpc-problems-aggregator
  (:use etaoin.api)
  (:require [clojure.edn :as edn]))

;;;;;;;;;

(def base-problem-set-url "https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8")

(def config
  (edn/read-string
    (slurp "./config.edn")))

(def driver (firefox {:path-driver  (:path-driver config)
                      :path-browser (:path-browser config)}))

;;;;;;;;;

(go driver base-problem-set-url)







