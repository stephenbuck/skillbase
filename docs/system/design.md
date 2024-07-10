**Design:**

The Skillbase backend is designed a set of distributed microservices running in Docker containers. Each component of the backend is packaged into a custom Docker image derived from a standard image. For example, the 'skillbase/postgres' image is derived from the standard 'postgres' image, but adds configuration information, etc.

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)

**Containers:****

**Database (skillbase/postgres):**

Database functionality (Postgres) runs in a custom container derived from the 'postges:latest' image.

* See https://postgresql.org

**Features (skillbase/flagd):**

Feature flag functionality (Flagd) runs in a custom container derived from the
'ghcr.io/open-feature/flagd:latest' image.

* See https://flagd.io

**Identity (skillbase/keycloak):**

Identity functionaliy (Keycloak) runs in a custom container derived from the
'quay.io/keycloak/keycloak:latest' image.

* See https://www.keycloak.org

**Messaging (skillbase/kafka):**

Messaging functionality (Kafka) runs in a custom container derived from the 'bitnami/kafka:latest' image.

* See https://kafka.apache.org

**Runtime (skillbase/wildfly):**

Runtime functionality (Wildfly) runs in a custom container derived from the
'quay.io/wildfly/wildfly:latest' image.

* See https://www.wildfly.org

**Workflow (skillbase/flowable):**

Workflow functionality (Flowable) runs in a custom container derived from the
'flowable/flowable-rest:latest' image.

* See https://www.flowable.org

---

Caching (Redis):

Caching functionality is provided by Redis. Integration with Redis is through its client implementation.

* See https://redis.io
* Image skillbase/redis

Change-Data (Debezium):

Change-Data Capture functionality is provided by Debezium. Integration with Debezium is through its Postgres and Kafka interfaces.

* See https://debezium.io
* Image skillbase/debezium

Configuration (Etcd):

Configuration functionality is provided by Etcd. Integration with Etcd is through its provider implementation.

* See https://etcd.io
* Image skillbase/etcd

Logging (Fluentd):

Logging aggregation is provided by Fluentd.

* See https://fluentd.org
* Image skillbase/fluentd

Monitoring (Prometheus):

Monitoring functionality is provided by Prometheus.

* See https://prometheus.io
* Image skillbase/prometheus

Web (Nginx):

Web functionality is provided by Nginx.

* See https://nginx.org
* Image skillbase/nginx

Links:

https://docker.com
https://kubernetes.io
https://terraform.io
