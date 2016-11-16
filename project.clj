(defproject ck.config "0.1.0"
  :description "Configuration module for Conskit that adds environment variable support to trapperkeeper"
  :url "https://github.com/conskit/ck.config"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [conskit "1.0.0-rc1"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[puppetlabs/trapperkeeper "1.4.1" :classifier "test"]
                                  [puppetlabs/kitchensink "1.3.1" :classifier "test" :scope "test"]
                                  [midje "1.8.3"]]
                   :plugins [[lein-midje "3.2"]]}})
