package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;
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

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDefinition definition) {
        UUID id = repo.insert(definition);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_CREATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(definition.id))
                .add("deployment_id", String.valueOf(definition.deployment_id))
                .add("credential_id", String.valueOf(definition.credential_id))
                .add("title", definition.title)
                .add("note", definition.note)
                .add("created_at", String.valueOf(definition.createdAt))
                .build());
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_DELETED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build());
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition) {
        WorkflowDefinition updated = repo.update(definition);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEFINITION_UPDATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(updated.id))
                .add("deployment_id", String.valueOf(updated.deployment_id))
                .add("credential_id", String.valueOf(updated.credential_id))
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.createdAt))
                .add("updated_at", String.valueOf(updated.updatedAt))
                .build());
        return updated;
    }

    /**
     * Returns a workflow definition given an id.
     *
     * @param id Requested definition id.
     * @return An optional workflow definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    /**
     * Returns a workflow definition given an id.
     *
     * @param id Requested definition id.
     * @return An optional workflow definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findByCredentialId(@NotNull UUID credential_id) {
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
    public List<WorkflowDefinition> findAll(String sort, Integer offset, Integer limit) {
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
