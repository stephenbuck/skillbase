package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;
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

    private WorkflowConfigProvider conf = new WorkflowConfigProviderEtcd();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowEventProducer();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDeployment deployment) {
        UUID id = repo.insert(deployment);
        prod.produce(WorkflowEvent.buildEvent(deployment.id, WorkflowEvent.WORKFLOW_DEPLOYMENT_CREATED));
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED));
        return result;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment) {
        WorkflowDeployment updated = repo.update(deployment);
        prod.produce(WorkflowEvent.buildEvent(deployment.id, WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED));
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
        prod.produce(WorkflowEvent.buildEvent(UUID.randomUUID(), WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED));
        return 0;
    }
}
