FROM skillbase/wildfly:latest

WORKDIR /opt/jboss/wildfly 

COPY ./target/skillbase-backend-member-0.1.war ./standalone/deployment 

USER root

CMD ["./bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]




