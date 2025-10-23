# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy backend pom and source
COPY payment/pom.xml .
COPY payment/src ./src

# Copy frontend files to be served by Spring Boot
COPY payment-front/ ./src/main/resources/static/

# Build the executable JAR
RUN mvn -f pom.xml clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080

# Copy the JAR from the build stage
COPY --from=build /app/target/payment-0.0.1-SNAPSHOT.jar .

# Run the application
ENTRYPOINT ["java","-jar","payment-0.0.1-SNAPSHOT.jar"]
