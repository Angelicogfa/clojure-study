FROM clojure:lein AS base
WORKDIR /src/api
COPY . /src/api
RUN lein deps && lein uberjar

FROM openjdk:8-alpine
ENV CHAVE_API=
COPY --from=base src/api/target/uberjar/conversor-0.1.0-standalone.jar conversor-0.1.0-standalone.jar
CMD [ "java", "-jar", "conversor-0.1.0-standalone.jar" ]
