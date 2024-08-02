package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;
import com.headspin.skillbase.workflow.infrastructure.config.WorkflowConfigProviderDefault;
import com.headspin.skillbase.workflow.infrastructure.engine.WorkflowEngineProviderFlowable;
import com.headspin.skillbase.workflow.infrastructure.feature.WorkflowFeatureProviderFlipt;
import com.headspin.skillbase.workflow.infrastructure.messaging.WorkflowEventProducerKafka;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service interface for workflow definitions.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowDefinitionsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowDefinitionRepo repo;

    private WorkflowConfigProvider conf = new WorkflowConfigProviderDefault();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowEventProducerKafka();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();

    private void produceDefinitionCreatedEvent(WorkflowDefinition definition) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEFINITION_CREATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(definition.id))
                .add("title", definition.title)
                .build()));
    }

    private void produceDefinitionDeletedEvent(UUID id) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEFINITION_DELETED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build()));
    }

    private void produceDefinitionUpdatedEvent(WorkflowDefinition definition) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEFINITION_UPDATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(definition.id))
                .add("title", definition.title)
                .build()));
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDefinition definition) {
        UUID id = repo.insert(definition);
        produceDefinitionCreatedEvent(definition);
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        produceDefinitionDeletedEvent(id);
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition) {
        WorkflowDefinition updated = repo.update(definition);
        produceDefinitionUpdatedEvent(updated);
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
        conf.test();
        feat.test();
        prod.test();
        work.test();
        produceDefinitionDeletedEvent(UUID.randomUUID());
        return 0;
    }
}
