################################################################################
# Variables
################################################################################

locals {
}

variable "apicurio_port_internal" {
  type = number
  default = 8080
}

variable "apicurio_port_external" {
  type = number
  default = 8085
}

variable "apisix_1_port_internal" {
  type = number
  default = 9080
}

variable "apisix_1_port_external" {
  type = number
  default = 9080
}

variable "apisix_2_port_internal" {
  type = number
  default = 9180
}

variable "apisix_2_port_external" {
  type = number
  default = 9180
}

variable "apisix_3_port_internal" {
  type = number
  default = 9443
}

variable "apisix_3_port_external" {
  type = number
  default = 9443
}

variable "debezium_port_internal" {
  type = number
  default = 8080
}

variable "debezium_port_external" {
  type = number
  default = 8088
}

variable "docker_network_name" {
  type = string
  default = "skillbase"
}

variable "docker_registry_port_internal" {
  type = number
  default = 5000
}

variable "docker_registry_port_external" {
  type = number
  default = 5000
}

variable "docker_registry_user_name" {
  type = string
  default = "stephenbuck"
}

variable "docker_registry_user_pass" {
  type = string
  default = "stephenbuck"
}

variable "elastic_api_port_internal" {
  type = number
  default = 9200
}

variable "elastic_api_port_external" {
  type = number
  default = 9200
}

variable "elastic_peer_port_internal" {
  type = number
  default = 9300
}

variable "elastic_peer_port_external" {
  type = number
  default = 9300
}

variable "etcd_client_port_internal" {
  type = number
  default = 2379
}

variable "etcd_client_port_external" {
  type = number
  default = 2379
}

variable "etcd_peer_port_internal" {
  type = number
  default = 2380
}

variable "etcd_peer_port_external" {
  type = number
  default = 2380
}

variable "flip_admin_port_internal" {
  type = number
  default = 9000
}

variable "flip_admin_port_external" {
  type = number
  default = 9007
}

variable "flip_api_port_internal" {
  type = number
  default = 8080
}

variable "flip_api_port_external" {
  type = number
  default = 8087
}

variable "fluentd_port_internal" {
  type = number
  default = 9880
}

variable "fluentd_port_external" {
  type = number
  default = 9880
}

variable "kafka_broker_port_internal_1" {
  type = number
  default = 9092
}

variable "kafka_broker_port_external_1" {
  type = number
  default = 9092
}

variable "kafka_connect_port_internal" {
  type = number
  default = 8083
}

variable "kafka_connect_port_external" {
  type = number
  default = 8083
}

variable "kafka_connect_topic_config" {
  type = string
  default = "kafka_connect_configs"
}

variable "kafka_connect_topic_offset" {
  type = string
  default = "kafka_connect_offsets"
}

variable "kafka_connect_topic_status" {
  type = string
  default = "kafka_connect_statuses"
}

variable "kafka_ui_port_internal" {
  type = number
  default = 8080
}

variable "kafka_schema_port_external" {
  type = number
  default = 8081
}

variable "kafka_schema_port_internal" {
  type = number
  default = 8081
}

variable "kafka_ui_port_external" {
  type = number
  default = 8088
}

variable "keycloak_port_internal" {
  type = number
  default = 8080
}

variable "keycloak_port_external" {
  type = number
  default = 18080
}

variable "keycloak_user_name" {
  type = string
  default = "admin"
}

variable "keycloak_user_pass" {
  type = string
  default = "admin"
}

variable "memcached_port_internal" {
  type = number
  default = 11211
}

variable "memcached_port_external" {
  type = number
  default = 11211
}

variable "minio_user_name" {
  type = string
  default = "skillbase"
}

variable "minio_user_pass" {
  type = string
  default = "skillbase"
}

variable "minio_admin_port_internal" {
  type = number
  default = 9000
}

variable "minio_admin_port_external" {
  type = number
  default = 9000
}

variable "minio_api_port_internal" {
  type = number
  default = 9001
}

variable "minio_api_port_external" {
  type = number
  default = 9001
}

variable "nginx_port_internal" {
  type = number
  default = 80
}

variable "nginx_port_external" {
  type = number
  default = 80
}

variable "postgres_user_name" {
  type = string
  default = "postgres"
}

