package com.headspin.skillbase.identity;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.infrastructure.kafka.IdentityEventConsumerKafka;

/*
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
*/

import com.headspin.skillbase.identity.infrastructure.kafka.IdentityEventProducerKafka;

import io.cloudevents.CloudEvent;

@Slf4j
public class IdentityKafkaTest {

    @Test
    void testKafkaProduce() {
        /*
         * String ks = "127.0.0.0:9092"; String tn = "com.headspin.skillbase.identity"; Properties ps = new
         * Properties(); ps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, ks); Admin admin = Admin.create(ps);
         *
         * try (admin) {
         *
         * NewTopic nt = new NewTopic(tn, 1, (short) 1);
         *
         * CreateTopicsResult result = admin.createTopics(Collections.singleton(nt));
         *
         * KafkaFuture<Void> future = result.values().get(tn); future.get();
         *
         * } catch (InterruptedException | ExecutionException e) { log.info(e.toString()); }
         *
         * for (int i = 0; i < 5; i++) {
         *
         * CloudEvent event = IdentityEvent.build(UUID.randomUUID(), tn);
         *
         * IdentityEventProducerKafka.produce(event); }
         */
    }

    @Test
    void testKafkaConsume() {
        // IdentityEventConsumerKafka.consume();
    }
}