package com.headspin.skillbase.catalog.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;

import java.net.InetAddress;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.message.Encoding;
import io.cloudevents.jackson.JsonCloudEventData;
import io.cloudevents.jackson.JsonFormat;
import io.cloudevents.kafka.CloudEventSerializer;

@Slf4j
@ApplicationScoped
public class CatalogEventProducerKafka implements CatalogProducerProvider {

    private final String acks_config = "all";
    private final String bootstrap_servers = "kafka:9092";
    private final String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    private final String val_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    
    private String client_id;

    public CatalogEventProducerKafka() {
        try {
            this.client_id = InetAddress.getLocalHost().getHostName();
        }
        catch(Exception e) {
            log.info(String.valueOf(e));
        }
    }

    @Override
    public void test() {
        log.info("test");
        log.info("client_id = {}", client_id);
        log.info("bootstrap_servers = {}", bootstrap_servers);
    }

    @Override
    @Transactional
    public void produce(CatalogEvent e) {

        log.info(String.valueOf(e));

        // Basic producer configuration
        Properties props = new Properties();
        props.put(ProducerConfig.ACKS_CONFIG, acks_config);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, client_id);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key_serializer);

        // Configure the CloudEventSerializer to emit events as json structured events
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, val_serializer);
        props.put(CloudEventSerializer.ENCODING_CONFIG, Encoding.STRUCTURED);
        props.put(CloudEventSerializer.EVENT_FORMAT_CONFIG, JsonFormat.CONTENT_TYPE);

        // Create the KafkaProducer
        KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props);

        // Create the JSON cloud event data
        JsonNode node = new ObjectMapper().valueToTree(e.data());
        JsonCloudEventData data = JsonCloudEventData.wrap(node);

        // Create the event
        CloudEvent event = CloudEventBuilder.v1()
            .withId(String.valueOf(e.id()))
            .withType(e.type())
            .withSource(URI.create("http://headspinlabs.wordpress.com"))
            .withData(MediaType.APPLICATION_JSON, data)
            .withTime(ZonedDateTime.now().toOffsetDateTime())
            .build();

        // Send the record
        producer.send(new ProducerRecord<>(CatalogEvent.CATALOG_EVENT_TOPIC, event));

        producer.flush();
        producer.close();

    }
}
