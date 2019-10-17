(defproject conversor "0.1.0"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [clj-http "3.9.1"]
                 [cheshire "5.8.1"]]
  :main ^:skip-aot conversor.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
