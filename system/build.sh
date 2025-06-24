# pushd src/docker/apicurio
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/apicurio:latest ${PWD} 
# popd

pushd src/docker/consul
sudo docker build -f ${PWD}/Dockerfile -t skillbase/consul:latest ${PWD} 
popd

pushd src/docker/debezium
sudo docker build -f ${PWD}/Dockerfile -t skillbase/debezium:latest ${PWD} 
popd

pushd src/docker/elasticsearch
sudo docker build -f ${PWD}/Dockerfile -t skillbase/elasticsearch:latest ${PWD} 
popd

pushd src/docker/etcd
sudo docker build -f ${PWD}/Dockerfile -t skillbase/etcd:latest ${PWD} 
popd

pushd src/docker/flipt
sudo docker build -f ${PWD}/Dockerfile -t skillbase/flipt:latest ${PWD} 
popd

pushd src/docker/flowable
sudo docker build -f ${PWD}/Dockerfile -t skillbase/flowable:latest ${PWD}
popd

pushd src/docker/fluentd
sudo docker build -f ${PWD}/Dockerfile -t skillbase/fluentd:latest ${PWD}
popd

pushd src/docker/juicefs
sudo docker build -f ${PWD}/Dockerfile -t skillbase/juicefs:latest ${PWD}
popd

pushd src/docker/kafka
sudo docker build -f ${PWD}/Dockerfile -t skillbase/kafka:latest ${PWD} 
popd

pushd src/docker/kafka_connect
sudo docker build -f ${PWD}/Dockerfile -t skillbase/kafka_connect:latest ${PWD} 
popd

pushd src/docker/kafka_schema
sudo docker build -f ${PWD}/Dockerfile  -t skillbase/kafka_schema:latest ${PWD} 
popd

pushd src/docker/kafka_ui
sudo docker build -f ${PWD}/Dockerfile -t skillbase/kafka_ui:latest ${PWD} 
popd

pushd src/docker/keycloak
sudo docker build -f ${PWD}/Dockerfile -t skillbase/keycloak:latest ${PWD} 
popd

pushd src/docker/memcached
sudo docker build -f ${PWD}/Dockerfile -t skillbase/memcached:latest ${PWD} 
popd

pushd src/docker/minio
sudo docker build -f ${PWD}/Dockerfile -t skillbase/minio:latest ${PWD} 
popd

pushd src/docker/mysql
sudo docker build -f ${PWD}/Dockerfile -t skillbase/mysql:latest ${PWD} 
popd

pushd src/docker/nginx
sudo docker build -f ${PWD}/Dockerfile -t skillbase/nginx:latest ${PWD} 
popd

pushd src/docker/opensearch
sudo docker build -f ${PWD}/Dockerfile -t skillbase/opensearch:latest ${PWD}
popd

pushd src/docker/postgres
sudo docker build -f ${PWD}/Dockerfile -t skillbase/postgres:latest ${PWD}
popd

pushd src/docker/pulsar
sudo docker build -f ${PWD}/Dockerfile -t skillbase/pulsar:latest ${PWD}
popd

pushd src/docker/redis
sudo docker build -f ${PWD}/Dockerfile -t skillbase/redis:latest ${PWD}
popd

pushd src/docker/seaweedfs
sudo docker build -f ${PWD}/Dockerfile -t skillbase/seaweedfs:latest ${PWD}
popd

pushd src/docker/unleash
sudo docker build -f ${PWD}/Dockerfile -t skillbase/unleash:latest ${PWD}
popd

pushd src/docker/valkey
sudo docker build -f ${PWD}/Dockerfile -t skillbase/valkey:latest ${PWD}
popd

pushd src/docker/wildfly
sudo docker build -f ${PWD}/Dockerfile -t skillbase/wildfly:latest ${PWD} 
popd


