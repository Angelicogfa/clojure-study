(ns financeiro.db-test
  (:require [financeiro.db :as db]
            [clojure.test :refer :all]))

(deftest db-test
  (testing "Limpa dados no db"
    (db/limpar)
    (is (= (count (db/transacoes)) 0)))

  (testing "Inclui um registro no db"
    (let [registro (db/registrar {:valor 10 :tipo "receita"})]
      (is (= (count (db/transacoes)) 1))))

  (testing "saldo é positivo quando só tem receita"
    (db/limpar)
    (db/registrar {:valor 1 :tipo "receita"})
    (db/registrar {:valor 10 :tipo "receita"})
    (db/registrar {:valor 100 :tipo "receita"})
    (db/registrar {:valor 1000 :tipo "receita"})
    (is (= (db/saldo) 1111)))

  (testing "saldo é negativo quando só tem despesas"
    (db/limpar)
    (db/registrar {:valor 2 :tipo "despesa"})
    (db/registrar {:valor 20 :tipo "despesa"})
    (db/registrar {:valor 200 :tipo "despesa"})
    (db/registrar {:valor 2000 :tipo "despesa"})
    (is (= (db/saldo) -2222)))

  (testing "saldo é a soma das receitas menos a soma das despesas"
    (db/limpar)
    (db/registrar {:valor 2 :tipo "despesa"})
    (db/registrar {:valor 10 :tipo "receita"})
    (db/registrar {:valor 200 :tipo "despesa"})
    (db/registrar {:valor 1000 :tipo "receita"})
    (is (= (db/saldo) 808))))

