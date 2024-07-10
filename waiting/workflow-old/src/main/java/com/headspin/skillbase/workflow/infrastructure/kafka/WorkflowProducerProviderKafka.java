package com.headspin.groupbase.workflow.infrastructure.kafka;

import java.net.URI;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.headspin.groupbase.workflow.domain.WorkflowEvent;
import com.headspin.groupbase.workflow.providers.WorkflowProducerProvider;

@Slf4j
@ApplicationScoped
public class WorkflowProducerProviderKafka implements WorkflowProducerProvider {

    public WorkflowProducerProviderKafka() {
        log.info("producer");
    }

    @Override
    @Transactional
    public void produce(WorkflowEvent event) {

        log.info("produce");

        Properties props = new Properties();

        // Other config props
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);

        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props)) {

            // Build an event
            CloudEvent cloudEvent = CloudEventBuilder.v1().withId("workflow").withType("com.headspin.groupbase.workflow")
                    .withSource(URI.create("http://localhost")).build();

            // Produce the event
            producer.send(new ProducerRecord<>("com.headspin.groupbase.workflow", cloudEvent));
        }
    }
}
