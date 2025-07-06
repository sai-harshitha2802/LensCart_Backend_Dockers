# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven
COPY ./eureka/target/eureka-0.0.1-SNAPSHOT.jar app.jar

# Expose the Eureka server port
EXPOSE 8761

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
