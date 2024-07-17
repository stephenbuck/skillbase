package com.headspin.skillbase.member.infrastructure.messaging;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

@Slf4j
@ApplicationScoped
public class MemberEventProducer implements MemberProducerProvider {

//    @Resource(lookup = "java:/KafkaConnectionFactory")
//    private static ConnectionFactory connectionFactory;

//    @Resource(lookup = "java:/jms/topic/com.headspin.skillbase.member.event")
//    private static Topic topic;

    public MemberEventProducer() {
    }

    @Override
    public void test() {
        log.info("test");
    }

    @Override
    @Transactional
    public void produce(MemberEvent event) {

        log.info("$$$$$$$$$$$$$$$ produce()");
        log.info(String.valueOf(event));
        
//        try (JMSContext context = connectionFactory.createContext();) {
//            JMSProducer producer = context.createProducer();
//            producer.send(topic, event);
//        }
        
    }

    /*
    @Override
    public void produce(MemberEvent event) {

        log.info("produce");

        Properties props = new Properties();

        // Other config props
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CloudEventSerializer.class);

        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(props)) {

            // Build an event
            CloudEvent cloudEvent = CloudEventBuilder.v1()
                    .withId(String.valueOf(UUID.randomUUID()))
                    .withType(MemberEvent.MEMBER_EVENT_TOPIC)
                    .withSource(URI.create("http://localhost"))
                    .build();

            // Produce the event
            producer.send(new ProducerRecord<>(MemberEvent.MEMBER_EVENT_TOPIC, cloudEvent));
        }
    }
    */
}
