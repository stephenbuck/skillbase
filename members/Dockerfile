FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} MembersApp.jar
ENTRYPOINT ["java","-jar","/MembersApp.jar"]
