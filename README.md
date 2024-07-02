*** Work in Progress ***

Overview

SkillBase is a distributed application that allows an organization to develop, certify, and track the skill sets of its members. Organizations like schools, employers, or the military. The primary elements of the application are users, skills, and certifications. Users select skills and then follow a workflow to be granted certification. Users can search for other users that have specific skills. There is lots of room for integration with third-party applications for identity management, content management, social media, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

Backend

Domain-Driven Design

I’ve become a big fan of domain-driven design, primarily because it forces the architecture to focus on the business on its terms. I’m particularly interested in the intersection between DDD, microservices, and GraphQL. I’m going to use a GraphQL schema, in a schema-first approach, to represent the domain model and it should serve as a “single source of truth” from which other artifacts, such as SQL schemas and Java objects, can be created. Some parts of the system will be built with a code-first approach to take advantage of some of the GraphQL tools.

Event-Driven Architecture

An event-driven architecture is such a natural way of looking at applications that it’s hard to choose any other architecture. I’ve been doing some form of event-driven development for most of my career, so it’s gratifying to see it being so widely used these days. I’ll be using Kafka for the message broker and Cloud-Events for the event definitions. The initial version will not be designed using event-sourcing, but future version will make use of it to for eventual consistency among the various distributed components (microservices, distributed caches, etc).

Microservice Implementation

Past iterations have been implemented using a handful of services combined into one monolithic application, but there are definitely advantages to having a more fine-grained, microservice architecture and most new systems are being built this way. I’ll be using a combination of Jakarta EE and Spring Boot as they are the most popular microservice frameworks for Java. Some of the microservices will delegate a portion of their functionality to backend providers (e.g. KeyCloak for IAM).

Jakarta EE

Jakarta EE is the latest incarnation of the J2EE framework. I have extensive experience with J2EE so it will be interesting to see how the framework has advanced. I expect to use Jakarta EE, especially the MicroProfile and its extensions, for the core framework.

Services (Microprofile Extensions)

The application will make use of Microprofile extensions for configuration, health, metrics, telemetry, etc. The health functionality will be used by Kubernetes. 

Language (Java 21)

I’ve used Java for every backend implementation of this project so far and will use it for this one as well, especially given the recent evolution of Java, with new features like closures, records, etc.

Runtime (Spring Boot)

Spring Boot is more or less the current go-to solution for building Java microservices and I'll be using portions of it, primarily for launching and configuring services.

GraphQL (SmallRye)

One thing I’ve learned from all the reading I’ve been doing lately is that GraphQL is here to stay. It’s such a huge improvement over clumsy old REST that it’s hard to not use it. I expect that it will improve performance and simplify code, but what I’m most excited about is that can be used as a “single source of truth” when doing domain-driven design. Once a design has been captured in a GraphQL schema, it can be used to generate a variety of other artifacts like value objects, SQL tables, etc. It’s makes it much easier to keep everything in synch.

REST (RestEasy, Swagger)

I’m including a REST API as well, as it’s still very popular and commonly used for integration with third-party tools. The endpoints will be documented with Swagger.

Message Broker (Kafka)

I’ll be using the Kafka as the backbone of the application as it’s the 500-lb gorilla of message brokers and has performance to spare. It will also make the application more flexible, and easy to integrate into existing systems. I’ve got a lot of experience with message queueing and pub-sub systems, so I’m looking forward to using this. 

Identity (Keycloak)

Lots of options here, but the bottom line is that I've chosen KeyCloak due to its ease of hosting and integration. It will also handle JWT tokens and OAuth. No more dinky user, group, role JDBC toys for me! It will also provide authentication for other components, like the API gateway.

Configuration (etcd)

As a distributed application, skillbase needs a reliable way to change and propagate configuration information. In the microservices, I'll use the Microprofile Config API for etcd, the gold standard, as the provider. Other components, such as Kafka, ApiSix, will use it as well.

Log Aggregation (fluentd)

I'm using fluentd to consolidate log streams from the various components. 

Feature Flags (OpenFeatures, flagd)

I'm using flagd from the OpenFeatures project to support feature flags.

Observability (Prometheus, Grafana)

I've chosen Prometheus and grafana for monitoring and alerting.

Containers (Docker)

Containers are here to stay, and Docker is the gold standard. Having a good pipeline from the build system to a Docker system will help with making the application available in the “cloud” and when used with an orchestration system like Kubernetes, should help enormously with scaling the system up. There aren’t any alternatives that are as popular and flexible as Docker, so this is a keeper.

Infrastructure (Terraform, Kubernetes)

Terraform is an awesome "infrastructure as code" tool. I'm currently using it during development to populate my Docker instance with my containers. It will be a good segue into Kubernetes in future phases.

Persistence (JPA, PostgreSQL)

