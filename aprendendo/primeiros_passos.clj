(defn oi [nome]
  (str "oi, " nome "!"))

(oi "Guilherme")

(defn multiplo-de-3? [dividendo]
  (= 0 (mod dividendo 3)))

(multiplo-de-3? 9)
(multiplo-de-3? 8)

(defn par? [numero]
  (if (even? numero)
    "sim"
    "nao"))

(par? 2)
(par? 1)

(defn saldo [valor]
  (cond (< valor 0) "negativo"
        (> valor 0) "positivo"
        :else "zero"))

(saldo 10)
(saldo -10)
(saldo 0)