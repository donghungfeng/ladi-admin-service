FROM openjdk:16-alpine
WORKDIR /
COPY target/ladi-admin-service-0.0.1-SNAPSHOT.jar ladi-admin-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","ladi-admin-service-0.0.1-SNAPSHOT.jar"]
