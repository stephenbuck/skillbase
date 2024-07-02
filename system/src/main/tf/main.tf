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
    address  = "localhost:5000"
    username = "stephenbuck"
    password = "buckstephen"
  }
}

resource "docker_network" "host" {
  name = "apisix"
  driver = "bridge"
  ipam_config {
    subnet = "172.18.0.0/16"
    ip_range = "172.18.5.0/24"
    gateway = "172.18.5.254"
  }
}

################################################################################
# ApiSix
################################################################################

resource "docker_image" "apisix" {
   name = "apache/apisix:latest"
   keep_locally = true
}

resource "docker_container" "apisix" {
  name    = "apisix"
  image   = docker_image.apisix.image_id
  restart = "always"
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

################################################################################
# Ngnix
################################################################################

resource "docker_image" "ngnix" {
  name = "nginx:1.19.0-alpine"
  keep_locally = true
}

resource "docker_container" "ngnix" {
  name    = "ngnix"
  image   = docker_image.ngnix.image_id
  restart = "always"
  env = [
    "NGINX_PORT=80"
  ]
  /*
  networks_advanced {
    name = "host"
  }
  */
  ports {
    internal = 80
    external = 9980
  }
}

################################################################################
# Etcd
################################################################################

resource "docker_image" "etcd" {
  name         = "bitnami/etcd:latest"
  keep_locally = true
}

resource "docker_container" "etcd" {
  name    = "etcd"
  image   = docker_image.etcd.image_id
  restart = "always"
  env = [
    "ETCD_ENABLE_V2=true",
    "ALLOW_NONE_AUTHENTICATION=yes",
    "ETCD_ADVERTISE_CLIENT_URLS=http://etcd:2379",
    "ETCD_LISTEN_CLIENT_URLS=http://0.0.0.0:2379"
  ]
  volumes {
    container_path = "/etcd_data"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker"
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

/*
################################################################################
# Flagd
################################################################################

# sudo docker run --rm --name flagd   -p 8013:8013   -v $(pwd):/etc/flagd   ghcr.io/open-feature/flagd:latest start --uri file:/etc/flagd/demo.flagd.json
# curl -X POST "http://localhost:8013/flagd.evaluation.v1.Service/ResolveString"   -d '{"flagKey":"background-color","context":{}}' -H "Content-Type: application/json"

resource "docker_image" "flagd" {
  name         = "ghcr.io/open-feature/flagd:latest"
  keep_locally = true
}

resource "docker_container" "flagd" {
  name    = "flagd"
  image   = docker_image.flagd.image_id
  volumes {
    container_path = "/etc/flagd"
    host_path = "/home/stephenbuck/Desktop/skillbase/backend/system/docker"
  }
  ports {
      internal = 8013
      external = 8013
  }
  command = ["start"]
}
*/

################################################################################
# Flowable
################################################################################

resource "docker_image" "flowable" {
  name         = "flowable/all-in-one:latest"
  keep_locally = true
}

resource "docker_container" "flowable" {
  name  = "flowable"
  image = docker_image.flowable.image_id
  ports {
    internal = 8080
    external = 8080
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
  name    = "fluentd"
  image   = docker_image.fluentd.image_id
  ports {
    internal = 9880
    external = 9880
  }
}
*/

################################################################################
# Kafka
################################################################################

resource "docker_image" "kafka" {
  name         = "apache/kafka:latest"
  keep_locally = true
}

resource "docker_container" "kafka" {
  name    = "kafka"
  image   = docker_image.kafka.image_id
  ports {
    internal = 9092
    external = 9092
  }
}

################################################################################
# KeyCloak
################################################################################

resource "docker_image" "keycloak" {
  name         = "quay.io/keycloak/keycloak:latest"
  keep_locally = true
}

resource "docker_container" "keycloak" {
  name    = "keycloak"
  image   = docker_image.keycloak.image_id
  env     = ["KEYCLOAK_ADMIN=admin", "KEYCLOAK_ADMIN_PASSWORD=admin"]
  ports {
    internal = 8080
    external = 18080
  }
  command = ["start-dev"]
  depends_on = [
    docker_container.postgres
  ]
}

################################################################################
# Postgres
################################################################################

resource "docker_image" "postgres" {
  name         = "postgres:latest"
  keep_locally = true
}

resource "docker_container" "postgres" {
  name  = "postgres"
  image = docker_image.postgres.image_id
  env   = [
    "POSTGRES_USER=postgres",
    "POSTGRES_PASSWORD=postgres"
  ]
  ports {
    internal = 5432
    external = 15432
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
  name    = "prometheus"
  image   = docker_image.prometheus.image_id
  ports {
    internal = 9090
    external = 9090
  }
}
*/

/*
################################################################################
# Skillbase Catalog
################################################################################

resource "docker_image" "catalog" {
  name         = "catalog"
  keep_locally = false
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
# Skillbase Certify
################################################################################

resource "docker_image" "certify" {
  name         = "certify"
  keep_locally = false
}
resource "docker_container" "certify" {
  name  = "certify"
  image = docker_image.certify.image_id
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
# Identity
################################################################################

resource "docker_image" "identity" {
  name         = "identity"
  keep_locally = false
}

resource "docker_container" "identity" {
  name  = "identity"
  image = docker_image.identity.image_id
  depends_on = [
    docker_container.etcd,
    docker_container.postgres,
    docker_container.kafka,
    docker_container.keycloak
  ]
}
*/
