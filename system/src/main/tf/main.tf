################################################################################
# Variables
################################################################################

variable "skillbase_tag" {
  type    = string
  default = "latest"
}

################################################################################
# Terraform
################################################################################

# https://registry.terraform.io/providers/kreuzwerker/docker/latest/docs/resources/container

terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.2"
    }
    /*
    apisix = {
      source = "webbankir/apisix"
      version = "0.0.26"
    }
    */
  }
}

################################################################################
# Docker
################################################################################

provider "docker" {
  host = "unix:///var/run/docker.sock"
  registry_auth {
    address  = "172.17.0.1:5000"
    username = "stephenbuck"
    password = "buckstephen"
  }
}

# resource "docker_network" "skillbase" {
#   name   = "skillbase"
#   driver = "bridge"
# }

/*
################################################################################
# ApiSix
################################################################################

resource "docker_image" "apisix" {
   name = "apache/apisix:latest"
   keep_locally = true
}

resource "docker_container" "apisix" {
  name         = "apisix"
  image        = docker_image.apisix.image_id
  restart      = "always"
  env = [
    "APISIX_STAND_ALONE=true"
  ]
  ports {
    internal = 9080
    external = 9080
  }
  ports {
    internal = 9443
    external = 9443
  }
  depends_on = [
    docker_container.etcd
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
  name         = "debezium"
  image        = docker_image.debezium.image_id
  restart      = "always"
  ports {
    internal = 8085
    external = 8085
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
    docker_container.postgres,
    docker_container.kafka  
  ]
}
*/

/*
################################################################################
# Etcd
################################################################################

resource "docker_image" "etcd" {
  name         = "bitnami/etcd:latest"
  keep_locally = true
}

resource "docker_container" "etcd" {
  name         = "etcd"
  image        = docker_image.etcd.image_id
  restart      = "always"
  env = [
    "ETCD_ENABLE_V2=true",
    "ALLOW_NONE_AUTHENTICATION=yes",
    "ETCD_ADVERTISE_CLIENT_URLS=http://etcd:2379",
    "ETCD_LISTEN_CLIENT_URLS=http://0.0.0.0:2379"
  ]
  volumes {
    container_path = "/etcd_data"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker/etcd/etcd_data"
  }
  ports {
    internal = 2379
    external = 2379
  }
  ports {
    internal = 2380
    external = 2380
  }
}
*/

################################################################################
# Flipt
################################################################################

resource "docker_image" "flipt" {
  name         = "skillbase/flipt:latest"
  keep_locally = true
}

resource "docker_container" "flipt" {
  name         = "flipt"
  image        = docker_image.flipt.image_id
  stdin_open   = true
  ports {
      internal = 8080
      external = 8087
  }
  ports {
      internal = 9000
      external = 9007
  }
}


################################################################################
# Flowable
################################################################################

resource "docker_image" "flowable" {
  name         = "skillbase/flowable:latest"
  keep_locally = true
}

resource "docker_container" "flowable" {
  name  = "flowable"
  image = docker_image.flowable.image_id
  ports {
    internal = 8080
    external = 8081
  }
  depends_on = [
    docker_container.postgres
  ]
}

/*
################################################################################
# Fluentd
################################################################################

resource "docker_image" "fluentd" {
  name         = "fluent/fluentd:edge-debian"
  keep_locally = true
}

resource "docker_container" "fluentd" {
  name         = "fluentd"
  image        = docker_image.fluentd.image_id
  ports {
    internal = 9880
    external = 9880
  }
}
*/

/*
################################################################################
# Kafka
################################################################################

resource "docker_image" "kafka" {
  name         = "skillbase/kafka:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "kafka" {
  name         = "kafka"
  image        = docker_image.kafka.image_id
  env = [

    "KAFKA_ENABLE_KRAFT=yes",
    "KAFKA_PROCESS_ROLES=broker,controller",
    "KAFKA_NODE_ID=1",

    "KAFKA_KRAFT_CLUSTER_ID=1",

    "KAFKA_CFG_LISTENERS=INTERNAL://0.0.0.0:9092,BROKER://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093",
    "KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=INTERNAL:PLAINTEXT,BROKER:PLAINTEXT,CONTROLLER:PLAINTEXT",
    "KAFKA_CFG_ADVERTISED_LISTENERS=INTERNAL://localhost:9092",

    "KAFKA_CFG_INTER_BROKER_LISTENER_NAME=INTERNAL",
    
    "KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER",
    "KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@localhost:9093",
  ]
  ports {
    internal = 9092
    external = 9092
  }
  ports {
    internal = 9093
    external = 9093
  }
  ports {
    internal = 9094
    external = 9094
  }
}
*/

