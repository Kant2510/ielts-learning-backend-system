#FROM ubuntu:latest
#LABEL authors="tuann"
#
#ENTRYPOINT ["top", "-b"]
FROM maven:3.9.9-eclipse-temurin-21 AS builder
LABEL authors="tuannhat"

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests && ls -lh target

FROM openjdk:24-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/auth-module-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "app.jar"]