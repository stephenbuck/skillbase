**_ Work in Progress _**

**Overview:**

SkillBase is a showcase distributed application that allows an organization to develop, certify, and track the skill sets of its members. Organizations like schools, employers, or the military. The primary elements of the application are users, skills, and certifications. Users select skills and then follow a workflow to be granted certification. There is lots of room for integration with third-party applications for identity management, content management, social media, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

A primary goal of this application is to illustrate the use of the most popular tools in use today:

- Modern Java (version 21+)
- Microservices (Jakarta EE, MicroProfile)
- Runtime (Wildfly, Spring Boot)
- Event-Driven (JMS, Kafka, Pulsar, Cloud Events)
- Feature Flags (Open Features, Flipt, Unleash)
- Security (JWT, OAuth, Keycloak)
- Database (JPA, Postgres, MySQL, Liquibase)
- Storage (MinIO, JuiceFS, SeaweedFS)
- Containers (Docker)
- Infrastructure (Terraform, Kubernetes)
- Configuration (Microprofile, Etcd)
- Workflow (BPMN, Flowable)
- Search (ElasticSearch, OpenSearch)
- Caching (Memcached, Redis, Valkey)
- Registry (Etcd, Consul)
- Interfaces (REST, GraphQL)
- Logging (Log4j, Slf4j, Fluentd)
- Testing (JUnit, Cucumber, RestAssured, ArchUnit, Testcontainers)
- Build (Maven, NPM)
- Monitoring (OpenTelemetry, Prometheus)
- Frontend (Typescript, React, MaterialUI, NPM)
- Admin (Python, Flask, UV)
- Documentation (MkDocs, Markdown, OpenAPI)
- Source Control (Git)

**Modern Java**

The Skillbase backend is written using modern Java (version 21+).

**Microservices**

Designed as a set of microservices, according to the Jakarta EE 10 specifications and the MicroProfile extensions.

**Runtime**

Runs as a set of Docker containers, one-per-service, combined with Wildfly or Spring Boot for service support.

**Event-Driven**

Event-driven design allows for maximum scalability, flexibility, and integration with other products. Code is written to the Jakarta JMS specification using Kafka or Pulsar as the message broker.

**Feature Flags**

Built-in support for feature-flags using OpenFeatures with a Flipt or Unleash provider.

**Security**

Integrated with IAM provider, Keycloak, for JWT, OAuth, and RBAC security.

**Database**

Built on industry-standard PostgreSQL or MySQL relational databases, using the Jakarta JPA framework and the Liquibase schema management tool. Future versions may use MongoDB for account profiles.

**Storage (skillbase/minio):**

Object storage functionality is provided by MinIO or JuiceFS.

**Containers**

All services and providers are deployed as Docker containers for local and cloud use.

**Infrastructure**

Infrastructure is built using Terraform code and managed using Kubernetes.

**Configuration**

Runtime application configuration is built on the Microprofile Config API running with Etcd or Consul as the provider.

**Workflow**

Workflow process management is handled using BPMN process diagrams and the Flowable engine and API. Future versions may embed the engine with the application to simplify transaction management.

**Search**

Keyword search for skills, etc. is handled by the ElasticSearch or OpenSearch engine.

**Caching**

Distributed caching is handled using Redis, Memcached, or Valkey. Cache contents are updated by domain events received by Debezium from the message broker.

**Interfaces**

Application access is through REST and GraphQL endpoints.

**Logging**

Logging is handled with Log4j and Slf4j. Log streams across services are aggregated using Fluentd.

**Testing**

Unit testing is performed using JUnit and Mockito. Feature testing is performed using Cucumber. Architectural testing is performed using ArchUnit. Integration testing is performed using Testcontainers.

**Build**

The Skillbase backend is built using Maven and a variety of plugins. The frontend is built using NPM.

**Monitoring**

Monitoring is handled by a combination of Microprofile APIs and the open-source Prometheus tool.

**Frontend**

The primary frontend framework is React, with the code written in Typescript. I'll be using other frameworks ReactRouter, ReactQuery, ReactAdmin, NextJS, and MaterialUI.

**Administration**

The administration command line utility is written in Python using the Flask service framework and built using UV.

**Documentation**

Documentation of the application is built with the open-source MkDocs tool, Markdown documents, and automatic API documentation using OpenAPI.

**Source Control**

Source code versioning and control is done with Git.


Full documentation can be found in the 'site' directory.
