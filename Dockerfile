FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/mywork-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9001

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
