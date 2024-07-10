package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

public class WorkflowEvent implements Serializable {

    private static URI source = URI.create("http://skillbase.com");

    public static final String WORKFLOW_MODEL_CREATED = "com.headspin.skillbase.workflow.model.created";
    public static final String WORKFLOW_MODEL_DELETED = "com.headspin.skillbase.workflow.model.deleted";
    public static final String WORKFLOW_MODEL_UPDATED = "com.headspin.skillbase.workflow.model.updated";

    public static final String WORKFLOW_PROCESS_CREATED = "com.headspin.skillbase.workflow.process.created";
    public static final String WORKFLOW_PROCESS_DELETED = "com.headspin.skillbase.workflow.process.deleted";
    public static final String WORKFLOW_PROCESS_UPDATED = "com.headspin.skillbase.workflow.process.updated";

    private UUID id;
    private String type;

    public WorkflowEvent(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public static WorkflowEvent buildEvent(UUID id, String type) {
        return new WorkflowEvent(id, type);
    }

    public static CloudEvent buildCloud(WorkflowEvent event) {
        return CloudEventBuilder
                .v1()
                .withId(String.valueOf(event.id()))
                .withType(event.type())
                .withSource(source)
                .build();
    }
}