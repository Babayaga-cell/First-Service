FROM openjdk:11
EXPOSE 8082
ADD target/first-service-docker.jar first-service-docker.jar
ENTRYPOINT ["java","-jar","/first-service-docker.jar"]