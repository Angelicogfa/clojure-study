(ns financeiro.db)

(def registros (atom []))

(defn transacoes []
  @registros)

(defn limpar []
  (reset! registros []))

(defn- calcular [acumulado transacao]
  (let [valor (:valor transacao 0)]
    (if (= (:tipo transacao) "despesa")
      (- acumulado valor)
      (+ acumulado valor))))

(defn saldo []
  (if (= (count @registros) 0)
    0
    (reduce calcular 0 @registros)))

(defn registrar [transacao]
  (let [id (+ (count @registros) 1)
        nova-transacao (assoc transacao :id id)]
    (-> (swap! registros conj nova-transacao))
    nova-transacao))
