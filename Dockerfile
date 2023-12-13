FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY target/projeto-pratico-5-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8282

CMD ["java", "-jar", "app.jar"]