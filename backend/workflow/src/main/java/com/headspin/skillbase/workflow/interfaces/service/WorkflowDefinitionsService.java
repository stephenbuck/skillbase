package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;
import com.headspin.skillbase.workflow.infrastructure.config.WorkflowConfigProviderEtcd;
import com.headspin.skillbase.workflow.infrastructure.engine.WorkflowEngineProviderFlowable;
import com.headspin.skillbase.workflow.infrastructure.feature.WorkflowFeatureProviderFlipt;
import com.headspin.skillbase.workflow.infrastructure.messaging.WorkflowEventProducer;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
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


    private WorkflowConfigProvider conf = new WorkflowConfigProviderEtcd();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowEventProducer();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();


//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDefinition definition) {
        UUID id = repo.insert(definition);
        prod.produce(WorkflowEvent.buildEvent(definition.id, WorkflowEvent.WORKFLOW_DEFINITION_UPDATED));
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_DEFINITION_DELETED));
        return result;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid WorkflowDefinition definition) {
        WorkflowDefinition updated = repo.update(definition);
        prod.produce(WorkflowEvent.buildEvent(definition.id, WorkflowEvent.WORKFLOW_DEFINITION_UPDATED));
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
        prod.produce(WorkflowEvent.buildEvent(UUID.randomUUID(), WorkflowEvent.WORKFLOW_DEFINITION_UPDATED));
        return 0;
    }
}
