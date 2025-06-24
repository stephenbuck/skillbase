
*** Work in Progress ***

This iteration of the Skillbase system is designed to run locally as
a set of Docker containers

Instructions:

* Type 'sudo mvn tf:apply' to apply terraform plan
* Type 'sudo mvn tf:destroy' to destroy terraform plan
* Type 'sudo docker ps' to see container status
* Type 'psql -d skillbase -U postgres -h localhost -p 15432' for PostgreSQL tool (password is 'postgres')
* Open 'http://localhost:9013' for ApiSix tool
* Open 'http://localhost:8080/flowable-admin' for Flowable tool
* Open 'http://localhost:8080/admin' for Keycloak tool (admin:admin)
* Open 'http://localhost:9080' for Ngnix tool
* Open 'http://127.0.0.1:9001/login' for MinIO tool (skillbase:skillbase)

Links:

https://apisix.apache.org
https://debezium.io
https://etcd.io
https://flagd.io
https://fluentd.org
https://grafana.com
https://kubernetes.io
https://nginx.org
https://prometheus.io
https://redis.io
https://terraform.io
https://github.com/deliveredtechnologies/terraform-maven
