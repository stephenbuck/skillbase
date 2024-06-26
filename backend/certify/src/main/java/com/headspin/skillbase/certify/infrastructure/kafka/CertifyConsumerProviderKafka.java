package com.headspin.skillbase.certify.infrastructure.kafka;

import java.time.Duration;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.kafka.CloudEventDeserializer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.headspin.skillbase.certify.providers.CertifyConsumerProvider;

@Slf4j
@ApplicationScoped
public class CertifyConsumerProviderKafka implements CertifyConsumerProvider {

    public CertifyConsumerProviderKafka() {
        log.info("consumer");
    }

    @Override
    @Transactional
    public void consume() {

        log.info("consume");

        Properties props = new Properties();

        // Other config props
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);

        try (KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(props)) {

            ConsumerRecords<String, CloudEvent> records = consumer.poll(Duration.ofSeconds(10));

            records.forEach(rec -> {
                // log.info(rec.value().toString());
            });
        }
    }
}