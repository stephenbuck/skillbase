package com.headspin.skillbase.identity.infrastructure.kafka;

import java.net.URI;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.enterprise.context.ApplicationScoped;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.headspin.skillbase.identity.domain.IdentityEvent;

@Slf4j
@ApplicationScoped
public class IdentityEventProducerKafka {

    public static void produce(CloudEvent event) {

        Properties props = new Properties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props);

        try (producer) {

            CloudEvent cloudEvent = CloudEventBuilder.v1().withId("identity").withSource(URI.create("http://127.0.0.0"))
                    .withType("com.headspin.skillbase.identity").build();

            log.info("******************************");
            log.info("Produce Kafka Event");
            log.info("******************************");
            producer.send(new ProducerRecord<>("com.headspin.skillbase.identity", cloudEvent));
        }
    }
}
