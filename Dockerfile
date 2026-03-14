FROM openjdk:24-jdk-alpine

WORKDIR /app

COPY target/digital_wallet-0.0.1-SNAPSHOT.jar app.jar