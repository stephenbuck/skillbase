package com.headspin.skillbase.catalog.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

@Slf4j
@ApplicationScoped
public class CatalogEventProducer implements CatalogProducerProvider {

    public CatalogEventProducer() {
    }

    @Override
    public void test() {
        log.info("test");
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
