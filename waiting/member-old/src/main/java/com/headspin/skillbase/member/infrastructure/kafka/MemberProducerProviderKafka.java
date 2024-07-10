package com.headspin.skillbase.member.infrastructure.kafka;

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

import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

/*
 * MemberEventProducerKafka implements the MemberEventProducer
 * interface using the Kafka message broker.
 */

@Slf4j
@ApplicationScoped
public class MemberProducerProviderKafka implements MemberProducerProvider {

    public MemberProducerProviderKafka() {
    }

    /*
    private KafkaProducer<String, CloudEvent> producer;

    public MemberProducerProviderKafka() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "skillbase");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);
        producer = new KafkaProducer<>(props);
    }
    */

    @Override
    @Transactional
    public void produce(MemberEvent event) {
        log.info("******************************");
        log.info("Produce Kafka Event");
        log.info("******************************");
        CloudEvent cloud = MemberEvent.buildCloud(event);
    //    producer.send(new ProducerRecord<String, CloudEvent>(event.type(), cloud));
    }
}
