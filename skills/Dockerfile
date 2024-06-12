FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SkillsApp.jar
ENTRYPOINT ["java","-jar","/SkillsApp.jar"]
