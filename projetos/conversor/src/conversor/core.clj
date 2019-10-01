(ns conversor.core
  (:gen-class))

(defn -main
  "Nosso proprio main"
  [& args]
  (println "Temos " (count args) "argumentos")
  (println "São eles: " args))
