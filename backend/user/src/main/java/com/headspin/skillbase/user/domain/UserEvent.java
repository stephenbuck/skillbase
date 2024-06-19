package com.headspin.skillbase.user.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.user.deleted
 * com.headspin.skillbase.user.inserted
 * com.headspin.skillbase.user.updated
 */
public class UserEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
    
}