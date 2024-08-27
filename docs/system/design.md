**Design:**

The Skillbase backend is designed as a set of distributed microservices running in Docker containers. Each component of the backend is packaged into a custom Docker image derived from a standard image. For example, the 'skillbase/postgres' image is derived from the standard 'postgres' image, but adds configuration information, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

**Containers (Docker):**

Containers are here to stay, and Docker is the gold standard. Having a good pipeline from the build system to a Docker system will help with making the application available in the “cloud” and when used with an orchestration system like Kubernetes, should help enormously with scaling the system up. There aren’t any alternatives that are as popular and flexible as Docker, so this is a keeper.

* See https://docker.com

**Infrastructure (Terraform and Kubernetes):**

Terraform is an awesome "infrastructure as code" tool. I'm currently using it during development to populate my Docker instance with my containers. It will be a good segue into Kubernetes in future phases.

* See https://kubernetes.io
* See https://terraform.io

**Database (skillbase/postgres):**

Database functionality (Postgres) runs in a custom container derived from the 'postges:latest' image. The Skillbase image adds SQL DDL to create the main 'skillbase' database.

The individual services create their own schema and tables within the main database.
The use of seperate schemas will facilitate moving to a database-per-service model
in the future.

* See https://postgresql.org

**Storage (skillbase/minio):**

Object storage functionality (Postgres) runs in a custom container derived from the 'minio/minio:latest' image. The Skillbase image adds configuration files to create the main 'skillbase' database. Objects are accessed through a
POSIX file system layer (JuiceFS).

* See https://min.io
* See https://juicefs.com

**Identity (skillbase/keycloak):**

Identity functionality (Keycloak) runs in a custom container derived from the
'quay.io/keycloak/keycloak:latest' image. The Skillbase image adds custom configuration.

Keycloak provides standard IAM features like authentication, OAuth suport, JWT token
generation, etc. Administration is through a browser interface. Integration with
the 'member' service is through a REST API.

* See https://www.keycloak.org

**Messaging (skillbase/kafka):**

Messaging functionality (Kafka) runs in a custom container derived from the 'bitnami/kafka:latest' image. The Skillbase image adds custom configuration and topics.

Integration with the individual services is through the Kafka JMS provider.

* See https://kafka.apache.org

**Runtime (skillbase/wildfly):**

Runtime functionality (Wildfly) runs in a custom container derived from the
'quay.io/wildfly/wildfly:latest' image. The Skillbase image adds custom configuration
for JDBC drivers, etc.

In the first phase, the individual services all share a single Wildfly instance.
In future phases, each service will have it's own runtime, base on Wildfly or
Spring Boot.

* See https://www.wildfly.org
* See https://spring.io/projects/spring-boot
* See https://docs.spring.io/spring-boot/maven-plugin

**Workflow (skillbase/flowable):**

Workflow functionality (Flowable) runs in a custom container derived from the
'flowable/flowable-rest:latest' image. The Skillbase image adds configuration
for the database connection, etc.

Flowable is the workflow engine used by the Workflow service. It executes workflow
processes defined by BPMN diagrams.

* See https://www.flowable.org

**Features (skillbase/flipt):**

Feature flag functionality (Flipt) runs in a custom container derived from the
'docker.flipt.io/flipt/flipt:latest' image. The Skillbase image adds custom
configuration.

Feature flags are defined using the Flipt browser interface and then
propagated to the individual services using a REST API.

* See https://flipt.io

**Search (skillbase/elastic):**

Search functionality (Elasticsearch) runs in a custom container derived from the
'bitnami/elasticsearch:latest' image. The Skillbase image adds custom
configuration.

The Elasticsearch index is updated using the catalog service domain events.

* See https://elasticsearch.co

**Caching (skillbase/redis):**

Caching functionality (Redis) runs in a custom container derived from the 'redis:latest'
image. The Skillbase image adds custom configuration.

Redis is used primarily for session management.

* See https://redis.io

**Change-Data (skillbase/debezium):**

Change-Data Capture functionality (Debezium) runs in a custom container derived from the 'debezium/server:latest' image. The Skillbase image adds configuration for connectors.

Integration with Debezium is through its Postgres and Kafka interfaces.

* See https://debezium.io

**Configuration (skillbase/etcd):**

Configuration functionality (Etcd) runs in a custom container derived from the 'bitnami/etcd:latest' image. The Skillbase image adds custom configuration.

Etcd propagates configuration information to the individual services.

* See https://etcd.io

**Logging (skillbase/fluentd):**

Logging functionality (Fluentd) runs in a custom container derived from the 'fluent/fluentd:edge-debian' image. The Skillbase image adds custom configuration.

Fluentd aggregates log information from the individual services into a single stream.

* See https://fluentd.org

**Web Server (skillbase/nginx):**

Web server functionality (Nginx) runs in a custom container derived from the 'nginx:1.19.0-alpine' image. The Skillbase image adds custom configuration.

* See https://nginx.org

**Monitoring (skillbase/prometheus):**

Monitoring functionality (Prometheus) runs in a custom container derived from the 'bitnami/prometheus:latest' image. The Skillbase image adds custom configuration.

* See https://prometheus.io
