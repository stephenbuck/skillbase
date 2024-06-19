package com.headspin.skillbase.user.interfaces;

import java.net.URI;
import java.util.Properties;
import java.util.logging.Logger;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.kafka.CloudEventSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.headspin.skillbase.user.domain.UserEvent;

public class UserEventProducer {

    private static final Logger logger = Logger.getLogger(UserEventConsumer.class.getName());

    public void produce(UserEvent userEvent) {
        
        logger.info("produce");
        
        Properties props = new Properties();

        // Other config props
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);

        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props)) {

            // Build an event
            CloudEvent cloudEvent = CloudEventBuilder.v1()
                    .withId("hello")
                    .withType("example.kafka")
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>("your.topic", cloudEvent));
        }
    }
}
