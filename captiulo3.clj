;; Listas - performaticos para incluir itens no inicio
(list 1 2 3 4 5)
'(1 2 3 4 5)

(def um-ate-5 '(1 2 3 4 5))
(count um-ate-5)

(def um-ate-15 (range 1 16))
um-ate-15

(map fizzbuzz um-ate-15)

(def cantora-arretada (list "Renata Arruda" "Joao Pessoa" 23 " dezembro" 1967))
(nth cantora-arretada 0)
(nth cantora-arretada 4)
(first cantora-arretada)
(conj cantora-arretada "MPB")

;; Vetores - performaticos para incluir itens no final
(vector 1 2 3 4)
[1 2 3]

(def numero-vetorizados [1 2 3 4])
numero-vetorizados

(map fizzbuzz numero-vetorizados)

(def cantor-arretado (vector "Chico Cesar" "Catole da Rocha"
                             26 "Janeiro" 1964))

cantor-arretado
(get cantor-arretado 0)
(get cantor-arretado 4)
(last cantor-arretado)
(first cantor-arretado)

(conj cantor-arretado "MPB")
cantor-arretado

;; Set
(hash-set "Chico Cesar" "Renata Arruda")
#{"Chico Cesar" "Renata Arruda"}

(def artistas #{"Chico Cesar" "Renata Arruda"})
artistas

(conj artistas "Jackson do Pandeiro")
(conj artistas "Chico Cesar")
