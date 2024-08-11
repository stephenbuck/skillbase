package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEventsProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeaturesProvider;

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
 * Workflow definitions service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowDefinitionsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowDefinitionRepo repo;

    @Inject
    private WorkflowConfigProvider conf;

    @Inject
    private WorkflowFeaturesProvider feat;

    @Inject
    private WorkflowEventsProvider evnt;

    @Inject
    private WorkflowEngineProvider work;

    /**
     * Inserts a new workflow definition.
     *
     * @param definition The new definition.
     * @return The id of the new definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final WorkflowDefinition definition) {
        final UUID definition_id = repo.insert(definition);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_CREATED,
            Json.createObjectBuilder()
                .add("definition_id", String.valueOf(definition.definition_id))
                .add("deployment_id", String.valueOf(definition.deployment_id))
                .add("credential_id", String.valueOf(definition.credential_id))
                .add("title", definition.title)
                .add("note", definition.note)
                .add("created_at", String.valueOf(definition.created_at))
                .add("updated_at", String.valueOf(definition.updated_at))
                .build());
        return definition_id;
    }

    /**
     * Deletes a workflow definition given an id.
     *
     * @param definition_id The requested definition id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID definition_id) {
        repo.delete(definition_id);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_DELETED,
            Json.createObjectBuilder()
                .add("definition_id", String.valueOf(definition_id))
                .build());
    }

    /**
     * Updates an existing workflow definition.
     *
     * @param instance The updated definition.
     * @return The updated definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid final WorkflowDefinition definition) {
        final WorkflowDefinition updated = repo.update(definition);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_UPDATED,
            Json.createObjectBuilder()
                .add("definition_id", String.valueOf(updated.definition_id))
                .add("deployment_id", String.valueOf(updated.deployment_id))
                .add("credential_id", String.valueOf(updated.credential_id))
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a workflow definition given an id.
     *
     * @param definition_id The requested definition id.
     * @return An optional workflow definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findById(@NotNull final UUID definition_id) {
        return repo.findById(definition_id);
    }

    /**
     * Returns a workflow definition given a credential id.
     *
     * @param credential_id The requested credential id.
     * @return An optional workflow definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findByCredentialId(@NotNull final UUID credential_id) {
        return repo.findByCredentialId(credential_id);
    }

    /**
     * Returns a list of all workflow definitiones.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of workflow definitiones.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<WorkflowDefinition> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a count of workflow definitiones.
     *
     * @return The count.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        work.test();
        return 0;
    }
}
