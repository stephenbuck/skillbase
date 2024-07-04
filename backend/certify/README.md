*** Work in Progress ***

The certify service will provides skillbase-specific functionality itself, but will delegate workflow processing functionality to a third-party BPM: Flowable.

Integration with Flowable will be through its REST endpoints. Skillbase entities will be associated with Flowable entities via a peer_id.

Instructions:

* Type 'psql -d skillbase -U postgres -h localhost -p 15432' for PostgreSQL tool (password is 'postgres')
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool

Dependencies:

* Open 'http://localhost:8080/flowable-admin' for Flowable tool

REST Endpoints:

GraphQL Endpoints:

* Open './target/generated/schema.graphql' for GraphQL schema

Links:

https://www.flowable.com
https://bpmn.org

