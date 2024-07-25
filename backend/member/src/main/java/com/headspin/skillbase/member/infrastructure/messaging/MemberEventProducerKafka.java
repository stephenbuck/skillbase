package com.headspin.skillbase.member.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.net.InetAddress;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

@Slf4j
@ApplicationScoped
public class MemberEventProducerKafka implements MemberProducerProvider {

    private final String acks_config = "all";
    private final String bootstrap_servers = "kafka:9092";
    private final String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    private final String val_serializer = "org.apache.kafka.common.serialization.StringSerializer";

    private String client_id;

    public MemberEventProducerKafka() {
        try {
            this.client_id = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e) {
            log.info(String.valueOf(e));
        }
    }

    @Override
    public void test() {
        log.info("test");
    }

    @Override
    @Transactional
    public void produce(MemberEvent event) {
        log.info(String.valueOf(event));
        Properties config = new Properties();
        config.put(ProducerConfig.ACKS_CONFIG, acks_config);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, client_id);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key_serializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, val_serializer);
        try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config)) {
            producer.send(new ProducerRecord<String, String>(MemberEvent.MEMBER_EVENT_TOPIC, 1, "TBD", "Hello World"));
            producer.flush();
        }
    }
}
