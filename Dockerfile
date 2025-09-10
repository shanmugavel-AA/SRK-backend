# Stage 1: Build the JAR
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all project files
COPY pom.xml .
COPY src ./src

# Build JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM openjdk:17-slim
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
