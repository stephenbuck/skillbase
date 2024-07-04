package com.headspin.skillbase.identity;

/*
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
*/
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.infrastructure.kafka.IdentityConsumerProviderKafka;
import com.headspin.skillbase.identity.infrastructure.kafka.IdentityProducerProviderKafka;
import com.headspin.skillbase.identity.providers.IdentityConsumerProvider;
import com.headspin.skillbase.identity.providers.IdentityProducerProvider;

@Disabled

@Slf4j
/*
@ExtendWith(WeldJunit5AutoExtension.class)
@AddPackages(IdentityConsumerProvider.class)
@AddPackages(IdentityProducerProvider.class)
@AddPackages(IdentityConsumerProviderKafka.class)
@AddPackages(IdentityProducerProviderKafka.class)
*/
public class IdentityProducerProviderTest {
/*
    // @Inject
    // private IdentityEventProducer producer;
    private IdentityProducerProvider producer = new IdentityProducerProviderKafka();

    // @Inject
    // private IdentityEventConsumer consumer;
    private IdentityConsumerProvider consumer = new IdentityConsumerProviderKafka();

    @BeforeAll
    public static void beforeAll() {
        log.info("identity");
    }

    @Test
    public void testProvider() {
        assertNotNull(producer, "Provider not found");
        assertNotNull(consumer, "Provider not found");
    }

    @Test
    void testProducer() {
        for (int i = 0; i < 5; i++) {
            UUID id = UUID.randomUUID();
            producer.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_USER_INSERTED));
            producer.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_USER_UPDATED));
            producer.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_USER_DELETED));
        }
    }

    @Test
    void testConsumer() {
        consumer.consume();
    }
*/
}