# Usa uma imagem base com Maven e JDK 21 para build
FROM maven:3.9.6-jdk-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usa uma imagem base mais leve com apenas o JRE 21 para executar
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]