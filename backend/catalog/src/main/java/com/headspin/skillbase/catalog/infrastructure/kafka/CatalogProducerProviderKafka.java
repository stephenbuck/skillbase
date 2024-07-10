package com.headspin.skillbase.catalog.infrastructure.kafka;

import java.net.URI;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Topic;
import jakarta.transaction.Transactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.headspin.skillbase.catalog.domain.CatalogEvent;
import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;

@Slf4j
@ApplicationScoped
public class CatalogProducerProviderKafka implements CatalogProducerProvider {

//    @Resource(lookup = "java:/KafkaConnectionFactory")
//    private static ConnectionFactory connectionFactory;

//    @Resource(lookup = "java:/jms/topic/com.headspin.skillbase.catalog.event")
//    private static Topic topic;

    public CatalogProducerProviderKafka() {
    }

    @Override
    @Transactional
    public void produce(CatalogEvent event) {

        log.info("$$$$$$$$$$$$$$$ produce()");
        log.info(String.valueOf(event));
        
//        try (JMSContext context = connectionFactory.createContext();) {
//            JMSProducer producer = context.createProducer();
//            producer.send(topic, event);
//        }
        
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
            CloudEvent cloudEvent = CloudEventBuilder.v1().withId("catalog")
                    .withType("com.headspin.skillbase.catalog.event")
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>("com.headspin.skillbase.catalog.event", cloudEvent));
        }
    }
    */
}
