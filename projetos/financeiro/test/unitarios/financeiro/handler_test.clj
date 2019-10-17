(ns financeiro.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [financeiro.handler :refer :all]
            [cheshire.core	:as	json]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "O saldo inicial e 0"
    (let [response (app (mock/request :get "/saldo"))
          status (:status response)
          application-type (get-in response [:headers "Content-Type"])
          body (json/parse-string (:body response) true)]
      (is (= status 200))
      (is (= application-type "application/json; charset=utf-8"))
      (is (= body {:saldo 0}))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
