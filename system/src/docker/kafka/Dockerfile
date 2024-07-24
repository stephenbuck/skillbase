FROM bitnami/kafka:latest

RUN /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic skillbase_catalog_event --bootstrap-server localhost:9092
RUN /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic skillbase_member_event --bootstrap-server localhost:9092
RUN /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic skillbase_workflow_event --bootstrap-server localhost:9092
