(ns change.handler
  (:use compojure.core)
  (:use ring.middleware.resource)
  (:use change.nopolabs)
  (:use change.view)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.data.json :as json]
            [com.twinql.clojure.conneg :as conneg]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/write-str data)})

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (GET ["/:amt", :amt #"[0-9]+"] { params :params headers :headers }
    (let [amt (Long. (:amt params))
          accept-header (get headers "accept")
          content-type (conneg/best-allowed-content-type accept-header #{"application/json" "text/html"})
          change (make-lcm-breadth-first-change amt)]
      (if (= ["application" "json"] content-type)
        (json-response change)
        (view-change change))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
