**Build:**

The Skillbase backend is built using Maven and a variety of plugins for dependency management, packaging, source formatting, etc.

The Docker container names are:

* 'skillbase/catalog:latest'
* 'skillbase/workflow:latest'
* 'skillbase/member:latest'

**Commands:**

* Type 'mvn clean' to clean
* Type 'mvn verify' to build
* Type 'mvn test' to test
* Type 'mvn wildfly:deploy' to deploy

**Tools:**

* Type 'psql -d skillbase -U postgres -h localhost -p 5432' for the Postgres tool
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool
* Type 'sudo mvn wildfly:help' for Wildfly tool
* Type 'sudo mvn elasticsearch:help' for Elasticsearch tool
* Type 'sudo mvn openrewrite:help' for OpenRewrite


**Links:**

https://maven.apache.org
https://code.revelc.net/formatter-maven-plugin
https://maven.apache.org/plugins/maven-javadoc-plugin
https://maven.apache.org/plugins/maven-resources-plugin/
