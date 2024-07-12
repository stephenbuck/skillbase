package com.headspin.skillbase.workflow.infrastructure.kafka;

import java.net.URI;
import java.util.Base64;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;

import com.headspin.skillbase.workflow.domain.WorkflowEvent;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

@Slf4j
@ApplicationScoped
public class WorkflowProducerProviderKafka implements WorkflowProducerProvider {

    public WorkflowProducerProviderKafka() {
    }

    @Override
    @Transactional
    public void produce(WorkflowEvent event) {

        log.info(String.valueOf(event));

/*
try {

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target("http://172.17.0.1:8081/flowable-rest");
        WebTarget info = base.path("service/repository/deployments");

        String credentials = "rest-admin:test";
        String base64encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

        String result = info
//            .path("{id}")
//            .queryParam("foo", "bar")
            .request(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "Basic " + base64encoded)
            .get(String.class);


        log.info("================================");
        log.info("result = {}", result);
        log.info("================================");
}
catch (Exception e) {
    log.info(String.valueOf(e));
}
*/
        
//        try (JMSContext context = connectionFactory.createContext();) {
//            JMSProducer producer = context.createProducer();
//            producer.send(topic, event);
//        }
        
    }

    /*
    @Override
    public void produce(WorkflowEvent event) {

        log.info("produce");

        Properties props = new Properties();

        // Other config props
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);

        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props)) {

            // Build an event
            CloudEvent cloudEvent = CloudEventBuilder.v1().withId("workflow")
                    .withType("com.headspin.skillbase.workflow.event")
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>("com.headspin.skillbase.workflow.event", cloudEvent));
        }
    }
    */
}
