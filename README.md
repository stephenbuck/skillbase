**_ Work in Progress _**

**Overview:**

SkillBase is a showcase distributed application that allows an organization to develop, certify, and track the skill sets of its members. Organizations like schools, employers, or the military. The primary elements of the application are users, skills, and certifications. Users select skills and then follow a workflow to be granted certification. There is lots of room for integration with third-party applications for identity management, content management, social media, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

A primary goal of this application is to illustrate the use of the most popular tools in use today. Some of the tools used:

- Modern Java (version 21+)
- Microservices (Jakarta EE, DDD)
- Runtime (Wildfly, Spring Boot)
- Event-Driven (JMS, Kafka, Cloud Events)
- Feature Flags (Open Features, Flipt)
- Security (JWT, OAuth, Keycloak)
- Database (JPA, Postgres, Liquibase)
- Containers (Docker)
- Infrastructure (Terraform, Kubernetes)
- Configuration (Microprofile, etcd)
- Workflow (BPMN, Flowable)
- Caching (Redis)
- Interfaces (REST, GraphQL)
- Logging (Log4j, Slf4j, fluentd)
- Testing (JUnit, ArchUnit, Testcontainers)
- Build (Maven and NPM)
- Monitoring (Microprofile, Prometheus)
- Frontend (Javascript, SPA, React, MaterialUI)
- Documentation (Markdown, OpenAPI, MkDocs)
- Versioning (Git)

**Modern Java**

The Skillbase backend is written using modern Java (version 21+).

**Microservices**

Designed as a set of microservices, according to the Jakarta EE 10 specifications.

**Runtime**

Runs as a set of Docker containers, one-per-service, combined with Wildfly and Spring Boot.

**Event-Driven**

Event-driven design allows for maximum scalability, flexibility, and integration with other products. Code is written to the JMS specification using Kafka as the message broker.

**Feature Flags**

Built-in support for feature-flags using OpenFeatures with a Flipt provider.

**Security**

Integrated with IAM provider, Keycloak, for JWT, OAuth, and RBAC security.

**Database**

Built on industry-standard Postgres relational database, using the Jakarta JPA framework and the Liquibase schema management tool. Future versions may use MongoDB for account profiles.

**Containers**

All services and providers are deployed as Docker containers for local and cloud use.

**Infrastructure**

Infrastructure is built using Terraform code and managed using Kubernetes.

**Configuration**

Runtime application configuration is built on the Microprofile Config API running with Etcd as the provider.

**Workflow**

Workflow process management is handled using BPMN process diagrams and the Flowable engine and API. Future versions may embed the engine with the application to simplify transaction management.

**Caching**

Distributed caching is handled using Redis, and it's Java API. Cache contents will be updated by domain events received from Kafka.

**Interfaces**

Application access is through REST and GraphQL endpoints.

**Logging**

Logging is handled with Log4j and Slf4j. Log streams across services are aggregated using Fluentd.

**Testing**

Unit testing is performed using JUnit tests. Architectural testing is performed using ArchUnit. Integration testing is performed using Testcontainers.

**Build**

The Skillbase backend is built using Maven and a variety of plugins. The frontend is built using NPM.

**Monitoring**

Monitoring is handled by a combination of Microprofile APIs and the open-source Prometheus tool.

**Frontend**

The frontend application is built as a single-page application (SPA) using the React framework and the MaterialUI library.

**Documentation**

Documentation of the application is built using Markdown documents, automatic API documentation using OpenAPI, and the open-source MkDocs tool.

**Versioning**

Source code versioning and control is done with Git.


Full documentation can be found in the 'site' directory.
