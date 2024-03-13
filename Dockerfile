FROM openjdk:17-slim-buster
ADD /target/supplier-service-0.0.1-SNAPSHOT.jar supplier-service.jar
ENTRYPOINT ["java", "-jar", "supplier-service.jar"]