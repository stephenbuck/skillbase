package com.headspin.skillbase.identity.domain;

import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/*
 * com.headspin.skillbase.identity.group.deleted
 * com.headspin.skillbase.identity.group.inserted
 * com.headspin.skillbase.identity.group.updated
 * com.headspin.skillbase.identity.role.deleted
 * com.headspin.skillbase.identity.role.inserted
 * com.headspin.skillbase.identity.role.updated
 * com.headspin.skillbase.identity.user.deleted
 * com.headspin.skillbase.identity.user.inserted
 * com.headspin.skillbase.identity.user.updated
 */
public class IdentityEvent {

    public static CloudEvent build(UUID id, String type) {
        return CloudEventBuilder.v1().withId("000").withType(type).build();
    }

}