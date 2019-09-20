(def transacao (hash-map :valor 200 :tipo "receita"))
transacao

;; Incusao de mais um campo
(assoc transacao :categoria "receita")
(get transacao :valor)
(:valor transacao)

(def transacao-desnecessaria {:valor 34
                              :tipo "despesa" :rotulos '("desnecessaria" "cartao")})
transacao-desnecessaria
(:rotulos transacao-desnecessaria)
(:rotulos transacao)
(:rotulos transacao '())
