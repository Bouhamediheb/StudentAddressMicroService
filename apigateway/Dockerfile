# Use openjdk as the base image
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file (Assuming you have already built the JAR file using Maven)
COPY target/apigateway-0.0.1-SNAPSHOT.jar /app/apigateway.jar

# Expose the port on which API Gateway runs (9090)
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "/app/apigateway.jar"]
