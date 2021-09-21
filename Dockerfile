# Start with a base image - in this case JDK 8 Alpine
FROM openjdk:8-jdk-alpine

# Specify JAR location
ARG JAR_FILE=target/*.jar

# Copy the JAR
COPY ${JAR_FILE} dock.jar

# Set ENTRYPOINT in exec form to run the container as an executable
ENTRYPOINT ["java","-jar","/dock.jar"]