(ns financeiro.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [financeiro.handler :refer :all]
            [financeiro.db :as db]
            [cheshire.core	:as	json]))

(deftest test-api
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "O saldo inicial e 0"
    (db/limpar)
    (let [response (app (mock/request :get "/saldo"))
          status (:status response)
          application-type (get-in response [:headers "Content-Type"])
          body (json/parse-string (:body response) true)]
      (is (= status 200))
      (is (= application-type "application/json; charset=utf-8"))
      (is (= body {:saldo 0}))))

  (testing "Registra uma receita no valor de 10"
    (db/limpar)
    (let [response (app (-> (mock/request :post "/transacoes")
                            (mock/json-body {:valor 10 :tipo "receita"})))
          status (:status response)
          body (json/parse-string (:body response) true)]
      (is (= status 201))
      (is (= body {:id 1 :valor 10 :tipo "receita"}))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

