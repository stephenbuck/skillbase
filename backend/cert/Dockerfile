FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SkillbaseCertApp.jar
ENTRYPOINT ["java","-jar","/SkillbaseCertApp.jar"]
