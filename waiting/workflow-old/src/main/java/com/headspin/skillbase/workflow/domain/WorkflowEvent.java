package com.headspin.groupbase.workflow.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

public class WorkflowEvent implements Serializable {

    private static URI source = URI.create("http://groupbase.com");

    public static final String WORKFLOW_CERT_CREATED = "com.headspin.groupbase.workflow.cert.created";
    public static final String WORKFLOW_CERT_DELETED = "com.headspin.groupbase.workflow.cert.deleted";
    public static final String WORKFLOW_CERT_UPDATED = "com.headspin.groupbase.workflow.cert.updated";

    public static final String WORKFLOW_DOCUMENT_CREATED = "com.headspin.groupbase.workflow.document.created";
    public static final String WORKFLOW_DOCUMENT_DELETED = "com.headspin.groupbase.workflow.document.deleted";
    public static final String WORKFLOW_DOCUMENT_UPDATED = "com.headspin.groupbase.workflow.document.updated";

    public static final String WORKFLOW_MODEL_CREATED = "com.headspin.groupbase.workflow.model.created";
    public static final String WORKFLOW_MODEL_DELETED = "com.headspin.groupbase.workflow.model.deleted";
    public static final String WORKFLOW_MODEL_UPDATED = "com.headspin.groupbase.workflow.model.updated";

    public static final String WORKFLOW_PROCESS_CREATED = "com.headspin.groupbase.workflow.process.created";
    public static final String WORKFLOW_PROCESS_DELETED = "com.headspin.groupbase.workflow.process.deleted";
    public static final String WORKFLOW_PROCESS_UPDATED = "com.headspin.groupbase.workflow.process.updated";

    public static final String WORKFLOW_TASK_CREATED = "com.headspin.groupbase.workflow.task.created";
    public static final String WORKFLOW_TASK_DELETED = "com.headspin.groupbase.workflow.task.deleted";
    public static final String WORKFLOW_TASK_UPDATED = "com.headspin.groupbase.workflow.task.updated";

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