variable "postgres_user_pass" {
  type = string
  default = "postgres"
}

variable "postgres_port_internal" {
  type = number
  default = 5432
}

variable "postgres_port_external" {
  type = number
  default = 5432
}

variable "prometheus_port_internal" {
  type = number
  default = 9090
}

variable "prometheus_port_external" {
  type = number
  default = 9090
}

variable "redis_port_internal" {
  type = number
  default = 6379
}

variable "redis_port_external" {
  type = number
  default = 6379
}

variable "skillbase_tag" {
  type = string
  default = "latest"
}

variable "wildfly_admin_port_internal" {
  type = number
  default = 9990
}

variable "wildfly_admin_port_external" {
  type = number
  default = 9990
}

variable "wildfly_http_port_internal" {
  type = number
  default = 8080
}

variable "wildfly_http_port_external" {
  type = number
  default = 8080
}

################################################################################
# Terraform
################################################################################

# https://registry.terraform.io/providers/kreuzwerker/docker/latest/docs/resources/container

terraform {
  required_providers {
    docker = {
      source = "kreuzwerker/docker"
      version = "~> 3.0.2"
    }
    kafka = {
      source = "Mongey/kafka"
      version = "0.7.1"
    }
  }
}

provider "kafka" {
  bootstrap_servers = ["kafka:${kafka_broker_port_internal_1}"]
}

################################################################################
# Docker
################################################################################

provider "docker" {
  host = "unix:///var/run/docker.sock"
  registry_auth {
    address = "localhost:${var.docker_registry_port_internal}"
    username = var.docker_registry_user_name
    password = var.docker_registry_user_pass
  }
}

resource "docker_network" "private_network" {
  name = var.docker_network_name
}

