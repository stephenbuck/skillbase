package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.workflow.domain.WorkflowProcess;
import com.headspin.skillbase.workflow.domain.WorkflowProcessRepo;
import com.headspin.skillbase.workflow.domain.WorkflowEvent;
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

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowProcessService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowProcessRepo repo;


    private WorkflowConfigProvider conf = new WorkflowConfigProviderEtcd();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowProducerProviderKafka();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();


    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid WorkflowProcess process) {
        UUID id = repo.insert(process);
        prod.produce(WorkflowEvent.buildEvent(process.id, WorkflowEvent.WORKFLOW_PROCESS_UPDATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_PROCESS_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public WorkflowProcess update(@NotNull @Valid WorkflowProcess process) {
        WorkflowProcess updated = repo.update(process);
        prod.produce(WorkflowEvent.buildEvent(process.id, WorkflowEvent.WORKFLOW_PROCESS_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowProcess> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<WorkflowProcess> findAll(String sort, Integer offset, Integer limit) {
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
        prod.produce(WorkflowEvent.buildEvent(UUID.randomUUID(), WorkflowEvent.WORKFLOW_PROCESS_UPDATED));
        return 0;
    }
}
