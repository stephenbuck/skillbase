package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;
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
 * Service interface for workflow deployments.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowDeploymentsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowDeploymentRepo repo;

    private WorkflowConfigProvider conf = new WorkflowConfigProviderDefault();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowEventProducerKafka();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();

    private void produceDeploymentCreatedEvent(WorkflowDeployment deployment) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEPLOYMENT_CREATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(deployment.id))
                .add("title", deployment.title)
                .build()));
    }

    private void produceDeploymentDeletedEvent(UUID id) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build()));
    }

    private void produceDeploymentUpdatedEvent(WorkflowDeployment deployment) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(deployment.id))
                .add("title", deployment.title)
                .build()));
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDeployment deployment) {
        UUID id = repo.insert(deployment);
        produceDeploymentCreatedEvent(deployment);
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        produceDeploymentDeletedEvent(id);
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment) {
        WorkflowDeployment updated = repo.update(deployment);
        produceDeploymentUpdatedEvent(updated);
        return updated;
    }

    /**
     * Returns a workflow deployment given an id.
     *
     * @param id Requested deployment id.
     * @return An optional workflow process.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    /**
     * Returns a workflow deployment given an id.
     *
     * @param id Requested deployment id.
     * @return An optional workflow process.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findBySkillId(@NotNull UUID skill_id) {
        return repo.findBySkillId(skill_id);
    }

    /**
     * Returns a list of all workflow deployments.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of workflow processes.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a count of workflow deployments.
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
        produceDeploymentDeletedEvent(UUID.randomUUID());
        return 0;
    }
}
