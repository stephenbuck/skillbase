# pushd src/docker/apicurio
# sudo docker build -t skillbase/apicurio:latest . < Dockerfile
# popd

# pushd src/docker/debezium
# sudo docker build -t skillbase/debezium:latest . < Dockerfile
# popd

pushd src/docker/elastic
sudo docker build -t skillbase/elastic:latest . < Dockerfile
popd

# pushd src/docker/flipt
# sudo docker build -t skillbase/flipt:latest . < Dockerfile
# popd

# pushd src/docker/flowable
# sudo docker build -t skillbase/flowable:latest . < Dockerfile
# popd

pushd src/docker/kafka
sudo docker build -t skillbase/kafka:latest . < Dockerfile
popd

# pushd src/docker/keycloak
# sudo docker build -t skillbase/keycloak:latest . < Dockerfile
# popd

pushd src/docker/minio
sudo docker build -t skillbase/minio:latest . < Dockerfile
popd

pushd src/docker/postgres
sudo docker build -t skillbase/postgres:latest . < Dockerfile
popd

pushd src/docker/wildfly
sudo docker build -t skillbase/wildfly:latest . < Dockerfile
popd


