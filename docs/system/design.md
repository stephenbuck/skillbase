
Design:

The Skillbase backend is designed a set of distributed microservices running in Docker containers. Each component of the backend is packaged into a custom Docker image derived from a standard image. For example, the 'skillbase/postgres' image is derived from the standard 'postgres' image, but adds configuration information, etc. 

![image](https://github.com/stephenbuck/skillbase/assets/1750488/857efe62-18e9-4426-b38f-1d339c8b4a8c)


The Docker container names are:

* 'skillbase/apisix:latest'
* 'skillbase/debezium:latest'
* 'skillbase/etcd:latest'
* 'skillbase/flagd:latest'
* 'skillbase/flowable:latest'
* 'skillbase/fluentd:latest'
* 'skillbase/kafka:latest'
* 'skillbase/keycloak:latest'
* 'skillbase/nginx:latest'
* 'skillbase/postgres:latest'
* 'skillbase/wildfly:latest'



The containers used are:

API Gateway (ApiSix):

API gateway functionality is provided by ApiSix.

* See https://apisix.apache.org
* Image skillbase/apisix


Security (Keycloak):

Security functionality (authentication and authorization) is provided by Keycloak. Integration with Keycloak is through its client implementation.

* See https://www.keycloak.org
* Image skillbase/keycloak


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


Features (Flagd):

Feature flag functionality is provided by Flagd. Integration with Flagd is through its provider implementation.

* See https://flagd.io
* Image skillbase/flagd


Logging (Fluentd):

Logging aggregation is provided by Fluentd.

* See https://fluentd.org
* Image skillbase/fluentd


Messaging (Kafka):

Messaging functionality is provided by Kafka. Integration with Kafka is through its JMS client libary.

* See https://kafka.apache.org
* Image skillbase/kafka


Monitoring (Prometheus):

Monitoring functionality is provided by Prometheus.

* See https://prometheus.io
* Image skillbase/prometheus


Persistence (Postgres):

Persistence functionality is provided by Postgres. Integration with Postgres is through its JDBC driver.

* See https://postgresql.org
* Image skillbase/postgres


Runtime (Wildfly):

Runtime functionality is provided by jBoss Wildfly. Integration with Wildfly is through its management interfaces.

* Image skillbase/wildfly
* See https://www.wildfly.org


Web (Nginx):

Web functionality is provided by Nginx.

* See https://nginx.org
* Image skillbase/nginx


Workflow (Flowable):

Workflow functionality is provided by a third-party BPM: Flowable. Integration with Flowable is through its REST endpoints. Skillbase entities are associated with Flowable entities via a peer_id.

* See https://www.flowable.org
* Image skillbase/flowable


Links:

https://docker.com
https://kubernetes.io
https://terraform.io

