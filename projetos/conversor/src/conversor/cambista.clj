(ns conversor.cambista
  (:require [clj-http.client :as http-client]
            [cheshire.core :refer [parse-string]]))

(defn- parametrizar-moedas [de para]
  (str de "_" para))

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)
        chave (or (System/getenv "CHAVE_API") "")
        api-url "https://free.currconv.com/api/v7/convert"]
    (-> (:body (http-client/get api-url
                                {:query-params {"q" moedas
                                                "compact" "ultra"
                                                "apiKey" chave}}))
        (parse-string)
        (get-in [moedas]))))