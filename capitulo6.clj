(def transacoes [{:valor 33.0M :tipo "despesa" :comentario "Almoço" :moeda "R$" :data "19/11/2016"}
                 {:valor 2700.0M :tipo "receita" :comentario "Bico" :moeda "R$" :data "01/06/2016"}
                 {:valor 29.0M :tipo "despesa" :comentario "livro de clojure" :moeda "R$" :data "03/12/2016"}])
transacoes

(defn valor-sinalizado [transacao]
  (if (= (:tipo transacao) "despesa")
    (str (:moeda transacao) " -" (:valor transacao))
    (str (:moeda transacao) " +" (:valor transacao))))

(valor-sinalizado (first transacoes))
(valor-sinalizado (second transacoes))
(valor-sinalizado (last transacoes))

(defn valor-sinalizado [transacao]
  (let [moeda (:moeda transacao "R$")
        valor (:valor transacao)]
    (if (= (:tipo transacao) "despesa")
      (str moeda " -" valor)
      (str moeda " +" valor))))

(valor-sinalizado (first transacoes))
(map valor-sinalizado transacoes)

(defn data-valor [transacao]
  (str (:data transacao) " => " (valor-sinalizado transacao)))

(data-valor (first transacoes))

(defn transacao-em-yuan [transacao]
  (assoc transacao :valor (* 2.15 (:valor transacao))
         :moeda "¥"))

(transacao-em-yuan (first transacoes))

(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}})

(defn transacao-em-yuan [transacao]
  (assoc transacao :valor (* (:cotacao (:yuan cotacoes))
                             (:valor transacao))
         :moeda (:simbolo (:yuan cotacoes))))

(transacao-em-yuan (first transacoes))

(defn transacao-em-yuan [transacao]
  (assoc transacao :valor (* (get-in cotacoes [:yuan :cotacao]) (:valor transacao))
         :moeda (get-in cotacoes [:yuan :simbolo])))

(transacao-em-yuan (first transacoes))

(defn transacao-em-yuan [transacao]
  (let [yuan (:yuan cotacoes)]
    (assoc transacao :valor (* (:cotacao yuan) (:valor transacao))
           :moeda (:simbolo yuan))))

(transacao-em-yuan (first transacoes))

(data-valor (first transacoes))
(data-valor (transacao-em-yuan (first transacoes)))

(defn texto-resumo-em-yuan [transacao]
  (-> (transacao-em-yuan transacao)
      (data-valor)))

(map texto-resumo-em-yuan transacoes)

(def texto-resumo-em-yuan (comp data-valor transacao-em-yuan))

(map texto-resumo-em-yuan transacoes)

(defn transacao-em-yuan [transacao]
  (let [{yuan :yuan} cotacoes]
    (assoc transacao :valor (* (:cotacao yuan) (:valor transacao))
           :moeda (:simbolo yuan))))

(transacao-em-yuan (first transacoes))

(defn transacao-em-yuan [transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} :yuan} cotacoes]
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

(transacao-em-yuan (first transacoes))

(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}
   :euro {:cotacao 0.28M :simbolo "€"}})

(defn transacao-em-outra-moeda [moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

(transacao-em-outra-moeda :euro (first transacoes))
(transacao-em-outra-moeda :euro (last transacoes))

(transacao-em-outra-moeda :yuan (first transacoes))
(transacao-em-outra-moeda :yuan (last transacoes))

(def transacao-em-yuan (partial transacao-em-outra-moeda :yuan))
(def transacao-em-euro (partial transacao-em-outra-moeda :euro))

(map transacao-em-yuan transacoes)
(map transacao-em-euro transacoes)

(clojure.string/join ", " (map texto-resumo-em-yuan transacoes))

(def junta-tudo (partial clojure.string/join " ,"))
(junta-tudo (map texto-resumo-em-yuan transacoes))