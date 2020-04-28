(defproject gank "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :plugins [[s3-wagon-private "1.3.1"]
            [lein-modules "0.3.11"]
            [lein-ancient "0.6.15"]
            [lein-exec "0.3.6"]
            [lein-environ "1.1.0"]
            [lein-cljfmt "0.5.7"]
            [lein-kibit "0.1.6"]
            [lein-nsorg "0.2.0"]]

  :cljfmt {:indents {flow         [[:block 1]]
                     facts        [[:block 1]]
                     fact         [[:block 1]]
                     as-customer  [[:block 1]]
                     as-of        [[:block 1]]
                     assoc        [[:block 1]]
                     let-entities [[:block 2]]
                     provided     [[:inner 0]]
                     tabular      [[:inner 0]]
                     system-map   [[:block 0]]}}

  :aliases {"lint"        ["do" ["cljfmt" "check"] ["nsorg"]]
            "lint-fix"    ["do" ["cljfmt" "fix"] ["nsorg" "--replace"]]
            "install-all" ["do" ["shell" "../scripts/install"]]}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.5.7"]
                 ;; Remove this line and uncomment one of the next lines to
                 ;; use Immutant or Tomcat instead of Jetty:
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 ;; [io.pedestal/pedestal.immutant "0.5.7"]
                 ;; [io.pedestal/pedestal.tomcat "0.5.7"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.26"]
                 [org.slf4j/jcl-over-slf4j "1.7.26"]
                 [org.slf4j/log4j-over-slf4j "1.7.26"]
                 [clj-http "3.10.0"]
                 [cheshire "5.9.0"]
                 [prismatic/schema "1.1.12"]
                 [com.stuartsierra/component "1.0.0"]
                 [org.clojure/tools.logging "1.1.0"]]
  
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles {:dev     {:aliases      {"run-dev" ["trampoline" "run" "-m" "gank.server/run-dev"]}
                       :dependencies [[io.pedestal/pedestal.service-tools "0.5.7"]
                                      [midje "1.9.9"]]
                       :plugins      [[lein-midje "3.2.1"]]}
             :uberjar {:aot [gank.server]}}
  :main ^{:skip-aot true} gank.server)
