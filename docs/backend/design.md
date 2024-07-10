
Design:


Domain-Driven Design:

I’ve become a big fan of domain-driven design, primarily because it forces the architecture to focus on the business on its terms. I’m particularly interested in the intersection between DDD, microservices, and GraphQL. I’m going to use a GraphQL schema, in a schema-first approach, to represent the domain model and it should serve as a “single source of truth” from which other artifacts, such as SQL schemas and Java objects, can be created. Some parts of the system will be built with a code-first approach to take advantage of some of the GraphQL tools.


Event-Driven Architecture:

An event-driven architecture is such a natural way of looking at applications that it’s hard to choose any other architecture. I’ve been doing some form of event-driven development for most of my career, so it’s gratifying to see it being so widely used these days. I’ll be using Kafka for the message broker and Cloud-Events for the event definitions. The initial version will not use event-sourcing, but future versions will use Debezium for eventual consistency among the various distributed components (microservices, distributed caches, etc).


Microservice Implementation:

Past iterations have been implemented using a handful of services combined into one monolithic application, but there are definitely advantages to having a more fine-grained, microservice architecture and most new systems are being built this way. I’ll be using a combination of Jakarta EE and Spring Boot as they are the most popular microservice frameworks for Java. Some of the microservices will delegate a portion of their functionality to backend providers (e.g. KeyCloak for IAM).


Language (Java 21):

I’ve used Java for every backend implementation of this project so far and will use it for this one as well, especially given the recent evolution of Java, with new features like closures, records, etc.


Framework (Jakarta EE):

Jakarta EE is the latest incarnation of the J2EE framework. I have extensive experience with J2EE so it will be interesting to see how the framework has advanced. I expect to use Jakarta EE, especially the MicroProfile and its extensions, for the core framework.


Authentication / Authorization (Jakarta EE JWT):

The Skillbase backend uses the Jakarta EE JWT framework for runtime authentication and authorization.


Configuration (Jakarta EE Configuration):

The Skillbase backend uses the Jakarta EE Config framework for runtime configuration.


Fault-Tolerance (Jakarta EE Fault-Tolerance):

The Skillbase backend uses the Jakarta EE Fault-Tolerance framework to implement fault-tolerance.


Features (OpenFeatures):

The Skillbase backend uses the OpenFeatures framework to manage feature flags.


GraphQL (Jakarta Microprofile GraphQL):

The Skillbase backend uses the Jakarta Microprofile GraphQL framework to provide GraphQL endpoints.


Health (Jakarta EE Health):

Sklllbase uses the Jakarta EE Health framework to runtime health information.


Messaging (Jakarta EE JMS):

The Skillbase backend uses the Jakarta EE JMS framework to produce and consume events.


Metrics (Jakarta EE Metrics):

The Skillbase backend uses the Jakarta EE Metrics framework to provide runtime metrics.


Persistence (Jakarta EE Persistence):

The Skillbase backend uses the Jakara EE Persistence framework for object storage.


REST (Jakarta EE JAX-RS):

The Skillbase backend uses the Jakarta EE JAX-RS framework to provide REST endpoints.


Telemetry (Jakarta EE Telemetry):

The Skillbase backend uses the Jakarta EE Telemetry framework to provide runtime telemetry.


Logging (Log4j, Slf4j):

The Skillbase backend uses the Log4j and Slf4j libraries to generate logs.




Links:

https://dzone.com/refcardz/getting-started-domain-driven

https://docs.oracle.com/en/java/javase/21/docs/api/index.html

https://jakarta.ee

https://microprofile.io/
https://microprofile.io/specifications/microprofile-config/
https://microprofile.io/specifications/microprofile-fault-tolerance/
https://microprofile.io/specifications/microprofile-health/
https://microprofile.io/specifications/microprofile-jwt-auth/
https://microprofile.io/specifications/microprofile-metrics/
https://microprofile.io/specifications/microprofile-open-api/
https://microprofile.io/specifications/microprofile-telemetry/

https://smallrye.io
https://github.com/smallrye/smallrye-config
https://github.com/smallrye/smallrye-fault-tolerance
https://github.com/smallrye/smallrye-graphql
https://github.com/smallrye/smallrye-health
https://github.com/smallrye/smallrye-jwt
https://github.com/smallrye/smallrye-metrics
https://github.com/smallrye/smallrye-open-api
https://github.com/smallrye/smallrye-opentelemetry
https://github.com/smallrye/smallrye-opentracing
https://github.com/smallrye/smallrye-reactive-messaging

https://spring.io
https://spring.io/projects/spring-boot
https://docs.spring.io/spring-boot/maven-plugin

https://www.docker.com
https://fabric8io/docker-maven-plugin

https://junit.org
https://archunit.org
https://arquillian.org
https://testcontainers.com/

https://graphql.org
https://smallrye.io/smallrye-graphql/maven-plugin/
https://github.com/graphql/graphiql

https://openapis.org
https://swagger.io

https://grpc.io

https://apache.kafka.org
https://docs.confluent.io/kafka-clients/java/current/overview.html
https://cloudevents.io

https://postgresql.org
https://www.liquibase.com
https://github.com/mtxr/vscode-sqltools

https://log4j.org
https://slf4j.org


https://opentelemetry.io/

https://github.com/enriquemolinari/jqueue

https://github.com/gruelbox/transaction-outbox

https://flagd.dev
https://openfeature.dev

https://json-schema.org/draft-07/schema#


