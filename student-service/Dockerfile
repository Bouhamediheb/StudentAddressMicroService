# Use openjdk as the base image
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file (Assuming you have already built the JAR file using Maven)
COPY target/student-service-0.0.1-SNAPSHOT.jar /app/student-service.jar

# Expose the port on which the application runs (8081)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/app/student-service.jar"]
