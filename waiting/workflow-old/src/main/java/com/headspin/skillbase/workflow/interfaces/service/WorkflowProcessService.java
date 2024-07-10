package com.headspin.groupbase.workflow.interfaces.service;

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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.groupbase.workflow.domain.WorkflowProcess;
import com.headspin.groupbase.workflow.domain.WorkflowProcessRepo;
import com.headspin.groupbase.workflow.providers.WorkflowProducerProvider;
import com.headspin.groupbase.workflow.domain.WorkflowEvent;

@Stateless
@PermitAll
@DeclareRoles({ "Admin", "User" })
public class WorkflowProcessService {

    @Inject
    private WorkflowProcessRepo repo;

    /*
    @Inject
    private WorkflowProducerProvider prod;

    @Resource
    private SessionContext ctx;
    */

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid WorkflowProcess process) {
        UUID id = repo.insert(process);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_PROCESS_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_PROCESS_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public WorkflowProcess update(@NotNull @Valid WorkflowProcess process) {
        WorkflowProcess updated = repo.update(process);
//        prod.produce(WorkflowEvent.buildEvent(updated.id, WorkflowEvent.WORKFLOW_PROCESS_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin", "User" })
    public Optional<WorkflowProcess> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowProcess> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowProcess> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllBySkillId(groupId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowProcess> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
