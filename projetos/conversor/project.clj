(defproject conversor "0.1.0"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot conversor.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
