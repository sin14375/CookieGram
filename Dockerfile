FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/CookieGramApp-0.0.1-SNAPSHOT.jar CookieGramApp-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "CookieGramApp-0.0.1-SNAPSHOT.jar"]