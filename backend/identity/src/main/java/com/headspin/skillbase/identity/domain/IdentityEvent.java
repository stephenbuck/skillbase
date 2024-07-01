package com.headspin.skillbase.identity.domain;

import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

public class IdentityEvent {

    private static URI source = URI.create("http://skillbase.com");

    public static final String IDENTITY_EVENT_GROUP_INSERTED = "com.headspin.skillbase.identity.group.inserted";
    public static final String IDENTITY_EVENT_GROUP_DELETED = "com.headspin.skillbase.identity.group.deleted";
    public static final String IDENTITY_EVENT_GROUP_UPDATED = "com.headspin.skillbase.identity.group.updated";

    public static final String IDENTITY_EVENT_ROLE_INSERTED = "com.headspin.skillbase.identity.role.inserted";
    public static final String IDENTITY_EVENT_ROLE_DELETED = "com.headspin.skillbase.identity.role.deleted";
    public static final String IDENTITY_EVENT_ROLE_UPDATED = "com.headspin.skillbase.identity.role.updated";

    public static final String IDENTITY_EVENT_USER_INSERTED = "com.headspin.skillbase.identity.user.inserted";
    public static final String IDENTITY_EVENT_USER_DELETED = "com.headspin.skillbase.identity.user.deleted";
    public static final String IDENTITY_EVENT_USER_UPDATED = "com.headspin.skillbase.identity.user.updated";

    private UUID id;
    private String type;

    public IdentityEvent(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public static IdentityEvent buildEvent(UUID id, String type) {
        return new IdentityEvent(id, type);
    }

    public static CloudEvent buildCloud(IdentityEvent event) {
        return CloudEventBuilder.v1().withId(String.valueOf(event.id())).withType(event.type()).withSource(source)
                .build();
    }
}