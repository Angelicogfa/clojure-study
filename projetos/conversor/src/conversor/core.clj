(ns conversor.core
  (:gen-class)
  (:require [conversor.formatador :refer [formatar]]
            [conversor.interpretador-de-opcoes :refer [interpretar-opcoes]]
            [conversor.cambista :refer [obter-cotacao]]))

;; Geração da chave
;; https://free.currencyconverterapi.com/free-api-key/

;; Exemplos
;; lein run --de=USD --para=BRL
;; lein run --de=USD --para=GBP

(defn -main
  [& args]
  (let [{:keys [de para]} (interpretar-opcoes args)]
    (-> (obter-cotacao de para)
        (formatar de para)
        (prn))))
