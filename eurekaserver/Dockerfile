# Use openjdk as the base image
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file (Assuming you have already built the JAR file using Maven)
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar /app/eureka.jar

# Verify the Java version to ensure it is Java 17
RUN java -version

# Expose the port on which Eureka runs (8761)
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/app/eureka.jar"]