I've chosen JPA as it's the easy choice at this phase. Every time I've used JPA, I've eventually replaced it with JDBC access for performance, so we'll see how it goes.

Database (PostgreSQL)

I’ve used the PostgreSQL database many times and, although it’s tempting to go all-in with a more modern NoSQL database, I think I’ll stick with a relational database at least for the primary datastore. Nobody ever got fired for recommending SQL and this should make the application easier to use in a variety of environments. I’m open to the possibility of using a NoSQL database, like MongoDB or CouchDB, for some parts of the system, especially on the “response” side.

Workflow (Flowable)

A key component of SkillBase is its ability to execute different workflows for each skill certification. Some certifications have no requirements and others may require complex steps and approvals. I’m going to use BPMN to let users model workflows with a web-based diagram editor and the Flowable engine will drive the processes. I’ll be for the BPMN modeler. Flowable works well with PostgreSQL, so it’ll fit right in.

SQL Changes (Liquibase)

I’ve used Liquibase for SQL database change management in a number of projects and really like its flexibility. I’ll be using it for this project to keep the multiple databases used for microservices in sync.

Unit Testing (JUnit, Weld)

I’ll be using JUnit 5 for unit testing. Not much new here.

Architectural Testing (ArchUnit)

I recently discovered the ArchUnit framework and it’s useful for making sure the architecture of the project matches the requirements.

Integration Testing (Arquillian, JUnit, Weld)

I’ll be using Arquillian for integration testing. I’ve never used it before, but it seems like it will handle the distributed components of the project.

Documentation (JavaDoc, Swagger)

More gold standards: JavaDoc for code, Swagger for REST endpoints.

Caching (Redis)

I love Redis! It’s easy to use as a caching layer and it’s crazy fast. What’s not to love? There are plenty of other alternatives, like memcached and Hazelcast, but I don’t see anything better than Redis for this application.

Deployment (Kubernetes, Terraform)

The first version will use Terraform for small-scale deployment. Future versions will use Kubernetes.

Gateway (ApiSix)

The API gateway will be Apache's ApiSix, which based on top of ngnix and etcd. It also integrates
with KeyCloak for authentication, authorization, and JWTs.

Web Server (ngnix)

The project will use ngnix for the primary web server. It's also used by the API gateway (ApiSix).

Frontend

I’ve done heaps of frontend development work in the past, but I’m more focused on backend development these days, so I’m going to go with the most popular choices. In general, I’ll be developing a single-page style system based on the React framework.

Language (JavaScript)

Ubiquitous - no choice.

Framework (React)

I’ve worked with a lot of JavaScript frameworks like Angular, JQuery and Backbone, but React seems to be the framework du jour, so I’ll go with it. The React Router package seems like it will be useful for a Single-Page Architecture, which is my current bias, due to the high likelihood for customization and integration.

Interfacing (GraphQL, REST)

The backend will support GraphQL and REST so the client will have to follow along. I’ll be using the Relay client library since it’s designed for the React framework.

Development

IDE (VisualStudio)

Build (Maven)

CI/CD (Jenkins)

More coming soon!


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

https://terraform.io
https://github.com/deliveredtechnologies/terraform-maven

https://kubernetes.io

https://maven.apache.org
https://maven.apache.org/plugins/maven-javadoc-plugin
https://maven.apache.org/plugins/maven-resources-plugin/
https://code.revelc.net/formatter-maven-plugin

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

https://resteasy.org
https://openapis.org
https://www.thunderclient.com
https://swagger.io

https://grpc.io

https://react.dev
https://reactrouter.com

https://www.flowable.com
https://bpmn.org

https://www.keycloak.org
https://oauth.net/2
https://www.baeldung.com/java-ee-oauth2-implementation
https://www.baeldung.com/java-json-web-tokens-jjwt

https://apache.kafka.org
https://docs.confluent.io/kafka-clients/java/current/overview.html
https://cloudevents.io

https://postgresql.org
https://www.liquibase.com
https://github.com/mtxr/vscode-sqltools

https://log4j.org
https://slf4j.org
https://www.fluentd.org

https://connect2id.com/products/nimbus-jose-jwt
<dependency>
    <groupId>com.nimbusds</groupId>
    <artifactId>nimbus-jose-jwt</artifactId>
    <version>9.x</version>
</dependency

https://opentelemetry.io/
https://prometheus.io

https://github.com/enriquemolinari/jqueue

https://etcd.io

https://redis.io

https://github.com/gruelbox/transaction-outbox

https://projectlombok.org

https://jenkins.io

https://flagd.dev
https://openfeature.dev

https://apisix.apache.org
https://grafana.com
https://nginx.org

https://sdkman.io

https://code.visualstudio.com

https://json-schema.org/draft-07/schema#



