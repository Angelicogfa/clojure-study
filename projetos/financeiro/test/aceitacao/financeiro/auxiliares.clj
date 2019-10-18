(ns financeiro.auxiliares
  (:require [financeiro.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clj-http.client :as http]
            [cheshire.core	:as	json]))

(def porta-padrao 3001)

(def servidor (atom nil))

(defn iniciar-servidor []
  (swap! servidor
         (fn [_]
           (run-jetty app {:port porta-padrao :join? false}))))

(defn parar-servidor []
  (.stop @servidor))

(defn- log-rota [rota]
  (println rota)
  rota)

(defn conteudo-como-json [transacao]
  {:content-type :json
   :body (json/generate-string transacao)
   :throw-exceptions false})

(defn despesa [valor]
  (conteudo-como-json {:valor valor :tipo "despesa"}))

(defn receita [valor]
  (conteudo-como-json {:valor valor :tipo "receita"}))

(defn conteudo [rota]
  (-> (str "http://localhost:" porta-padrao rota)
      (http/get {:throw-exceptions false})))

(defn alterar [rota conteudo]
  (-> (str "http://localhost:" porta-padrao rota)
      (http/post conteudo)))