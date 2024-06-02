#FROM eclipse-temurin:17-jre-alpine
FROM openjdk:17
RUN mkdir /app
RUN rm -f /app/*.jar
WORKDIR /app
COPY target/task05_rest-1.0-SNAPSHOT.jar task05_rest-1.0-SNAPSHOT.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "task05_rest-1.0-SNAPSHOT.jar"]
