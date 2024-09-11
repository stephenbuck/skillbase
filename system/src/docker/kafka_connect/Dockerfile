FROM quay.io/debezium/connect:latest

COPY ./connector.json ./connector.json

RUN confluent connect plugin install --force ./connector.json
