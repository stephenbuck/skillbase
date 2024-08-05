package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowInstanceRepo;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeaturesProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEventsProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Workflow instances service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowInstancesService {

    @Resource
    private SessionContext ctx;

    @Inject
    private WorkflowInstanceRepo repo;

    @Inject
    private WorkflowConfigProvider conf;

    @Inject
    private WorkflowFeaturesProvider feat;

    @Inject
    private WorkflowEventsProvider evnt;

    @Inject
    private WorkflowEngineProvider work;

    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowInstance instance) {
        UUID id = repo.insert(instance);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_INSTANCE_CREATED,
                Json.createObjectBuilder()
                    .add("id", String.valueOf(instance.id))
                    .add("definition_id", String.valueOf(instance.definition_id))
                    .add("user_id", String.valueOf(instance.user_id))
                    .add("is_test", instance.is_test)
                    .add("state", instance.state)
                    .add("title", instance.title)
                    .add("note", instance.note)
                    .add("created_at", String.valueOf(instance.createdAt))
                    .add("updated_at", String.valueOf(instance.updatedAt))
                    .build());
        return id;
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_INSTANCE_DELETED,
                Json.createObjectBuilder()
                        .add("id", String.valueOf(id))
                        .build());
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowInstance update(@NotNull @Valid WorkflowInstance instance) {
        WorkflowInstance updated = repo.update(instance);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_INSTANCE_UPDATED,
                Json.createObjectBuilder()
                    .add("id", String.valueOf(updated.id))
                    .add("definition_id", String.valueOf(updated.definition_id))
                    .add("user_id", String.valueOf(updated.user_id))
                    .add("is_test", updated.is_test)
                    .add("state", updated.state)
                    .add("title", updated.title)
                    .add("note", updated.note)
                    .add("created_at", String.valueOf(updated.createdAt))
                    .add("updated_at", String.valueOf(updated.updatedAt))
                    .build());
    return updated;
    }

    /**
     * Returns a workflow instance given an id.
     *
     * @param id Requested instance id.
     * @return An optional workflow instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<WorkflowInstance> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    /**
     * Returns a list of all workflow instances.
     *
     * @param sort   Sort field.
     * @param offset Offset of first result.
     * @param limit  Limit of results returned.
     * @return A list of workflow instances.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<WorkflowInstance> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Starts a workflow instance for a specified instance and user.
     *
     * @return The id of the workflow instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID start(UUID definition_id, UUID user_id) {
        WorkflowInstance instance = new WorkflowInstance();
        instance.peer_id = null;
        instance.definition_id = definition_id;
        instance.user_id = user_id;
        instance.title = "TBD";
        instance.note = "TBD";
        UUID instance_id = repo.insert(instance);
        // UUID peer_id = work.start(instance_id, user_id);
        return instance_id;
    }

    /**
     * Returns a count of workflow instances.
     *
     * @return The count.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

    // @RolesAllowed({ "Admin" })
    public Integer test() {
        conf.test();
        feat.test();
        evnt.test();
        work.test();
        return 0;
    }
}
