(ns financeiro.saldo-aceitacao-test
  (:require [clojure.test :refer :all]
            [financeiro.auxiliares :refer :all]
            [financeiro.db :as db]
            [cheshire.core	:as	json]
            [clj-http.client :as client]))

(deftest test-app
  (testing "O saldo inicial e 0"
    (iniciar-servidor)
    (db/limpar)
    (let [response (conteudo "/saldo")
          status (:status response)
          application-type (get-in response [:headers "Content-Type"])
          body (json/parse-string (:body response) true)]
      (is (= status 200))
      (is (= application-type "application/json;charset=utf-8"))
      (is (= body {:saldo 0})))
    (parar-servidor))

  (testing "O saldo da receita é 10 quando a unica transação da receita é 10"
    (iniciar-servidor)
    (db/limpar)
    (let [response (alterar "/transacoes" (receita 10))
          status (:status response)
          result (conteudo "/saldo")
          status-response (:status result)
          body (json/parse-string (:body result) true)]
      (is (= status 201))
      (is (= status-response 200))
      (is (= body {:saldo 10})))
    (parar-servidor))

  (testing "O saldo é 1000 quando criamos duas receitas de 2000 e uma despesa de 3000"
    (iniciar-servidor)
    (db/limpar)
    (let [_ (alterar "/transacoes" (receita 2000))
          _ (alterar "/transacoes" (receita 2000))
          _ (alterar "/transacoes" (despesa 3000))
          response (conteudo "/saldo")
          body (json/parse-string (:body response) true)]
      (is (= body {:saldo 1000})))))