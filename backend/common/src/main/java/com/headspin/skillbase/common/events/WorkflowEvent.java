package com.headspin.skillbase.common.events;

import java.util.UUID;

import jakarta.json.JsonObject;

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

public class WorkflowEvent extends SkillbaseEvent {

    public static final String WORKFLOW_EVENT_TOPIC = "skillbase_workflow_event";


    /**
     * The WORKFLOW_DEFINITION_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "definition_id": "<definition_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_DEFINITION_CREATED = "com.headspin.skillbase.workflow.definition.created";

    /**
     * The WORKFLOW_DEFINITION_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "definition_id": "<definition_id>"
     *     }
     */
    public static final String WORKFLOW_DEFINITION_DELETED = "com.headspin.skillbase.workflow.definition.deleted";

    /**
     * The WORKFLOW_DEFINITION_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "definition_id": "<definition_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_DEFINITION_UPDATED = "com.headspin.skillbase.workflow.definition.updated";


    /**
     * The WORKFLOW_DEPLOYMENT_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "deployment_id": "<deployment_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_DEPLOYMENT_CREATED = "com.headspin.skillbase.workflow.deployment.created";

    /**
     * The WORKFLOW_DEPLOYMENT_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "deployment_id": "<deployment_id>"
     *     }
     */
    public static final String WORKFLOW_DEPLOYMENT_DELETED = "com.headspin.skillbase.workflow.deployment.deleted";

    /**
     * The WORKFLOW_DEPLOYMENT_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "deployment_id": "<deployment_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_DEPLOYMENT_UPDATED = "com.headspin.skillbase.workflow.deployment.updated";


    /**
     * The WORKFLOW_INSTANCE_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "instance_id": "<instance_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_INSTANCE_CREATED = "com.headspin.skillbase.workflow.instance.created";

    /**
     * The WORKFLOW_INSTANCE_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "instance_id": "<instance_id>"
     *     }
     */
    public static final String WORKFLOW_INSTANCE_DELETED = "com.headspin.skillbase.workflow.instance.deleted";

    /**
     * The WORKFLOW_INSTANCE_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "instance_id": "<instance_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_INSTANCE_UPDATED = "com.headspin.skillbase.workflow.instance.updated";


    public static final String WORKFLOW_PROCESS_STARTED = "com.headspin.skillbase.workflow.process.started";
    public static final String WORKFLOW_PROCESS_STOPPED = "com.headspin.skillbase.workflow.process.stopped";
    public static final String WORKFLOW_PROCESS_UPDATED = "com.headspin.skillbase.workflow.process.updated";
    public static final String WORKFLOW_PROCESS_PASSED = "com.headspin.skillbase.workflow.process.passed";
    public static final String WORKFLOW_PROCESS_FAILED = "com.headspin.skillbase.workflow.process.failed";


    /**
     * The WORKFLOW_TASK_CREATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "task_id": "<task_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_TASK_CREATED = "com.headspin.skillbase.workflow.task.created";

    /**
     * The WORKFLOW_TASK_DELETED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "task_id": "<task_id>"
     *     }
     */
    public static final String WORKFLOW_TASK_DELETED = "com.headspin.skillbase.workflow.task.deleted";

    /**
     * The WORKFLOW_TASK_UPDATED event contains JSON data with the
     * following format:
     * 
     *     {
     *         "task_id": "<task_id>",
     *         "title": "<title>",
     *         "note": "<note>",
     *         "created_at": "<created_at>",
     *         "updated_at": "<updated_at>"
     *     }
     */
    public static final String WORKFLOW_TASK_UPDATED = "com.headspin.skillbase.workflow.task.updated";


    public WorkflowEvent(String type, JsonObject data) {
        this(UUID.randomUUID(), type, data);
    }

    public WorkflowEvent(UUID id, String type, JsonObject data) {
        super(id, type, data);
    }

    @Override
    public String toString() {
        return
            "WorkflowEvent {\n" +
                "id   = " + id() + "\n" +
                "type = " + type() + "\n" +
                "data = " + data() + "\n" +
            "}\n";
    }
}