/*
################################################################################
# ApiCurio
################################################################################

resource "docker_image" "apicurio" {
  name = "skillbase/apicurio:latest"
  keep_locally = true
}

resource "docker_container" "apicurio" {
  name = "apicurio"
  image = docker_image.apicurio.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  ports {
    internal = var.apicurio_port_internal
    external = var.apicurio_port_external
  }
  depends_on = [
    docker_container.kafka,
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# ApiSix
################################################################################

resource "docker_image" "apisix" {
   name = "apache/apisix:latest"
   keep_locally = true
}

resource "docker_container" "apisix" {
  name = "apisix"
  image = docker_image.apisix.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  env = [
    "APISIX_STAND_ALONE=true"
  ] 
  ports {
    internal = var.apisix_1_port_internal
    external = var.apisix_1_port_external
  }
  ports {
    internal = var.apisix_2_port_internal
    external = var.apisix_2_port_external
  }
  ports {
    internal = var.apisix_3_port_internal
    external = var.apisix_3_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Debezium
################################################################################

resource "docker_image" "debezium" {
  name = "debezium/server:latest"
  keep_locally = true
}

resource "docker_container" "debezium" {
  name = "debezium"
  image = docker_image.debezium.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  ports {
    internal = var.debezium_port_internal
    external = var.debezium_port_external
  }
  volumes {
    container_path = "/debezum/conf"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker/debezium/conf"
  }
  volumes {
    container_path = "/debezum/data"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker/debezium/data"
  }
  depends_on = [
    docker_container.kafka,
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Elasticsearch
################################################################################

resource "docker_image" "elastic" {
  name = "skillbase/elastic:latest"
  keep_locally = true
}

resource "docker_container" "elastic" {
  name = "elastic"
  image = docker_image.elastic.image_id
  network_mode = docker_network.private_network.name
  env = [
    "http.cors.enabled=true",
    "http.cors.allow-origin=http://localhost:8080"
    #    "http.cors.allow-headers: X-Requested-With,Content-Type,Content-Length,Authorization"
  ]
  ports {
    internal = var.elastic_api_port_internal
    external = var.elastic_api_port_external
  }
  ports {
    internal = var.elastic_peer_port_internal
    external = var.elastic_peer_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Etcd
################################################################################

resource "docker_image" "etcd" {
  name = "bitnami/etcd:latest"
  keep_locally = true
}

resource "docker_container" "etcd" {
  name = "etcd"
  image = docker_image.etcd.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  env = [
    "ETCD_ENABLE_V2=true",
    "ALLOW_NONE_AUTHENTICATION=yes",
    "ETCD_ADVERTISE_CLIENT_URLS=http://etcd:${var.etcd_client_port_internal}",
    "ETCD_LISTEN_CLIENT_URLS=http://0.0.0.0:${var.etcd_client_port_internal}"
  ]
  volumes {
    container_path = "/etcd_data"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker/etcd/etcd_data"
  }
  ports {
    internal = var.etcd_client_port_internal
    external = var.etcd_client_port_external
  }
  ports {
    internal = var.etcd_peer_port_internal
    external = var.etcd_peer_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Flipt
################################################################################

resource "docker_image" "flipt" {
  name = "skillbase/flipt:latest"
  keep_locally = true
}

resource "docker_container" "flipt" {
  name = "flipt"
  image = docker_image.flipt.image_id
  network_mode = docker_network.private_network.name
  stdin_open = true
  ports {
      internal = var.flipt_api_port_internal
      external = var.flipt_api_port_external
  }
  ports {
      internal = var.flipt_admin_port_internal
      external = var.flipt_admin_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Flowable
################################################################################

resource "docker_image" "flowable" {
  name = "skillbase/flowable:latest"
  keep_locally = true
}

resource "docker_container" "flowable" {
  name = "flowable"
  image = docker_image.flowable.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.flowable_port_internal
    external = var.flowable_port_external
  }
  depends_on = [
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Fluentd
################################################################################

resource "docker_image" "fluentd" {
  name = "fluent/fluentd:edge-debian"
  keep_locally = true
}

resource "docker_container" "fluentd" {
  name = "fluentd"
  image = docker_image.fluentd.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.fluentd_port_internal
    external = var.fluentd_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

################################################################################
# Kafka
################################################################################

resource "docker_image" "kafka" {
  name = "skillbase/kafka:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka" {
  name = "kafka"
  image = docker_image.kafka.image_id
  network_mode = docker_network.private_network.name
  env = [
    "KAFKA_CFG_REST_BOOTSTRAP_SERVERS=PLAINTEXT://0.0.0.0:9093,BROKER://0.0.0.0:9092,CONTROLLER://0.0.0.0:9094",
    "KAFKA_CFG_LISTENERS=CONTROLLER://:29093,PLAINTEXT_HOST://:9092,PLAINTEXT://:9094",
    "KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=BROKER:PLAINTEXT,PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT",
    "KAFKA_ADVERTISED_LISTENERS=PLAINTEXT_HOST://kafka:9092,PLAINTEXT://kafka:9094",
    "KAFKA_CFG_INTER_BROKER_LISTENER_NAME=PLAINTEXT",
    "KAFKA_CFG_BROKER_ID=1",
    "KAFKA_KRAFT_CLUSTER_ID=4L6g3nShT-eMCtK--X86sw",
    "KAFKA_CFG_NODE_ID=1",
    "KAFKA_CFG_PROCESS_ROLES=broker,controller",
    "KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER",
    "KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@localhost:29093",
    "KAFKA_CFG_OFFSETS_TOPIC_REPLICATION_FACTOR=1",
    "KAFKA_CFG_OFFSETS_TOPIC_NUM_PARTITIONS=1",
    "KAFKA_CFG_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1",
    "KAFKA_CFG_TRANSACTION_STATE_LOG_MIN_ISR=1",
    "KAFKA_CFG_GROUP_INITIAL_REBALANCE_DELAY_MS=0",
    "KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE=true"
  ]
  ports {
    internal = var.kafka_broker_port_internal_1
    external = var.kafka_broker_port_external_1
  }
  depends_on = [
    docker_container.registry
  ]
}

################################################################################
# Kafka-Connect
################################################################################

resource "docker_image" "kafka_connect" {
  name = "skillbase/kafka_connect:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka_connect" {
  name = "kafka_connect"
  image = docker_image.kafka_connect.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  ports {
    internal = var.kafka_connect_port_internal
    external = var.kafka_connect_port_external
  }
  env = [
    "BOOTSTRAP_SERVERS=kafka:${var.kafka_broker_port_internal_1}",
    "GROUP_ID=1",
    "CONFIG_STORAGE_TOPIC=${var.kafka_connect_topic_config}",
    "OFFSET_STORAGE_TOPIC=${var.kafka_connect_topic_offset}",
    "STATUS_STORAGE_TOPIC=${var.kafka_connect_topic_status}"
  ]
  depends_on = [
    docker_container.kafka,
    docker_container.postgres,
    docker_container.registry
  ]
}

################################################################################
# Kafka-Schema
################################################################################

resource "docker_image" "kafka_schema" {
  name = "skillbase/kafka_schema:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka_schema" {
  name = "kafka_schema"
  image = docker_image.kafka_schema.image_id
  network_mode = docker_network.private_network.name
  env = [
    "SCHEMA_REGISTRY_HOST_NAME=kafka_schema",
    "SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS=kafka:${var.kafka_broker_port_internal_1}",
    "SCHEMA_REGISTRY_LISTENERS=http://0.0.0.0:${var.kafka_schema_port_internal}"
  ]
  ports {
    internal = var.kafka_schema_port_internal
    external = var.kafka_schema_port_external
  }
  depends_on = [
    docker_container.kafka
  ]
}

################################################################################
# Kafka-UI
################################################################################

resource "docker_image" "kafka_ui" {
  name = "skillbase/kafka_ui:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka_ui" {
  name = "kafka_ui"
  image = docker_image.kafka_ui.image_id
  network_mode = docker_network.private_network.name
  env = [
    "KAFKA_CLUSTERS_0_NAME=local",
    "KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:${var.kafka_broker_port_internal_1}",
    "KAFKA_CLUSTERS_0_SCHEMAREGISTRY=http://kafka_schema:${var.kafka_schema_port_internal}",
    "KAFKA_CLUSTERS_0_KAFKACONNECT_0_NAME=kafka_connect",
    "KAFKA_CLUSTERS_0_KAFKACONNECT_0_ADDRESS=http://kafka_connect:${var.kafka_connect_port_internal}",
    "DYNAMIC_CONFIG_ENABLED=true"
  ]
  ports {
    internal = var.kafka_ui_port_internal
    external = var.kafka_ui_port_external
  }
  depends_on = [
    docker_container.registry,
    docker_container.kafka,
    docker_container.kafka_connect,
    docker_container.kafka_schema
  ]
}

/*
################################################################################
# KeyCloak
################################################################################

resource "docker_image" "keycloak" {
  name = "quay.io/keycloak/keycloak:latest"
  keep_locally = true
}

resource "docker_container" "keycloak" {
  name = "keycloak"
  image = docker_image.keycloak.image_id
  network_mode = docker_network.private_network.name
  env = [
    "KEYCLOAK_ADMIN=${var.keycloak_user_name}",
    "KEYCLOAK_ADMIN_PASSWORD=${var.keycloak.user_pass}"
  ]
  ports {
    internal = var.keycloak_port_internal
    external = var.keycloak_port_external
  }
  depends_on = [
    docker_container.postgres,
    docker_container.registry
  ]
  command = ["start-dev"]
}
*/

