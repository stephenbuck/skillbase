package com.headspin.skillbase.certify.domain;

import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.certify.deleted
 * com.headspin.skillbase.certify.inserted
 * com.headspin.skillbase.certify.updated
 */
public class CertifyEvent {

    public static CloudEvent build(UUID id, String type) {
        return CloudEventBuilder.v1().withId("000").withType(type).build();
    }

}