(defn resumo [transacao]
  (select-keys transacao [:valor :tipo :data]))

(def transacoes [{:valor 33.0 :tipo "despesa" :comentario "Almoço" :data "19/11/2016"}
                 {:valor 2700.0 :tipo "receita" :comentario "Bico" :data "01/12/2016"}
                 {:valor 33.0 :tipo "despesa" :comentario "Livro de Clojure" :data "03/12/016"}])

(map resumo transacoes)

(defn despesa? [transacao]
  (= (:tipo transacao) "despesa"))

(filter despesa? transacoes)

(defn so-valor [transacao]
  (:valor transacao))

(reduce + (map so-valor (filter despesa? transacoes)))
;; ou
(->> transacoes
     (filter despesa?)
     (map so-valor)
     (reduce +))

(defn valor-grande? [transacao]
  (> (:valor transacao) 100))

(filter valor-grande? transacoes)