FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY mvnw ./
# REMOVE THIS LINE: COPY .mvn .mvn
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests
