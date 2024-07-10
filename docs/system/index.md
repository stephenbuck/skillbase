
Overview:

This Skillbase application runs locally or in the cloud as a set of Docker containers. In this version, the containers are deployed to local Docker hosts using Terraform. In future versions, the containers will be deployed to the cloud using Kubernetes.


Containers (Docker)

Containers are here to stay, and Docker is the gold standard. Having a good pipeline from the build system to a Docker system will help with making the application available in the “cloud” and when used with an orchestration system like Kubernetes, should help enormously with scaling the system up. There aren’t any alternatives that are as popular and flexible as Docker, so this is a keeper.


Infrastructure (Terraform, Kubernetes)

Terraform is an awesome "infrastructure as code" tool. I'm currently using it during development to populate my Docker instance with my containers. It will be a good segue into Kubernetes in future phases.

