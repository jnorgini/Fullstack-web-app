FROM openjdk:17

WORKDIR /produtos

COPY target/produtos.jar produtos.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "produtos.jar"]