################################################################################
# KeyCloak
################################################################################

resource "docker_image" "keycloak" {
  name         = "quay.io/keycloak/keycloak:latest"
  keep_locally = true
}

resource "docker_container" "keycloak" {
  name  = "keycloak"
  image = docker_image.keycloak.image_id
  env = [
    "KEYCLOAK_ADMIN=admin",
    "KEYCLOAK_ADMIN_PASSWORD=admin"
  ]
  ports {
    internal = 8080
    external = 18080
  }
  depends_on = [
    docker_container.postgres
  ]
  command = ["start-dev"]
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
  name         = "nginx"
  image        = docker_image.nginx.image_id
  restart      = "always"
  env = [
    "NGINX_PORT=80"
  ]
  ports {
    internal = 80
    external = 80
  }
}
*/

################################################################################
# Postgres
################################################################################

resource "docker_image" "postgres" {
  name         = "skillbase/postgres:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "postgres" {
  name  = "postgres"
  image = docker_image.postgres.image_id
  env = [
    "POSTGRES_USER=postgres",
    "POSTGRES_PASSWORD=postgres"
  ]
  ports {
    internal = 5432
    external = 5432
  }
}

################################################################################
# Registry
################################################################################

resource "docker_image" "registry" {
  name         = "registry:latest"
  keep_locally = true
}

resource "docker_container" "registry" {
  name    = "registry"
  image   = docker_image.registry.image_id
  restart = "always"
  ports {
    internal = 5000
    external = 5000
  }
}

/*
################################################################################
# Prometheus
################################################################################

resource "docker_image" "prometheus" {
  name         = "prom/prometheus:latest"
  keep_locally = true
}

resource "docker_container" "prometheus" {
  name         = "prometheus"
  image        = docker_image.prometheus.image_id
  ports {
    internal = 9090
    external = 9090
  }
}
*/

################################################################################
# Wildfly
################################################################################

resource "docker_image" "wildfly" {
  name         = "skillbase/wildfly:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "wildfly" {
  name  = "wildfly"
  image = docker_image.wildfly.image_id
  ports {
    internal = 9990
    external = 9990
  }
  ports {
    internal = 8080
    external = 8080
  }
  host {
    host = "localhost"
    ip   = "0.0.0.0"
  }
  env = [
    "WILDFLY_BIND_INTERFACE=0.0.0.0",
    "WILDFLY_MGMT_BIND_INTERFACE=0.0.0.0"
  ]
  depends_on = [
    docker_container.postgres,
    docker_container.flipt,
  ]
}

/*
################################################################################
# Skillbase Catalog
################################################################################

resource "docker_image" "catalog" {
  name         = "skillbase/catalog:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "catalog" {
  name  = "catalog"
  image = docker_image.catalog.image_id
  depends_on = [
    docker_container.etcd,
    docker_container.postgres,
    docker_container.kafka
  ]
}
*/

/*
################################################################################
# Skillbase Workflow
################################################################################

resource "docker_image" "workflow" {
  name         = "skillbase/workflow:${var.skillbase_tag}"
  keep_locally = true
}
resource "docker_container" "workflow" {
  name  = "workflow"
  image = docker_image.workflow.image_id
  depends_on = [
    docker_container.etcd,
    docker_container.postgres,
    docker_container.kafka,
    docker_container.flowable
  ]
}
*/

/*
################################################################################
# Member
################################################################################

resource "docker_image" "member" {
  name         = "skillbase/member:${var.skillbase_tag}"
  keep_locally = true
}

resource "docker_container" "member" {
  name  = "member"
  image = docker_image.member.image_id
  depends_on = [
    docker_container.etcd,
    docker_container.postgres,
    docker_container.kafka,
    docker_container.keycloak
  ]
}
*/
