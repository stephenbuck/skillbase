package com.headspin.skillbase.catalog.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.net.InetAddress;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

@Slf4j
@ApplicationScoped
public class CatalogEventProducerKafka implements CatalogProducerProvider {

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.catalog.kafka.url")
    private String url;

    public CatalogEventProducerKafka() {
    }

    @Override
    public void test() {
        log.info("test-1");
        log.info("url = {}", url);
        try {
            Properties config = new Properties();
            config.put(ProducerConfig.CLIENT_ID_CONFIG, InetAddress.getLocalHost().getHostName());
            config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
            config.put(ProducerConfig.ACKS_CONFIG, "all");
            config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config)) {
                producer.send(new ProducerRecord<String, String>(CatalogEvent.CATALOG_EVENT_TOPIC, 1, "TBD", "Hello World"));
                producer.flush();
            }
        }
        catch (Exception e) {
            log.info(String.valueOf(e));
        }
    }

    @Override
    @Transactional
    public void produce(CatalogEvent event) {
        log.info(String.valueOf(event));
    }

    /*
    @Override
    public void produce(CatalogEvent event) {

        log.info("produce");

        Properties props = new Properties();

        // Other config props
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);

        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props)) {

            // Build an event
            CloudEvent cloudEvent = CloudEventBuilder.v1()
                    .withId(String.valueOf(UUID.randomUUID()))
                    .withType(CatalogEvent.CATALOG_EVENT_TOPIC)
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>(CatalogEvent.CATALOG_EVENT_TOPIC, cloudEvent));
        }
    }
    */
}
