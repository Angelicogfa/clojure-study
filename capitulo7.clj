;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; Função Pura ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def de-para [{:de "a" :para "4"}
              {:de "e" :para "3"}
              {:de "i" :para "1"}
              {:de "o" :para "0"}])

(defn escrita-hacker [texto dicionario]
  (if (empty? dicionario)
    texto
    (let [conversao (first dicionario)]
      (escrita-hacker (clojure.string/replace texto (:de conversao) (:para conversao))
                      (rest dicionario)))))

(escrita-hacker "alameda" de-para)

(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}
   :euro {:cotacao 0.28M :simbolo "€"}})

(defn transacao-em-outra-moeda [moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
                                            ;; ^^^^^^^^^^^^ 
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

(defn transacao-em-outra-moeda [cotacoes moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
                                            ;; ^^^^^ o novo argumento
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

;; ^^^^ Quebra o código anterior

(defn transacao-convertida [cotacoes moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
                                            ;; ^^^^^ o novo argumento
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

;; Possivel solução
(def transacao-em-outra-moeda (partial transacao-convertida cotacoes))

;; Aridade Multipla (override de parametros)
(defn transacao-em-outra-moeda
  ([cotacoes moeda transacao]
   (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
     (assoc transacao :valor (* cotacao (:valor transacao))
            :moeda simbolo)))
  ([moeda transacao]
   (transacao-em-outra-moeda cotacoes moeda transacao)))

;;;;;;;;;;;;;;;;;;;;;;;;; Imutabilidade ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def membros-fundadores
  (list "Argentina" "Brasil" "Paraguai" "Uruguai"))

(def membros-plenos (cons "Venezuela" membros-fundadores))

(rest membros-plenos)

(identical? (rest membros-plenos)
            membros-fundadores)

(def transacoes
  [{:valor 33M :tipo "despesa" :comentario "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700M :tipo "receita" :comentario "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29M :tipo "despesa" :comentario "Livro de Clojure" :moeda "R$" :data "03/12/2016"}])

(def transacoes (cons {:valor 45M :tipo "despesa" :comentario "Jogo no steam" :moeda "R$" :data "03/12/2016"} transacoes))

(def registros (atom ()))
@registros

(swap! registros conj {:valor 29M :tipo "despesa" :comentario "Livro de Clojure" :moeda "R$" :data "03/12/2016"})
registros

(conj {:valor 29M :tipo "despesa" :comentario "Livro de Clojure" :moeda "R$" :data "03/12/2016"} ())

(swap! registros conj
       {:valor 2700M :tipo "despesa" :comentario "Bico" :moeda "R$" :data "01/12/2016"})