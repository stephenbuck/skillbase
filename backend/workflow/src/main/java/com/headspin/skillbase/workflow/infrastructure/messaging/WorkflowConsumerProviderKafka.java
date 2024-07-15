package com.headspin.skillbase.workflow.infrastructure.messaging;

import java.time.Duration;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import jakarta.transaction.Transactional;
import jakarta.jms.Message;

import com.headspin.skillbase.workflow.providers.WorkflowConsumerProvider;

/**
 * Kafka implementation of workflow consumer provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
public class WorkflowConsumerProviderKafka implements WorkflowConsumerProvider {

    public WorkflowConsumerProviderKafka() {
    }

    @Override
    public void test() {
        log.info("test");
    }

    /*
    @Override
    public void onMessage(Message message) {
        service.onMessage(message);
    }
    */
    
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