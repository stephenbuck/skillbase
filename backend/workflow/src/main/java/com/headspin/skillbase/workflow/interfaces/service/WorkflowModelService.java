package com.headspin.skillbase.workflow.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.workflow.domain.WorkflowProcess;
import com.headspin.skillbase.workflow.domain.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowModel;
import com.headspin.skillbase.workflow.domain.WorkflowModelRepo;
import com.headspin.skillbase.workflow.infrastructure.config.WorkflowConfigProviderEtcd;
import com.headspin.skillbase.workflow.infrastructure.engine.WorkflowEngineProviderFlowable;
import com.headspin.skillbase.workflow.infrastructure.feature.WorkflowFeatureProviderFlipt;
import com.headspin.skillbase.workflow.infrastructure.messaging.WorkflowProducerProviderKafka;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowModelService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowModelRepo repo;

    private WorkflowConfigProvider conf = new WorkflowConfigProviderEtcd();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowProducerProviderKafka();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowModel model) {
        UUID id = repo.insert(model);
        prod.produce(WorkflowEvent.buildEvent(model.id, WorkflowEvent.WORKFLOW_MODEL_CREATED));
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_MODEL_DELETED));
        return result;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowModel update(@NotNull @Valid WorkflowModel model) {
        WorkflowModel updated = repo.update(model);
        prod.produce(WorkflowEvent.buildEvent(model.id, WorkflowEvent.WORKFLOW_MODEL_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowModel> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<WorkflowModel> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

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
        prod.produce(WorkflowEvent.buildEvent(UUID.randomUUID(), WorkflowEvent.WORKFLOW_MODEL_UPDATED));
        return 0;
    }
}
