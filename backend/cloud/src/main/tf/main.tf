terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.2"
    }
  }
}

provider "docker" {
  host = "unix:///var/run/docker.sock"
  registry_auth {
    address  = "localhost:5000"
    username = "stephenbuck"
    password = "buckstephen"
  }
}

resource "docker_image" "etcd" {
  name         = "quay.io/coreos/etcd:latest"
  keep_locally = true
}

resource "docker_image" "flagd" {
  name         = "ghcr.io/open-feature/flagd:latest"
  keep_locally = true
}

resource "docker_image" "flowable" {
  name         = "flowable/all-in-one:latest"
  keep_locally = true
}

resource "docker_image" "fluentd" {
  name         = "fluent/fluentd:edge-debian"
  keep_locally = true
}

resource "docker_image" "kafka" {
  name         = "apache/kafka"
  keep_locally = true
}

resource "docker_image" "keycloak" {
  name         = "quay.io/keycloak/keycloak:latest"
  keep_locally = true
}

resource "docker_image" "postgres" {
  name         = "postgres:latest"
  keep_locally = true
}

resource "docker_image" "prometheus" {
  name         = "prom/prometheus:latest"
  keep_locally = true
}

/*
resource "docker_container" "etcd" {
  name    = "etcd"
  image   = docker_image.etcd.image_id
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

/*
resource "docker_container" "flagd" {
  name    = "flagd"
  image   = docker_image.flagd.image_id
  ports {
    internal = 8013
    external = 8013
  }
  entrypoint = ["start"]
}
*/

resource "docker_container" "flowable" {
  name    = "flowable"
  image   = docker_image.flowable.image_id
  ports {
    internal = 8080
    external = 8080  
  }
}

/*
resource "docker_container" "fluentd" {
  name    = "fluentd"
  image   = docker_image.fluentd.image_id
  ports {
    internal = 9880
    external = 9880
  }
}
*/

/*
resource "docker_container" "kafka" {
  name    = "kafka"
  image   = docker_image.kafka.image_id
  ports {
    internal = 9092
    external = 9092
  }
}
*/

/*
resource "docker_container" "keycloak" {
  name    = "keycloak"
  image   = docker_image.keycloak.image_id
  env     = ["KEYCLOAK_ADMIN=admin", "KEYCLOAK_ADMIN_PASSWORD=admin"]
  ports {
    internal = 8080
    external = 18080
  }
  depends_on = [docker_container.postgres]
  command = ["start-dev"]
}
*/

resource "docker_container" "postgres" {
  name    = "postgres"
  image   = docker_image.postgres.image_id
  env     = ["POSTGRES_USER=postgres", "POSTGRES_PASSWORD=postgres"]
  ports {
    internal = 5432
    external = 15432
  }
}

/*
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
resource "docker_image" "catalog" {
  name         = "catalog"
  keep_locally = false
}

resource "docker_image" "certify" {
  name         = "certify"
  keep_locally = false
}

resource "docker_image" "identity" {
  name         = "identity"
  keep_locally = false
}

resource "docker_container" "catalog" {
  name  = "catalog"
  image = docker_image.catalog.image_id
  depends_on = [docker_container.etcd, docker_container.postgres, docker_container.kafka]
}

resource "docker_container" "certify" {
  name  = "certify"
  image = docker_image.certify.image_id
  depends_on = [docker_container.etcd, docker_container.postgres, docker_container.kafka, docker_container.flowable]
}

resource "docker_container" "identity" {
  name  = "identity"
  image = docker_image.identity.image_id
  depends_on = [docker_container.etcd, docker_container.postgres, docker_container.kafka, docker_container.keycloak]
}
*/