(ns ck.config
  (:require
    [clojure.tools.logging :as log]
    [puppetlabs.trapperkeeper.core :refer [defservice]]
    [puppetlabs.trapperkeeper.services :refer [service-context]]
    [puppetlabs.trapperkeeper.services :as s]))

;; Source/Credits: https://gist.github.com/taylorSando/4bbbe8217ddb2863ae8c

(defprotocol EnvironmentConfigService
  (get-config [this]
    "Returns a map containing all of the configuration values (enviornmentally aware).  Any string value that is
     prefixed by ENV_, will be considered an environmental variable.")
  (get-in-config [this ks] [this ks default]
                 "Returns the individual configuration value from the nested
                 configuration structure, where ks is a sequence of keys.
                 Returns nil if the key is not present, or the default value if
                 supplied."))

(defn- envify-map [m]
  (clojure.walk/postwalk
   (fn [x]
     (if (string? x)
       (if-let [[_ env] (re-find #"ENV_(.*+)" x)]
         (System/getenv env)
         x)
       x))
   m))

(defservice
  config-service EnvironmentConfigService
  [[:ConfigService get-config]]
  (init [this context]
    (assoc context :env-config (envify-map (get-config))))
  (get-config [this]
    (let [context (s/service-context this)]
      (:env-config context)))
  (get-in-config [this ks]
    (let [context (s/service-context this)]
      (get-in (:env-config context) ks)))
  (get-in-config [this ks default]
    (let [context (s/service-context this)]
      (get-in (:env-config context) ks default))))
