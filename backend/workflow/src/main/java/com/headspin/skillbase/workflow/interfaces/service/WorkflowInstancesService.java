package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowInstanceRepo;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;

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
    private WorkflowEngineProvider work;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    /**
     * Inserts a new workflow instance.
     *
     * @param instance The new instance.
     * @return The id of the new instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final WorkflowInstance instance) {
        final UUID instance_id = repo.insert(instance);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_INSTANCE_CREATED,
            Json.createObjectBuilder()
                .add("instance_id", String.valueOf(instance.instance_id))
                .add("definition_id", String.valueOf(instance.definition_id))
                .add("user_id", String.valueOf(instance.user_id))
                .add("is_test", instance.is_test)
                .add("state", instance.state)
                .add("title", instance.title)
                .add("note", instance.note)
                .add("created_at", String.valueOf(instance.created_at))
                .add("updated_at", String.valueOf(instance.updated_at))
                .build());
        return instance_id;
    }

    /**
     * Deletes a workflow instance given an id.
     *
     * @param instance_id The requested instance id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID instance_id) {
        repo.delete(instance_id);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_INSTANCE_DELETED,
            Json.createObjectBuilder()
                .add("instance_id", String.valueOf(instance_id))
                .build());
    }

    /**
     * Updates an existing workflow instance.
     *
     * @param instance The updated instance.
     * @return The updated instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowInstance update(@NotNull @Valid final WorkflowInstance instance) {
        final WorkflowInstance updated = repo.update(instance);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_INSTANCE_UPDATED,
            Json.createObjectBuilder()
                .add("instance_id", String.valueOf(updated.instance_id))
                .add("definition_id", String.valueOf(updated.definition_id))
                .add("user_id", String.valueOf(updated.user_id))
                .add("is_test", updated.is_test)
                .add("state", updated.state)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a workflow instance given an id.
     *
     * @param instance_id The requested instance id.
     * @return An optional workflow instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<WorkflowInstance> findById(@NotNull final UUID instance_id) {
        return repo.findById(instance_id);
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
    public List<WorkflowInstance> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Starts a workflow instance for a specified instance and user.
     *
     * @param definition_id The requested definition id.
     * @param user_id The requested user id.
     * @return The id of the workflow instance.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID start(final UUID definition_id, final UUID user_id) {
        final WorkflowInstance instance = new WorkflowInstance();
        instance.peer_id = null;
        instance.definition_id = definition_id;
        instance.user_id = user_id;
        instance.title = "TBD";
        instance.note = "TBD";
        final UUID instance_id = repo.insert(instance);
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
