**Design:**


**Domain-Driven Design:**

I’ve become a big fan of domain-driven design, primarily because it forces the architecture to focus on the business on its terms. I’m particularly interested in the intersection between DDD, microservices, and GraphQL. I’m going to use a GraphQL schema, in a schema-first approach, to represent the domain model and it should serve as a “single source of truth” from which other artifacts, such as SQL schemas and Java objects, can be created. Some parts of the system will be built with a code-first approach to take advantage of some of the GraphQL tools.

* See https://dzone.com/refcardz/getting-started-domain-driven


**Event-Driven Architecture:**

An event-driven architecture is such a natural way of looking at applications that it’s hard to choose any other architecture. I’ve been doing some form of event-driven development for most of my career, so it’s gratifying to see it being so widely used these days. I’ll be using Kafka for the message broker and Cloud-Events for the event definitions. The initial version will not use event-sourcing, but future versions will use Debezium for eventual consistency among the various distributed components (microservices, distributed caches, etc).

* See https://apache.kafka.org
* See https://docs.confluent.io/kafka-clients/java/current/overview.html
* See https://cloudevents.io
* See https://github.com/enriquemolinari/jqueue
* See https://github.com/gruelbox/transaction-outbox


**Microservice Implementation:**

Past iterations have been implemented using a handful of services combined into one monolithic application, but there are definitely advantages to having a more fine-grained, microservice architecture and most new systems are being built this way. I’ll be using a combination of Jakarta EE and Spring Boot as they are the most popular microservice frameworks for Java. Some of the microservices will delegate a portion of their functionality to backend providers (e.g. KeyCloak for IAM).

* See https://microprofile.io/
* See https://smallrye.io
* See https://spring.io/projects/spring-boot


**Language (Java 21):**

I’ve used Java for every backend implementation of this project so far and will use it for this one as well, especially given the recent evolution of Java, with new features like closures, records, etc.

* See https://docs.oracle.com/en/java/javase/21/docs/api/index.html


**Framework (Jakarta EE and Spring Boot):**

Jakarta EE is the latest incarnation of the J2EE framework. I have extensive experience with J2EE so it will be interesting to see how the framework has advanced. I expect to use Jakarta EE, especially the MicroProfile and its extensions, for the core framework. I'll be using Spring Boot for the runtime
container for the microservices.

* See https://jakarta.ee
* See https://microprofile.io
* See https://spring.io/projects/spring-boot


**Authentication / Authorization (Jakarta EE JWT):**

The Skillbase backend uses the Jakarta EE JWT framework for runtime authentication and authorization.

* See https://github.com/smallrye/smallrye-jwt
* See https://microprofile.io/specifications/microprofile-jwt-auth/


**Configuration (Jakarta EE Configuration):**

The Skillbase backend uses the Jakarta EE Config framework for runtime configuration.

* See https://github.com/smallrye/smallrye-config
* See https://microprofile.io/specifications/microprofile-config/


**Fault-Tolerance (Jakarta EE Fault-Tolerance):**

The Skillbase backend uses the Jakarta EE Fault-Tolerance framework to implement fault-tolerance.

* See https://github.com/smallrye/smallrye-fault-tolerance
* See https://microprofile.io/specifications/microprofile-fault-tolerance/


**Feature Flags (OpenFeatures):**

The Skillbase backend uses the OpenFeatures framework to manage feature flags.

* See https://openfeature.dev


**Health (Jakarta EE Health):**

Sklllbase uses the Jakarta EE Health framework to runtime health information.

* See https://github.com/smallrye/smallrye-health
* See https://microprofile.io/specifications/microprofile-health/


**Messaging (Jakarta EE JMS):**

The Skillbase backend uses the Jakarta EE JMS framework to produce and consume events via a
message broker (e.g. Kafka).


**Search (Elasticsearch)**

The Skillbase backend uses the Elasticsearch engine for keyword search. The search engine is
updated via Change Data Capture (CDC) events (e.g. Debezium).

* See https://elasticsearch.co


**Persistence (Jakarta EE Persistence, MinIO Object Store):**

The Skillbase backend uses the Jakara EE Persistence framework for relational (e.g. PostgreSQL)
data storage and a distributed object store (e.g. MinIO) for content storage. Distributed objects
are accessed through a POSIX file system layer (e.g. JuiceFS).

* See https://postgresql.org
* See https://liquibase.com
* See https://github.com/mtxr/vscode-sqltools
* See https://minio.com
* See https://juicefs.com


**GraphQL (Jakarta Microprofile GraphQL):**

The Skillbase backend uses the Jakarta Microprofile GraphQL framework to provide GraphQL endpoints.

* See https://graphql.org
* See https://github.com/graphql/graphiql
* See https://github.com/smallrye/smallrye-graphql
* See https://smallrye.io/smallrye-graphql/maven-plugin/


**REST (Jakarta EE JAX-RS):**

The Skillbase backend uses the Jakarta EE JAX-RS framework to provide REST endpoints.

* See https://github.com/smallrye/smallrye-open-api
* See https://microprofile.io/specifications/microprofile-open-api/
* See https://openapis.org
* See https://swagger.io


**Telemetry (Jakarta EE Telemetry):**

The Skillbase backend uses the Jakarta EE Telemetry framework to provide runtime telemetry.

* See https://github.com/smallrye/smallrye-opentelemetry
* See https://github.com/smallrye/smallrye-opentracing
* See https://microprofile.io/specifications/microprofile-telemetry/</p>
* See https://opentelemetry.io/


**Logging (Log4j, Slf4j):**

The Skillbase backend uses the Log4j and Slf4j libraries to generate logs.

* See https://log4j.org
* See https://slf4j.org


**Testing (JUnit, ArchUnit, Testcontainers):**

The Skillbase backend uses the JUnit, ArchUnit, and Testcontainers frameworks
for testing.

* See https://archunit.org
* See https://junit.org
* See https://testcontainers.com/


**Miscellaneous:**

* Docker (https://www.docker.com)
* JSON Problem Detail (https://github.com/zalando/problem)
* JSON Schema (https://json-schema.org/draft-07/schema)
* OpenRewrite (https://docs.openrewrite.org)
* SDK Man (https://sdkman.io)

