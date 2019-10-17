(ns conversor.interpretador-de-opcoes
  (:require [clojure.tools.cli :refer [parse-opts]]))

(defn interpretar-opcoes [argumentos]
  (let [opcoes (vector ["-d" "--de moeda base" "moeda base para conversão" :default "USD"]
                       ["-p" "--para moeda destino" "moeda a qual queremos saber o valor" :default "BRL"])]
    (:options (parse-opts argumentos opcoes))))
