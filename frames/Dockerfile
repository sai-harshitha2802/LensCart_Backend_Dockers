# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Create working directory
WORKDIR /app

# ✅ Correct this line
COPY ./target/frames-0.0.1-SNAPSHOT.jar app.jar


# Expose the port your app runs on
EXPOSE 8083

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