/*
################################################################################
# Memcached
################################################################################

resource "docker_image" "memcached" {
  name = "skillbase/memcached:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "memcached" {
  name = "memcached"
  image = docker_image.memcached.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.memcached_port_internal
    external = var.memcached_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

################################################################################
# Minio
################################################################################

resource "docker_image" "minio" {
  name = "quay.io/minio/minio:latest"
  keep_locally = true
}

resource "docker_container" "minio" {
  name = "minio1"
  image = docker_image.minio.image_id
  network_mode = docker_network.private_network.name
  env = [
    "MINIO_ROOT_USER=${var.minio_user_name}",
    "MINIO_ROOT_PASSWORD=${var.minio_user_pass}"
  ]
  ports {
    internal = var.minio_admin_port_internal
    external = var.minio_admin_port_external
  }
  ports {
    internal = var.minio_api_port_internal
    external = var.minio_api_port_external
  }
  volumes {
    container_path = "/data"
    host_path = "/home/stephenbuck/Desktop/skillbase/system/runtime/minio/data"
  }
  depends_on = [
    docker_container.registry
  ]
  command = ["server", "/data", "--console-address", ":${var.minio_api_port_internal}"]
}

/*
################################################################################
# Nginx
################################################################################

resource "docker_image" "nginx" {
  name = "nginx:1.19.0-alpine"
  keep_locally = true
}

resource "docker_container" "nginx" {
  name = "nginx"
  image = docker_image.nginx.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  env = [
    "NGINX_PORT=${var.nginx_port_internal}"
  ]
  ports {
    internal = var.nginx_port_internal
    external = var.nginx_port_external
  }
  depends = [
    docker_container.registry
  ]
}
*/

