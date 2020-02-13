(ns abhi18av.acm-icpc-problems-aggregator
  (:use etaoin.api)
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

;;;;;;;;;
;; TODO use stack to implement the DFS
;; https://www.braveclojure.com/java/

;;=========== Setup the basic configs  =============

(def base-problem-set-url "https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8")

(def base-external-pdf-link "https://icpcarchive.ecs.baylor.edu/")

(def config
  (edn/read-string
    (slurp "src/abhi18av/config.edn")))

(def driver (firefox {:path-driver  (:path-driver config)
                      :path-browser (:path-browser config)}))


;;=========== Code Body  =============

(go driver base-problem-set-url)


;;=========== Page-1  =============

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


;;=========== Page-2  =============


(def page2 (rest
             (children driver
                       (query driver {:css "html body table tbody tr td.main table tbody tr td.maincontent table tbody"})
                       {:css "tr"})))



(comment
  (map
    (fn [el] (get-element-text-el driver el))
    page2)


  (get-element-text-el driver
                       (first (children driver
                                        (first page2)
                                        {:tag "a"})))

  (click-el driver
            (first (children driver
                             (first page2)
                             {:tag "a"})))

  '())

;;=========== Page-3  =============

(def page3 (rest
             (children driver
                       (query driver {:css "html body table tbody tr td.main table tbody tr td.maincontent table tbody"})
                       {:css "tr"})))


(comment

  (map
    (fn [el] (let [raw-text (get-element-text-el driver el)
                   text-content (str/split-lines (get-element-text-el driver el))
                   text-content (str/split-lines (get-element-text-el driver el))
                   problem-id (first (butlast (str/split (first text-content) #" ")))
                   ;; REFACTOR the extraction of `-` from the problem name
                   problem-name (str/join " " (rest (rest (butlast (str/split (first text-content) #" ")))))
                   total-submissions (last (str/split (first text-content) #" "))
                   [submissions-solving-percent total-users users-solving-percent] (rest text-content)]
               {:problem-id                  problem-id
                :problem-name                problem-name
                :total-submissions           total-submissions
                :submissions-solving-percent submissions-solving-percent
                :total-users                 total-users
                :users-solving-percent       users-solving-percent}))
    page3)


  (str/split-lines (get-element-text-el driver (first page3)))

  (get-element-text-el driver
                       (second (children driver
                                         (first page3)
                                         {:tag "a"})))

  (get-element-attr driver
                    (second (children driver
                                      (first page3)
                                      {:tag "a"}))
                    "href")






  ;; TODO Figure out why some elements return `nil`
  (map
    (fn [el]
      (second (children driver
                        el
                        {:tag "a"})))
    page3)

  (click-el driver
            (second (children driver
                              (first page3)
                              {:tag "a"})))

  '())




;;=========== Page-4  =============


(def pdf-link (str base-external-pdf-link
                   (get-element-attr-el driver
                                        (query driver
                                               {:css ".maincontent > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(5)"})
                                        :href)))


(comment

  (back driver)


  '())






;;=========== Main function  =============

(defn -main
  "This function is the main entry point for the code.
  Just fire up the repl, move to this namespace and execute `(main)`."
  []
  (do
    (go driver base-problem-set-url)
    ))







