(ns financeiro.saldo-aceitacao-test
  (:require [clojure.test :refer :all]
            [financeiro.auxiliares :refer :all]
            [cheshire.core	:as	json]))

(deftest test-app
  (testing "O saldo inicial e 0"
    (iniciar-servidor)
    (let [response (conteudo "/saldo")
          status (:status response)
          application-type (get-in response [:headers "Content-Type"])
          body (json/parse-string (:body response) true)]
      (is (= status 200))
      (is (= application-type "application/json; charset=utf-8"))
      (is (= body {:saldo 0})))
    (parar-servidor)))