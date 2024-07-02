*** Work in Progress ***

The identity service will provide skillbase-specific functions itself, but will delegate authentication and authorization to a third-party IAM: KeyCloak.

Integration with KeyCloak will be through its REST endpoints. Skillbase entities will be associated with KeyCloak entities via a peer_id.

Instructions:

* Open './target/generated/schema.graphql' for GraphQL schema
* Open 'http://localhost:18080' for KeyCloak tool
* Type 'psql -d skillbase -U postgres -h localhost -p 5432' for PostgreSQL tool (password is 'postgres')
* Type 'sudo mvn liquibase:help' for Liquibase tool
* Type 'sudo mvn docker:help' for Docker tool

Links:

https://www.keycloak.org
https://oauth.net/2
https://www.baeldung.com/java-ee-oauth2-implementation
https://www.baeldung.com/java-json-web-tokens-jjwt
https://connect2id.com/products/nimbus-jose-jwt

