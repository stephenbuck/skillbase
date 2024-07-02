*** Work in Progress ***

The certify service will provides skillbase-specific functionality itself, but will delegate workflow processing functionality to a third-party BPM: Flowable.

Integration with Flowable will be through its REST endpoints. Skillbase entities will be associated with Flowable entities via a peer_id.

Instructions:

* Open './target/generated/schema.graphql' for GraphQL schema
* Type 'psql -d skillbase -U postgres -h localhost -p 5432' for PostgreSQL tool (password is 'postgres')
* Open 'http://localhost:8080' for Flowable tool
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool

Links:

https://www.flowable.com
https://bpmn.org

