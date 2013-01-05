(ns change.handler
  (:use compojure.core)
  (:use ring.middleware.resource)
  (:use change.nopolabs)
  (:use change.view)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (GET ["/:amt", :amt #"[0-9]+"] [amt]
       (view-change (make-lcm-breadth-first-change (Long. amt))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
