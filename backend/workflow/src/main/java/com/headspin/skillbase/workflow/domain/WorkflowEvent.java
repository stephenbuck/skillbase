package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

/**
 * Represents a workflow event.
 * 
 *   WORKFLOW_DEFINITION_CREATED
 *   WORKFLOW_DEFINITION_DELETED
 *   WORKFLOW_DEFINITION_UPDATED
 *
 *   WORKFLOW_DEPLOYMENT_CREATED
 *   WORKFLOW_DEPLOYMENT_DELETED
 *   WORKFLOW_DEPLOYMENT_UPDATED
 *
 *   WORKFLOW_INSTANCE_CREATED
 *   WORKFLOW_INSTANCE_DELETED
 *   WORKFLOW_INSTANCE_UPDATED
 * 
 *   WORKFLOW_TASK_CREATED
 *   WORKFLOW_TASK_DELETED
 *   WORKFLOW_TASK_UPDATED
 *
 * @author Stephen Buck
 * @since 1.0
 */

public class WorkflowEvent implements Serializable {

    private static URI source = URI.create("http://skillbase.com");

    public static final String WORKFLOW_DEFINITION_CREATED = "com.headspin.skillbase.workflow.definition.created";
    public static final String WORKFLOW_DEFINITION_DELETED = "com.headspin.skillbase.workflow.definition.deleted";
    public static final String WORKFLOW_DEFINITION_UPDATED = "com.headspin.skillbase.workflow.definition.updated";

    public static final String WORKFLOW_DEPLOYMENT_CREATED = "com.headspin.skillbase.workflow.deployment.created";
    public static final String WORKFLOW_DEPLOYMENT_DELETED = "com.headspin.skillbase.workflow.deployment.deleted";
    public static final String WORKFLOW_DEPLOYMENT_UPDATED = "com.headspin.skillbase.workflow.deployment.updated";

    public static final String WORKFLOW_INSTANCE_CREATED = "com.headspin.skillbase.workflow.instance.created";
    public static final String WORKFLOW_INSTANCE_DELETED = "com.headspin.skillbase.workflow.instance.deleted";
    public static final String WORKFLOW_INSTANCE_UPDATED = "com.headspin.skillbase.workflow.instance.updated";

    public static final String WORKFLOW_TASK_CREATED = "com.headspin.skillbase.workflow.task.created";
    public static final String WORKFLOW_TASK_DELETED = "com.headspin.skillbase.workflow.task.deleted";
    public static final String WORKFLOW_TASK_UPDATED = "com.headspin.skillbase.workflow.task.updated";

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

    @Override
    public String toString() {
        return
            "WorkflowEvent {\n" +
                "id   = " + id + "\n" +
                "type = " + type + "\n" +
            "}\n";
    }
}