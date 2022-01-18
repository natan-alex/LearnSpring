FROM openjdk:11

WORKDIR /app

EXPOSE 8080

COPY ./target/*.jar ./

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]