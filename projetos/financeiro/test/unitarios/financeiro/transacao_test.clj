(ns financeiro.transacao-test
  (:require [clojure.test :refer :all]
            [financeiro.transacoes :refer [valida?]]))

(deftest valida-transacao
  (testing "Uma transacao sem valor não é valida"
    (is (= (valida? {:tipo "receita"}) false)))

  (testing "Uma transação com valor negativo não é valido"
    (is (= (valida? {:valor -10 :tipo "despesa"}) false)))

  (testing "Uma transação com valor não numérico não é valido"
    (is (= (valida? {:valor "123" :tipo "receita"}) false)))

  (testing "Uma transação sem tipo não é valido"
    (is (= (valida? {:valor 1}) false)))

  (testing "Uma transação com tipo desconhecido não é valido"
    (is (= (valida? {:valor 10 :tipo "investimento"}) false)))

  (testing "Uma transação com valor numérico positivo e com tipo conhecido é valido"
    (is (= (valida? {:valor 10 :tipo "despesa"}) true))))