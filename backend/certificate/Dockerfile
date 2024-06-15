FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CertificateApp.jar
ENTRYPOINT ["java","-jar","/CertificateApp.jar"]
