clear
mvn -DskipTests clean verify liquibase:clearCheckSums liquibase:update # wildfly:deploy
