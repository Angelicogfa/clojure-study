(def transacoes [{:valor 33.0 :tipo "despesa" :comentario "Almoço" :moeda "R$" :data "19/11/2016"}
                 {:valor 2700.0 :tipo "receita" :comentario "Bico" :moeda "R$" :data "01/06/2016"}
                 {:valor 29.0 :tipo "despesa" :comentario "livro de clojure" :moeda "R$" :data "03/12/2016"}])
transacoes

(defn valor-sinalizado [transacao]
  (if (= (:tipo transacao) "despesa")
    (str (:moeda transacao) " -" (:valor transacao))
    (str (:moeda transacao) " +" (:valor transacao))))

(valor-sinalizado (first transacoes))
(valor-sinalizado (second transacoes))
(valor-sinalizado (last transacoes))

(defn valor-sinalizado [transacao]
  (let [moeda (:moeda transacao)
        valor (:valor transacao)]
    (if (= (:tipo transacao) "despesa")
        (str moeda " -" valor)
        (str moeda " +" valor))))

(valor-sinalizado (first transacoes))
