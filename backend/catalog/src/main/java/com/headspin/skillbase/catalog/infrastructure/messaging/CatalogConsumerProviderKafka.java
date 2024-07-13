package com.headspin.skillbase.catalog.infrastructure.messaging;

import java.time.Duration;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.catalog.providers.CatalogConsumerProvider;

@Slf4j
public class CatalogConsumerProviderKafka implements CatalogConsumerProvider {

    public CatalogConsumerProviderKafka() {
    }

    @Override
    public void test() {
        log.info("test");
    }

    @Override
    @Transactional
    public void consume() {

        log.info("consume");
        /*
        Properties props = new Properties();

        // Other config props
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);

        try (KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(props)) {

            ConsumerRecords<String, CloudEvent> records = consumer.poll(Duration.ofSeconds(10));

            records.forEach(rec -> {
                log.info(rec.value().toString());
            });
        }
        */
    }
}