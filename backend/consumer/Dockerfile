FROM eclipse-temurin:21-jdk-alpine
WORKDIR /home/app
COPY ../backend/consumer/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
