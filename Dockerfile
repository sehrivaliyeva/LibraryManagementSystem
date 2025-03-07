# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build output JAR file into the container at /app
COPY build/libs/BookManagement-0.0.1-SNAPSHOT.jar /app/BookManagement.jar
# Or, if you want to use the plain JAR
# COPY build/libs/BookManagement-0.0.1-SNAPSHOT-plain.jar /app/BookManagement.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "BookManagement.jar"]
