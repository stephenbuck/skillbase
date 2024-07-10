package com.headspin.skillbase.workflow.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.workflow.domain.WorkflowProcess;
import com.headspin.skillbase.workflow.domain.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowModel;
import com.headspin.skillbase.workflow.domain.WorkflowModelRepo;
import com.headspin.skillbase.workflow.infrastructure.flowable.WorkflowEngineProviderFlowable;
import com.headspin.skillbase.workflow.infrastructure.kafka.WorkflowProducerProviderKafka;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
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
//@DeclareRoles({ "Admin", "User" })
public class WorkflowModelService {

    /*
    @Resource
    private SessionContext ctx;
    */
    
    @Inject
    private WorkflowModelRepo repo;

//    private WorkflowEngineProviderFlowable work = new WorkflowEngineProviderFlowable();

//    @Inject
//    private WorkflowProducerProviderKafka prod = new WorkflowProducerProviderKafka();

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid WorkflowModel model) {
        UUID id = repo.insert(model);
//        prod.produce(WorkflowEvent.buildEvent(model.id(), WorkflowEvent.MEMBER_GROUP_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.MEMBER_GROUP_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public WorkflowModel update(@NotNull @Valid WorkflowModel model) {
        WorkflowModel updated = repo.update(model);
//        prod.produce(WorkflowEvent.buildEvent(model.id(), WorkflowEvent.MEMBER_GROUP_UPDATED));
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
}
