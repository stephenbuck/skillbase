################################################################################
# Variables
################################################################################

variable "skillbase_system_runtime" {
  type = string
  default = "/home/stephenbuck/Desktop/skillbase/backend/system/runtime"
}

variable "skillbase_tag" {
  type = string
  default = "latest"
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

################################################################################
# Docker
################################################################################

variable "docker_network_name" {
  type = string
  default = "skillbase"
}

variable "docker_registry_port_external" {
  type = number
  default = 5000
}

variable "docker_registry_port_internal" {
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

variable "apicurio_port_external" {
  type = number
  default = 8085
}

variable "apicurio_port_internal" {
  type = number
  default = 8080
}

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
    external = var.apicurio_port_external
    internal = var.apicurio_port_internal
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

variable "apisix_1_port_external" {
  type = number
  default = 9080
}

variable "apisix_1_port_internal" {
  type = number
  default = 9080
}

variable "apisix_2_port_external" {
  type = number
  default = 9180
}

variable "apisix_2_port_internal" {
  type = number
  default = 9180
}

variable "apisix_3_port_external" {
  type = number
  default = 9443
}

variable "apisix_3_port_internal" {
  type = number
  default = 9443
}

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
    external = var.apisix_1_port_external
    internal = var.apisix_1_port_internal
  }
  ports {
    external = var.apisix_2_port_external
    internal = var.apisix_2_port_internal
  }
  ports {
    external = var.apisix_3_port_external
    internal = var.apisix_3_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Consul
################################################################################

variable "consul_port_external" {
  type = number
  default = 8088
}

variable "consul_port_internal" {
  type = number
  default = 8080
}

resource "docker_image" "consul" {
  name = "hashicorp/consul:latest"
  keep_locally = true
}

resource "docker_container" "consul" {
  name = "consul"
  image = docker_image.consul.image_id
  network_mode = docker_network.private_network.name
  restart = "always"
  ports {
    external = var.consul_port_external
    internal = var.consul_port_internal
  }
  volumes {
    container_path = "/consul/conf"
    host_path = "${var_skillbase_system_runtime}/consul/conf"
  }
  depends_on = [
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Debezium
################################################################################

variable "debezium_port_external" {
  type = number
  default = 8088
}

variable "debezium_port_internal" {
  type = number
  default = 8080
}

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
    external = var.debezium_port_external
    internal = var.debezium_port_internal
  }
  volumes {
    container_path = "/debezum/conf"
    host_path = "${var_skillbase_system_runtime}/debezium/conf"
  }
  volumes {
    container_path = "/debezum/data"
    host_path = "${var_skillbase_system_runtime}/debezium/data"
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
# ElasticSearch
################################################################################

variable "elasticsearch_api_port_external" {
  type = number
  default = 9200
}

variable "elasticsearch_api_port_internal" {
  type = number
  default = 9200
}

variable "elasticsearch_peer_port_external" {
  type = number
  default = 9300
}

variable "elasticsearch_peer_port_internal" {
  type = number
  default = 9300
}

resource "docker_image" "elasticsearch" {
  name = "skillbase/elasticsearch:latest"
  keep_locally = true
}

resource "docker_container" "elasticsearch" {
  name = "elasticsearch"
  image = docker_image.elasticsearch.image_id
  network_mode = docker_network.private_network.name
  env = [
    "http.cors.enabled=true",
    "http.cors.allow-origin=http://localhost:8080"
    #    "http.cors.allow-headers: X-Requested-With,Content-Type,Content-Length,Authorization"
  ]
  ports {
    external = var.elasticsearch_api_port_external
    internal = var.elasticsearch_api_port_internal
  }
  ports {
    external = var.elasticsearch_peer_port_external
    internal = var.elasticsearch_peer_port_internal
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

variable "etcd_client_port_external" {
  type = number
  default = 2379
}

variable "etcd_client_port_internal" {
  type = number
  default = 2379
}

variable "etcd_peer_port_external" {
  type = number
  default = 2380
}

variable "etcd_peer_port_internal" {
  type = number
  default = 2380
}

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
  ]  volumes {
    container_path = "/etcd_data"
    host_path = "${var_skillbase_system_runtime}/etcd/etcd_data"
  }

  volumes {
    container_path = "/etcd_data"
    host_path = "${var_skillbase_system_runtime}/etcd/etcd_data"
  }
  ports {
    external = var.etcd_client_port_external
    internal = var.etcd_client_port_internal
  }
  ports {
    external = var.etcd_peer_port_external
    internal = var.etcd_peer_port_internal
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

variable "flipt_admin_port_external" {
  type = number
  default = 9007
}

variable "flipt_admin_port_internal" {
  type = number
  default = 9000
}

variable "flipt_api_port_external" {
  type = number
  default = 8087
}

variable "flipt_api_port_internal" {
  type = number
  default = 8080
}

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
      external = var.flipt_api_port_external
      internal = var.flipt_api_port_internal
  }
  ports {
      external = var.flipt_admin_port_external
      internal = var.flipt_admin_port_internal
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

variable "flowable_port_external" {
  type = number
  default = 24224
}

variable "flowable_port_internal" {
  type = number
  default = 24224
}

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

variable "fluentd_port_external" {
  type = number
  default = 24224
}

variable "fluentd_port_internal" {
  type = number
  default = 24224
}

resource "docker_image" "fluentd" {
  name = "fluent/fluentd:edge-debian"
  keep_locally = true
}

resource "docker_container" "fluentd" {
  name = "fluentd"
  image = docker_image.fluentd.image_id
  network_mode = docker_network.private_network.name
  volumes {
    container_path = "/fluentd/etc"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/runtime/fluentd"
  }
  ports {
    external = var.fluentd_port_external
    internal = var.fluentd_port_internal
  }
  env = [
    "FLUENT_CONF=/fluentd/etc/fluentd.conf"
  ]
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# JuiceFS
################################################################################
*/

/*
################################################################################
# Kafka
################################################################################

variable "kafka_broker_port_external_1" {
  type = number
  default = 9092
}

variable "kafka_broker_port_internal_1" {
  type = number
  default = 9092
}

provider "kafka" {
  bootstrap_servers = ["kafka:${kafka_broker_port_internal_1}"]
}

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
    external = var.kafka_broker_port_external_1
    internal = var.kafka_broker_port_internal_1
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Kafka-Connect
################################################################################

variable "kafka_connect_port_external" {
  type = number
  default = 8083
}

variable "kafka_connect_port_internal" {
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
    external = var.kafka_connect_port_external
    internal = var.kafka_connect_port_internal
  }
  env = [
    "BOOTSTRAP_SERVERS=kafka:${var.kafka_broker_port_internal_1}",
    "CONFIG_STORAGE_TOPIC=${var.kafka_connect_topic_config}",
    "GROUP_ID=1",
    "OFFSET_STORAGE_TOPIC=${var.kafka_connect_topic_offset}",
    "STATUS_STORAGE_TOPIC=${var.kafka_connect_topic_status}"
  ]
  depends_on = [
    docker_container.kafka,
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Kafka-Schema
################################################################################

variable "kafka_schema_port_external" {
  type = number
  default = 8081
}

variable "kafka_schema_port_internal" {
  type = number
  default = 8081
}

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
    external = var.kafka_schema_port_external
    internal = var.kafka_schema_port_internal
  }
  depends_on = [
    docker_container.kafka
  ]
}
*/

/*
################################################################################
# Kafka-UI
################################################################################

variable "kafka_ui_port_external" {
  type = number
  default = 8088
}

variable "kafka_ui_port_internal" {
  type = number
  default = 8080
}

resource "docker_image" "kafka_ui" {
  name = "skillbase/kafka_ui:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka_ui" {
  name = "kafka_ui"
  image = docker_image.kafka_ui.image_id
  network_mode = docker_network.private_network.name
  env = [
    "DYNAMIC_CONFIG_ENABLED=true",
    "KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:${var.kafka_broker_port_internal_1}",
    "KAFKA_CLUSTERS_0_KAFKACONNECT_0_NAME=kafka_connect",
    "KAFKA_CLUSTERS_0_KAFKACONNECT_0_ADDRESS=http://kafka_connect:${var.kafka_connect_port_internal}",
    "KAFKA_CLUSTERS_0_NAME=local",
    "KAFKA_CLUSTERS_0_SCHEMAREGISTRY=http://kafka_schema:${var.kafka_schema_port_internal}"
  ]
  ports {
    external = var.kafka_ui_port_external
    internal = var.kafka_ui_port_internal
  }
  depends_on = [
    docker_container.registry,
    docker_container.kafka,
    docker_container.kafka_connect,
    docker_container.kafka_schema
  ]
}
*/

/*
################################################################################
# KeyCloak
################################################################################

variable "keycloak_port_external" {
  type = number
  default = 18080
}

variable "keycloak_port_internal" {
  type = number
  default = 8080
}

variable "keycloak_user_name" {
  type = string
  default = "admin"
}

variable "keycloak_user_pass" {
  type = string
  default = "admin"
}

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
    external = var.keycloak_port_external
    internal = var.keycloak_port_internal
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

variable "memcached_port_external" {
  type = number
  default = 11211
}

variable "memcached_port_internal" {
  type = number
  default = 11211
}

resource "docker_image" "memcached" {
  name = "skillbase/memcached:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "memcached" {
  name = "memcached"
  image = docker_image.memcached.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.memcached_port_external
    internal = var.memcached_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Minio
################################################################################

variable "minio_user_name" {
  type = string
  default = "skillbase"
}

variable "minio_user_pass" {
  type = string
  default = "skillbase"
}

variable "minio_admin_port_external" {
  type = number
  default = 9000
}

variable "minio_admin_port_internal" {
  type = number
  default = 9000
}

variable "minio_api_port_external" {
  type = number
  default = 9001
}

variable "minio_api_port_internal" {
  type = number
  default = 9001
}

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
    external = var.minio_admin_port_external
    internal = var.minio_admin_port_internal
  }
  ports {
    external = var.minio_api_port_external
    internal = var.minio_api_port_internal
  }
  volumes {
    container_path = "/data"
    host_path = "${var_skillbase_system_runtime}/minio/data"
  }
  depends_on = [
    docker_container.registry
  ]
  command = ["server", "/data", "--console-address", ":${var.minio_api_port_internal}"]
}
*/

/*
################################################################################
# MySQL
################################################################################

variable "mysql_user_name" {
  type = string
  default = "root"
}

variable "mysql_user_pass" {
  type = string
  default = "strong-password"
}

variable "mysql_port1_external" {
  type = number
  default = 3306
}

variable "mysql_port1_internal" {
  type = number
  default = 3306
}

variable "mysql_port2_external" {
  type = number
  default = 33060
}

variable "mysql_port2_internal" {
  type = number
  default = 33060
}

resource "docker_image" "mysql" {
  name = "skillbase/mysql:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "mysql" {
  name = "mysql"
  image = docker_image.mysql.image_id
  network_mode = docker_network.private_network.name
  env = [
    "MYSQL_ROOT_PASSWORD=${var.mysql_user_pass}"
  ]
  ports {
    external = var.mysql_port1_external
    internal = var.mysql_port1_internal
  }
  ports {
    external = var.mysql_port2_external
    internal = var.mysql_port2_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Nginx
################################################################################

variable "nginx_port_external" {
  type = number
  default = 80
}

variable "nginx_port_internal" {
  type = number
  default = 80
}

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
    external = var.nginx_port_external
    internal = var.nginx_port_internal
  }
  depends = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# OpenSearch
################################################################################

variable "opensearch_main_port_external" {
  type = number
  default = 9200
}

variable "opensearch_main_port_internal" {
  type = number
  default = 9200
}

variable "opensearch_perf_port_external" {
  type = number
  default = 9600
}

variable "opensearch_perf_port_internal" {
  type = number
  default = 9600
}

resource "docker_image" "opensearch" {
  name = "skillbase/opensearch:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "opensearch" {
  name = "opensearch"
  image = docker_image.opensearch.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.opensearch_main_port_external
    internal = var.opensearch_main_port_internal
  }
  ports {
    external = var.opensearch_perf_port_external
    internal = var.opensearch_perf_port_internal
  }
  env = [
    "OPENSEARCH_INITIAL_ADMIN_PASSWORD=Skillbase2024!",
    "cluster.name=opensearch-cluster",
    "node.name=opensearch-node1",
    "discovery.seed_hosts=opensearch-node1",
    "cluster.initial_master_nodes=opensearch-node1",
    "bootstrap.memory_lock=false",
    "OPENSEARCH_JAVA_OPTS=-Xms512m -Xmx512m"
  ]
  volumes {
    container_path = "/opensearch-data1"
    host_path = "${var_skillbase_system_runtime}/opensearch/data"
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

################################################################################
# Postgres
################################################################################

variable "postgres_user_name" {
  type = string
  default = "postgres"
}

variable "postgres_user_pass" {
  type = string
  default = "postgres"
}

variable "postgres_port_external" {
  type = number
  default = 5432
}

variable "postgres_port_internal" {
  type = number
  default = 5432
}

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
    external = var.postgres_port_external
    internal = var.postgres_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}

/*
################################################################################
# Prometheus
################################################################################

variable "prometheus_port_external" {
  type = number
  default = 9090
}

variable "prometheus_port_internal" {
  type = number
  default = 9090
}

resource "docker_image" "prometheus" {
  name = "prom/prometheus:latest"
  keep_locally = true
}

resource "docker_container" "prometheus" {
  name = "prometheus"
  image = docker_image.prometheus.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.prometheus_port_external
    internal = var.prometheus_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Pulsar
################################################################################

variable "pulsar_port_external" {
  type = number
  default = 9090
}

variable "pulsar_port_internal" {
  type = number
  default = 9090
}

resource "docker_image" "pulsar" {
  name = "skillbase/pulsar:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "pulsar" {
  name = "pulsar"
  image = docker_image.pulsar.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.pulsar_port_external
    internal = var.pulsar_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Redis
################################################################################

variable "redis_port_external" {
  type = number
  default = 6379
}

variable "redis_port_internal" {
  type = number
  default = 6379
}

resource "docker_image" "redis" {
  name = "skillbase/redis:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "redis" {
  name = "redis"
  image = docker_image.redis.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.redis_port_external
    internal = var.redis_port_internal
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
    external = var.docker_registry_port_external
    internal = var.docker_registry_port_internal
  }
}

/*
################################################################################
# SeaweedFS
################################################################################

variable "seaweedfs_port1_external" {
  type = number
  default = 9333
}

variable "seaweedfs_port1_internal" {
  type = number
  default = 9333
}

variable "seaweedfs_port2_external" {
  type = number
  default = 19333
}

variable "seaweedfs_port2_internal" {
  type = number
  default = 19333
}

variable "seaweedfs_port3_external" {
  type = number
  default = 9334
}

variable "seaweedfs_port3_internal" {
  type = number
  default = 9334
}

resource "docker_image" "seaweedfs" {
  name = "skillbase/seaweedfs:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "seaweedfs" {
  name = "seaweedfs"
  image = docker_image.seaweedfs.image_id
  network_mode = docker_network.private_network.name
  env = [
    "DATABASE_HOST=postgres",
    "DATABASE_NAME=skillbase",
    "DATABASE_USERNAME=postgres",
    "DATABASE_PASSWORD=postgres",
    "DATABASE_SSL=false"
  ]
  ports {
    external = var.seaweedfs_port1_external
    internal = var.seaweedfs_port1_internal
  }
  ports {
    external = var.seaweedfs_port2_external
    internal = var.seaweedfs_port2_internal
  }
  depends_on = [
    docker_container.postgres,
    docker_container.registry
  ]
    command = ["master", "-ip=master", "-ip.bind=0.0.0.0", "-metricsPort=${var.seaweedfs_port3_internal}"]
}
*/

/*
################################################################################
# Unleash
################################################################################

variable "unleash_port_external" {
  type = number
  default = 4242
}

variable "unleash_port_internal" {
  type = number
  default = 4242
}

resource "docker_image" "unleash" {
  name = "skillbase/unleash:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "unleash" {
  name = "unleash"
  image = docker_image.unleash.image_id
  network_mode = docker_network.private_network.name
  env = [
    "DATABASE_HOST=postgres",
    "DATABASE_NAME=skillbase",
    "DATABASE_USERNAME=postgres",
    "DATABASE_PASSWORD=postgres",
    "DATABASE_SSL=false"
  ]
  ports {
    external = var.unleash_port_external
    internal = var.unleash_port_internal
  }
  depends_on = [
    docker_container.postgres,
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Valkey
################################################################################

variable "valkey_port_external" {
  type = number
  default = 6379
}

variable "valkey_port_internal" {
  type = number
  default = 6379
}

resource "docker_image" "valkey" {
  name = "skillbase/valkey:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "valkey" {
  name = "valkey"
  image = docker_image.valkey.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.valkey_port_external
    internal = var.valkey_port_internal
  }
  depends_on = [
    docker_container.registry
  ]
}
*/

/*
################################################################################
# Wildfly
################################################################################

variable "wildfly_admin_port_external" {
  type = number
  default = 9990
}

variable "wildfly_admin_port_internal" {
  type = number
  default = 9990
}

variable "wildfly_http_port_external" {
  type = number
  default = 8080
}

variable "wildfly_http_port_internal" {
  type = number
  default = 8080
}

resource "docker_image" "wildfly" {
  name = "skillbase/wildfly:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "wildfly" {
  name = "wildfly"
  image = docker_image.wildfly.image_id
  network_mode = docker_network.private_network.name
  ports {
    external = var.wildfly_admin_port_external
    internal = var.wildfly_admin_port_internal
  }
  ports {
    external = var.wildfly_http_port_external
    internal = var.wildfly_http_port_internal
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

    // Caching:
    //    docker_container.memcached,
    //    docker_container.redis,
    docker_container.valkey,

    // CDC:
    //    docker_container_debezium,

    // Database:
    //    docker_container_mysql,
    docker_container.postgres,

    // Events:
    //    docker_container.kafka,
    //    docker_container.kafka_connect,
    //    docker_container.kafka_schema,
    //    docker_container.pulsar,

    // Features:
    //    docker_container.flipt,
    //    docker_container.unleash,

    // Identity:
    //    docker_container.keycloak,

    // Search:
    //    docker_container.elasticsearch,
    //    docker_container.opensearch,

    // Storage:
    //    docker_container.juicefs,
    //    docker_container.minio,
    //    docker_container.seaweedfs,

    // Workflow:
    //    docker_container.flowable,

    // Registry:
    docker_container.registry
  ]
}
*/

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
