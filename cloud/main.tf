terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.1"
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

resource "docker_image" "kafka" {
  name         = "apache/kafka"
  keep_locally = false
}

resource "docker_image" "postgres" {
  name         = "postgres:latest"
  keep_locally = false
}

resource "docker_image" "catalog" {
  name         = "catalog"
  keep_locally = false
}

/*
resource "docker_image" "certify" {
  name         = "certify"
  keep_locally = false
}

resource "docker_image" "identity" {
  name         = "identity"
  keep_locally = false
}
*/

resource "docker_container" "kafka" {
  name  = "kafka"
  image = docker_image.kafka.image_id
  ports {
    internal = 6673
    external = 6673
  }
  ports {
    internal = 16673
    external = 16673
  }
}

resource "docker_container" "postgres" {
  name  = "postgres"
  image = docker_image.postgres.image_id

  env = ["POSTGRES_USER=postgres", "POSTGRES_PASSWORD=postgres"]

  ports {
    internal = 6433
    external = 6433
  }
}

resource "docker_container" "catalog" {
  name  = "catalog"
  image = docker_image.catalog.image_id
}

/*
resource "docker_container" "certify" {
  name  = "certify"
  image = docker_image.certify.image_id
}

resource "docker_container" "identity" {
  name  = "identity"
  image = docker_image.identity.image_id
}
*/

