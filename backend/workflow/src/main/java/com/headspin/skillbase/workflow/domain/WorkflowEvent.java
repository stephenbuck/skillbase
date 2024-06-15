package com.headspin.skillbase.workflow.domain;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

import org.jmolecules.event.types.DomainEvent;

/*
 * com.headspin.skillbase.workflow.deleted
 * com.headspin.skillbase.workflow.inserted
 * com.headspin.skillbase.workflow.updated
 */
public class WorkflowEvent implements DomainEvent {

    public static CloudEvent build(String type) {
        return CloudEventBuilder.v1()
                .withId("000")
                .withType(type)
                .build();
    }
    
}