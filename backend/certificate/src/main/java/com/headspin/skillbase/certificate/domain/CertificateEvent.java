package com.headspin.skillbase.certificate.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

import org.jmolecules.event.types.DomainEvent;

/*
 * com.headspin.skillbase.certificate.deleted
 * com.headspin.skillbase.certificate.inserted
 * com.headspin.skillbase.certificate.updated
 * 
 * ERROR
 * EXPIRED
 * GRANTED
 * PAUSED
 * RESUMED
 * REVOKED
 * STARTED
 * STOPPED
 */
public class CertificateEvent implements DomainEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
    
}