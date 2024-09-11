# pushd src/docker/apicurio
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/apicurio:latest ${PWD} 
# popd

# pushd src/docker/debezium
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/debezium:latest ${PWD} 
# popd

pushd src/docker/elastic
sudo docker build -f ${PWD}/Dockerfile -t skillbase/elastic:latest ${PWD} 
popd

# pushd src/docker/flipt
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/flipt:latest ${PWD} 
# popd

# pushd src/docker/flowable
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/flowable:latest ${PWD}
# popd

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

# pushd src/docker/keycloak
# sudo docker build -f ${PWD}/Dockerfile -t skillbase/keycloak:latest ${PWD} 
# popd

pushd src/docker/memcached
sudo docker build -f ${PWD}/Dockerfile -t skillbase/memcached:latest ${PWD} 
popd

pushd src/docker/minio
sudo docker build -f ${PWD}/Dockerfile -t skillbase/minio:latest ${PWD} 
popd

pushd src/docker/postgres
sudo docker build -f ${PWD}/Dockerfile -t skillbase/postgres:latest ${PWD}

pushd src/docker/redis
sudo docker build -f ${PWD}/Dockerfile -t skillbase/redis:latest ${PWD}

pushd src/docker/wildfly
sudo docker build -f ${PWD}/Dockerfile -t skillbase/wildfly:latest ${PWD} 
popd


