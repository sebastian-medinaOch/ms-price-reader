# Etapa de construcción: usa Maven + JDK
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY . .

# Empaqueta con Spring Boot para que se genere un .jar ejecutable
RUN mvn clean package spring-boot:repackage -DskipTests

# Etapa de ejecución: JRE
FROM eclipse-temurin:21-jre-alpine

ENV TZ=America/Bogota
WORKDIR /app

# Copia el .jar generado desde la etapa anterior
COPY --from=builder /app/target/ms-price-reader-*.jar app.jar

LABEL maintainer="sebasthyy1@gmail.com"
LABEL application="ms-price-reader"
LABEL version="1.0.0"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
