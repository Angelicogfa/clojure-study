# Projeto Conversor

## Criar Chave
https://free.currencyconverterapi.com/free-api-key/

## Para rodar o projeto no docker compose
1) Atribua a chave na variavel de ambiente do docker-compose

2) Rode o projeto
```
docker-compose up
```

## Para rodar o projeto pelo lein
1) Crie uma variavel de ambiente no seu S.O com o mesmo nome da variavel do docker compose ou atribua a chave
no arquivo cambista.clj

2) Rode o projeto com lein run
2.1) Você podera informar as moedas para conversão, por exemplo:
```
lein run --de=USD --para=BRL
lein run --de=USD --para=GBP
```