################################################################################
# Postgres
################################################################################

resource "docker_image" "postgres" {
  name = "skillbase/postgres:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "postgres" {
  name = "postgres"
  image = docker_image.postgres.image_id
  network_mode = docker_network.private_network.name
  env = [
    "POSTGRES_USER=${var.postgres_user_name}",
    "POSTGRES_PASSWORD=${var.postgres_user_pass}"
  ]
  ports {
    internal = var.postgres_port_internal
    external = var.postgres_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}

/*
################################################################################
# Redis
################################################################################

resource "docker_image" "redis" {
  name = "skillbase/redis:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "redis" {
  name = "redis"
  image = docker_image.redis.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.redis_port_internal
    external = var.redis_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

################################################################################
# Registry
################################################################################

resource "docker_image" "registry" {
  name = "registry:latest"
  keep_locally = true
}

resource "docker_container" "registry" {
  name = "registry"
  image = docker_image.registry.image_id
  restart = "always"
  ports {
    internal = var.docker_registry_port_internal
    external = var.docker_registry_port_external
  }
}

/*
################################################################################
# Prometheus
################################################################################

resource "docker_image" "prometheus" {
  name = "prom/prometheus:latest"
  keep_locally = true
}

resource "docker_container" "prometheus" {
  name = "prometheus"
  image = docker_image.prometheus.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.prometheus_port_internal
    external = var.prometheus_port_external
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

################################################################################
# Wildfly
################################################################################

resource "docker_image" "wildfly" {
  name = "skillbase/wildfly:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "wildfly" {
  name = "wildfly"
  image = docker_image.wildfly.image_id
  network_mode = docker_network.private_network.name
  ports {
    internal = var.wildfly_admin_port_internal
    external = var.wildfly_admin_port_external
  }
  ports {
    internal = var.wildfly_http_port_internal
    external = var.wildfly_http_port_external
  }
  host {
    host = "wildfly"
    ip = "0.0.0.0"
  }
  env = [
    "WILDFLY_BIND_INTERFACE=0.0.0.0",
    "WILDFLY_MGMT_BIND_INTERFACE=0.0.0.0"
  ]
  depends_on = [
    //    docker_container_debezium,
    //    docker_container.elastic,
    //    docker_container.flipt,
    //    docker_container.flowable,
    docker_container.kafka,
    docker_container.kafka_connect,
    docker_container.kafka_schema,
    //    docker_container.keycloak,
    //    docker_container.memcached,
    docker_container.minio,
    docker_container.postgres,
    //    docker_container.redis,
    docker_container.registry
  ]
}

/*
################################################################################
# Skillbase Catalog
################################################################################

resource "docker_image" "catalog" {
  name = "skillbase/catalog:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "catalog" {
  name  = "catalog"
  image = docker_image.catalog.image_id
  depends_on = [
    docker_container.registry,
    docker_container.wildfly
  ]
}
*/

/*
################################################################################
# Skillbase Image
################################################################################

resource "docker_image" "image" {
  name = "skillbase/image:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "image" {
  name  = "image"
  image = docker_image.image.image_id
  depends_on = [
    docker_container.registry,
    docker_container.wildfly
  ]
}
*/

/*
################################################################################
# Skillbase Member
################################################################################

resource "docker_image" "member" {
  name = "skillbase/member:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "member" {
  name = "member"
  image = docker_image.member.image_id
  depends_on = [
    docker_container.registry,
    docker_container.wildfly
  ]
}
*/

/*
################################################################################
# Skillbase Workflow
################################################################################

resource "docker_image" "workflow" {
  name = "skillbase/workflow:${var.skillbase_tag}"
  keep_locally = true
}
resource "docker_container" "workflow" {
  name = "workflow"
  image = docker_image.workflow.image_id
  depends_on = [
    docker_container.registry,
    docker_container.wildfly
  ]
}
*/
