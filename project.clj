(defproject mywebapp "0.1.0-SNAPSHOT"
  :description "change: a little webapp for making change"
  :url "http://change.nopolabs.com"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [com.twinql.clojure/clj-conneg "1.1.0"]
                 [org.clojure/data.json "0.2.0"]
                 [org.clojure/math.numeric-tower "0.0.2"]]
  :plugins [[lein-ring "0.7.5"]]
  :ring {:handler change.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}})
