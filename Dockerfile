FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/test-devops.jar test-devops.jar
ENTRYPOINT ["java","-jar","/test-devops.jar"]
