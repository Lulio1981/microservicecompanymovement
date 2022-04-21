FROM openjdk:11
EXPOSE 8011
ARG JAR_FILE=target/microservice-company-movements-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]