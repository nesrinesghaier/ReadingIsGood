FROM maven:3.6.3 AS build-stage

WORKDIR /app
COPY . /app

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11-jre as final-stage

COPY --from=build-stage /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
