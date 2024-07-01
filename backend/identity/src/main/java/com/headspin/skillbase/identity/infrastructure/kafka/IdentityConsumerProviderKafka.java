package com.headspin.skillbase.identity.infrastructure.kafka;

import java.time.Duration;
import java.util.List;
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

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.providers.IdentityConsumerProvider;

/*
 * IdentityEventConsumerKafka implements the IdentityEventConsumer
 * interface using the Kafka message broker.
 */

@Slf4j
@ApplicationScoped
public class IdentityConsumerProviderKafka implements IdentityConsumerProvider {

    private KafkaConsumer<String, CloudEvent> provider;

    public IdentityConsumerProviderKafka() {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.0:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "temp-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);

        provider = new KafkaConsumer<>(props);
        provider.subscribe(List.of(IdentityEvent.IDENTITY_EVENT_USER_INSERTED));

    }

    @Override
    @Transactional
    public void consume() {
        ConsumerRecords<String, CloudEvent> records = provider.poll(Duration.ofSeconds(10));
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