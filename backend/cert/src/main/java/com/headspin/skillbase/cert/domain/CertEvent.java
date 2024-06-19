package com.headspin.skillbase.cert.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.cert.deleted
 * com.headspin.skillbase.cert.inserted
 * com.headspin.skillbase.cert.updated
 */
public class CertEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
    
}