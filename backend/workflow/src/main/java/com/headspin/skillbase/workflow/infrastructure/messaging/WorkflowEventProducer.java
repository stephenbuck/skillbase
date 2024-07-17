package com.headspin.skillbase.workflow.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

/**
 * Kafka implementation of workflow producer provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowEventProducer implements WorkflowProducerProvider {

    public WorkflowEventProducer() {
    }

    @Override
    public void test() {
        log.info("test");
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
            CloudEvent cloudEvent = CloudEventBuilder.v1()
                    .withId(String.valueOf(UUID.randomUUID()))
                    .withType(WorkflowEvent.WORKFLOW_EVENT_TOPIC)
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>(WorkflowEvent.WORKFLOW_EVENT_TOPIC, cloudEvent));
        }
    }
    */
}
