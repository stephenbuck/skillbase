FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SkillbaseCatalogApp.jar
ENTRYPOINT ["java","-jar","/SkillbaseCatalogApp.jar"]
