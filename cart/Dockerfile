# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY ./target/cart-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8087

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
