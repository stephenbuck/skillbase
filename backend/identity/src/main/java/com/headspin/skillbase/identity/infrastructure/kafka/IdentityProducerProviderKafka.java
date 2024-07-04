package com.headspin.skillbase.identity.infrastructure.kafka;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.providers.IdentityProducerProvider;

/*
 * IdentityEventProducerKafka implements the IdentityEventProducer
 * interface using the Kafka message broker.
 */

@Slf4j
@ApplicationScoped
public class IdentityProducerProviderKafka implements IdentityProducerProvider {

    private KafkaProducer<String, CloudEvent> producer;

    public IdentityProducerProviderKafka() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
        producer = new KafkaProducer<>(props);
    }

    @Override
    @Transactional
    public void produce(IdentityEvent event) {
        log.info("******************************");
        log.info("Produce Kafka Event");
        log.info("******************************");
        CloudEvent cloud = IdentityEvent.buildCloud(event);
        producer.send(new ProducerRecord<String, CloudEvent>(event.type(), cloud));
    }
}
