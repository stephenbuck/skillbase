*** Work in Progress ***

The identity service will provide skillbase-specific functions itself, but will delegate authentication and authorization to a third-party IAM: KeyCloak.

Integration with KeyCloak will be through its REST endpoints. Skillbase entities will be associated with KeyCloak entities via a peer_id.

Instructions:

* Type 'psql -d skillbase -U postgres -h localhost -p 15432' for PostgreSQL tool (password is 'postgres')
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool
* Type 'sudo mvn wildfly:help' for Wildfly tool

Dependencies:

* Open 'http://localhost:18080/admin/master/console/' for KeyCloak tool

REST Endpoints:

* Open '/identity' for identity REST endpoint
* Open '/identity/groups' for groups REST endpoint
* Open '/identity/roles' for roles REST endpoint
* Open '/identity/users' for users REST endpoint

GraphQL Endpoints:

* Open './target/generated/schema.graphql' for GraphQL schema

Links:

https://www.keycloak.org
https://oauth.net/2
https://www.baeldung.com/java-ee-oauth2-implementation
https://www.baeldung.com/java-json-web-tokens-jjwt
https://connect2id.com/products/nimbus-jose-jwt

