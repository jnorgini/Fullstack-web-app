FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package

FROM openjdk:17
COPY --from=build /app/target/*.jar produtos.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","produtos.jar"]
