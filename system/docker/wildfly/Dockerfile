FROM quay.io/wildfly/wildfly:latest

USER jboss

WORKDIR /opt/jboss/wildfly 

RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.3/postgresql-42.7.3.jar 

COPY batch.cli .

COPY catalog.war .

RUN ./bin/jboss-cli.sh --file=./batch.cli

RUN ./bin/add-user.sh admin admin --silent

RUN rm -rf ./standalone/configuration/standalone_xml_history
RUN rm -rf ./postgresql-42.7.3.jar

USER root
 
CMD ["./bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


