
Build:

The Skillbase backend is built using Maven and a variety of plugins for dependency management, packaging, source formatting, etc.

The Docker container names are:

* 'skillbase/catalog:latest'
* 'skillbase/workflow:latest'
* 'skillbase/member:latest'


Clean:

* Type 'mvn clean' to clean


Build:

* Type 'mvn verify' to build


Test:

* Type 'mvn test' to test


Deploy:

* Type 'mvn wildfly:deploy' to deploy


Tools:

* Type 'psql -d skillbase -U postgres -h localhost -p 15432' for PostgreSQL tool (password is 'postgres')
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool
* Type 'sudo mvn wildfly:help' for Wildfly tool


Change-Data Capture (Debezium)

See http://debezium.io


Database Changes (Liquibase)

I’ve used Liquibase for SQL database change management in a number of projects and really like its flexibility. I’ll be using it for this project to keep the multiple databases used for microservices in sync.


Links:

https://maven.apache.org
https://code.revelc.net/formatter-maven-plugin
https://maven.apache.org/plugins/maven-javadoc-plugin
https://maven.apache.org/plugins/maven-resources-plugin/

