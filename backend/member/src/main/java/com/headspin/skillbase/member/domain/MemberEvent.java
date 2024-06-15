package com.headspin.skillbase.member.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

import org.jmolecules.event.types.DomainEvent;

/*
 * com.headspin.skillbase.member.deleted
 * com.headspin.skillbase.member.inserted
 * com.headspin.skillbase.member.updated
 */
public class MemberEvent implements DomainEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
    
}