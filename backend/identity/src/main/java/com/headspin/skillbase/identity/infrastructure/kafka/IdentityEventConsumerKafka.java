package com.headspin.skillbase.identity.infrastructure.kafka;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.kafka.CloudEventDeserializer;
import jakarta.enterprise.context.ApplicationScoped;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

@Slf4j
@ApplicationScoped
public class IdentityEventConsumerKafka {

    public static void consume() {

        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "temp-group");

        KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of("com.headspin.skillbase.identity"));

        try (consumer) {

            ConsumerRecords<String, CloudEvent> records = consumer.poll(Duration.ofSeconds(10));

            if (records.count() > 0) {
                records.forEach(rec -> {
                    if (rec != null) {
                        log.info("******************************");
                        log.info("Consume Kafka Event");
                        log.info("******************************");
                        log.info("consume = {}", rec.toString());
                    }
                });
            }
        }
    }
}