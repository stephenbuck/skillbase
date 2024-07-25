package com.headspin.skillbase.common.events;

import com.headspin.skillbase.common.domain.DomainEvent;

import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import jakarta.ws.rs.core.MediaType;

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
 *   WORKFLOW_PROCESS_STARTED:
 *   WORKFLOW_PROCESS_STOPPED:
 *   WORKFLOW_PROCESS_UPDATED
 *   WORKFLOW_PROCESS_PASSED:
 *   WORKFLOW_PROCESS_FAILED:
 * 
 *   WORKFLOW_TASK_CREATED
 *   WORKFLOW_TASK_DELETED
 *   WORKFLOW_TASK_UPDATED
 *
 * @author Stephen Buck
 * @since 1.0
 */

public class WorkflowEvent extends DomainEvent {

    private static URI source = URI.create("http://skillbase.com");

    public static final String WORKFLOW_EVENT_TOPIC = "skillbase_workflow_event";

    public static final String WORKFLOW_DEFINITION_CREATED = "com.headspin.skillbase.workflow.definition.created";
    public static final String WORKFLOW_DEFINITION_DELETED = "com.headspin.skillbase.workflow.definition.deleted";
    public static final String WORKFLOW_DEFINITION_UPDATED = "com.headspin.skillbase.workflow.definition.updated";

    public static final String WORKFLOW_DEPLOYMENT_CREATED = "com.headspin.skillbase.workflow.deployment.created";
    public static final String WORKFLOW_DEPLOYMENT_DELETED = "com.headspin.skillbase.workflow.deployment.deleted";
    public static final String WORKFLOW_DEPLOYMENT_UPDATED = "com.headspin.skillbase.workflow.deployment.updated";

    public static final String WORKFLOW_INSTANCE_CREATED = "com.headspin.skillbase.workflow.instance.created";
    public static final String WORKFLOW_INSTANCE_DELETED = "com.headspin.skillbase.workflow.instance.deleted";
    public static final String WORKFLOW_INSTANCE_UPDATED = "com.headspin.skillbase.workflow.instance.updated";

    public static final String WORKFLOW_PROCESS_STARTED = "com.headspin.skillbase.workflow.process.started";
    public static final String WORKFLOW_PROCESS_STOPPED = "com.headspin.skillbase.workflow.process.stopped";
    public static final String WORKFLOW_PROCESS_UPDATED = "com.headspin.skillbase.workflow.process.updated";
    public static final String WORKFLOW_PROCESS_PASSED = "com.headspin.skillbase.workflow.process.passed";
    public static final String WORKFLOW_PROCESS_FAILED = "com.headspin.skillbase.workflow.process.failed";

    public static final String WORKFLOW_TASK_CREATED = "com.headspin.skillbase.workflow.task.created";
    public static final String WORKFLOW_TASK_DELETED = "com.headspin.skillbase.workflow.task.deleted";
    public static final String WORKFLOW_TASK_UPDATED = "com.headspin.skillbase.workflow.task.updated";

    private final UUID id;
    private final String type;
    private final Object data;

    public WorkflowEvent(UUID id, String type, Object data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public Object data() {
        return this.data;
    }

    public static WorkflowEvent buildEvent(UUID id, String type, Object data) {
        return new WorkflowEvent(id, type, data);
    }

    public static CloudEvent buildCloud(WorkflowEvent event) {
        return CloudEventBuilder
                .v1()
                .withId(String.valueOf(event.id()))
                .withType(event.type())
                .withSource(source)
                .withData(MediaType.APPLICATION_JSON, String.valueOf(event.data).getBytes())
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