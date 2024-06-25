FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SkillbaseCertifyApp.jar
ENTRYPOINT ["java","-jar","/SkillbaseCertifyApp.jar"]
