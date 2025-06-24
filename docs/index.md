**_ Work in Progress _**

**Overview:**

SkillBase is a distributed application that allows an organization to certify and track the skill sets of its members. Organizations like schools, employers, or the military. The primary elements of the application are users, skills, and certifications. Users select skills and then follow a workflow to be granted a certification. There is lots of room for integration with third-party applications for identity management, content management, social media, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

A primary goal of this application is to illustrate the use of the most popular tools in use today:

- Modern Java (version 24+)
- Microservices (Jakarta EE, MicroProfile)
- Runtime (Spring Boot, Wildfly)
- Event-Driven (Kafka, Pulsar, Cloud Events)
- Feature Flags (Open Features, Flipt, Unleash)
- Security (OAuth, Keycloak)
- Database (Postgres, MySQL, Liquibase)
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
- Build (Maven)
- CI/CD (Jenkins)
- Monitoring (OpenTelemetry, Prometheus)
- Documentation (MkDocs, Markdown, OpenAPI)
- Source Control (Git)


**Modern Java**

The Skillbase backend is written using modern Java (version 24+).

**Microservices**

Designed as a set of microservices, according to the Jakarta EE 11 specifications and the MicroProfile extensions.

**Runtime**

Runs as a set of Docker containers, one-per-service, combined with Wildfly or Spring Boot for service support.

**Event-Driven**

Event-driven design allows for maximum scalability, flexibility, and integration with other products. Code is written to the Jakarta JMS specification using Kafka or Pulsar as the message broker and CloudEvents as the message format. The "transactional outbox" and "change data capture" patterns are used to simplify transactional integrity. CDC events are generated from PostgreSQL logs and Debezium.

**Feature Flags**

Built-in support for feature-flags using OpenFeatures with a Flipt or Unleash provider.

**Security**

Integrated with IAM provider, Keycloak, for JWT, OAuth, and RBAC security.

**Database**

Built on industry-standard PostgreSQL or MySQL relational databases, using the Jakarta JPA framework and the Liquibase schema management tool. Future versions may use a non-SQL database (e.g. MongoDB) for account profiles.

**Storage**

Object storage functionality is provided by MinIO or JuiceFS.

**Containers**

All services and providers are deployed as Docker containers for local and cloud use.

**Infrastructure**

Infrastructure is built using Terraform code and managed using Kubernetes.

**Configuration**

Runtime application configuration is built on the Microprofile Config API running with Etcd or Consul as the provider.

**Workflow**

Workflow process management is handled using BPMN process models and the Flowable embedded workflow engine. Embedded the workflow engine simplifiess transaction management and improves performance.

**Search**

Keyword search for skills, etc. is handled by the ElasticSearch or OpenSearch engine.

**Caching**

Distributed caching is handled using Redis, Memcached, or Valkey. Cache contents are updated by domain events received by Debezium from the message broker.

**Batch**

Batch processing is performed using JobRunr and the Jakarta EE Batch interface.

**Interfaces**

Application access is through REST and GraphQL endpoints.

**Logging**

Logging is handled with Log4j and Slf4j. Log streams across services are aggregated using Fluentd.

**Testing**

Static code analysis is performed using PMD. Unit testing is performed using JUnit and Mockito. Feature testing is performed using Cucumber. Architectural testing is performed using ArchUnit. Integration testing is performed using Testcontainers. Also JSpecify (see jspecify.org and errorprone.info/index).

**Build**

The Skillbase backend is built using Maven and a variety of plugins.

**Monitoring**

Monitoring is handled by a combination of Microprofile APIs and the open-source Prometheus tool.

**Frontend**

TBD

**Documentation**

Documentation of the application is built with the open-source MkDocs tool, Markdown documents, and automatic API documentation using OpenAPI.

**Source Control**

Source code versioning and control is done with Git.

Full documentation can be found in the 'site' directory.

