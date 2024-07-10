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

import com.headspin.groupbase.workflow.domain.WorkflowTask;
import com.headspin.groupbase.workflow.domain.WorkflowTaskRepo;
import com.headspin.groupbase.workflow.providers.WorkflowProducerProvider;
import com.headspin.groupbase.workflow.domain.WorkflowEvent;

@Stateless
@PermitAll
@DeclareRoles({ "Admin", "User" })
public class WorkflowTaskService {

    @Inject
    private WorkflowTaskRepo repo;

    /*
    @Inject
    private WorkflowProducerProvider prod;

    @Resource
    private SessionContext ctx;
    */

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid WorkflowTask task) {
        UUID id = repo.insert(task);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_TASK_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_TASK_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public WorkflowTask update(@NotNull @Valid WorkflowTask task) {
        WorkflowTask updated = repo.update(task);
//        prod.produce(WorkflowEvent.buildEvent(task.id, WorkflowEvent.WORKFLOW_TASK_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin", "User" })
    public Optional<WorkflowTask> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowTask> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowTask> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllBySkillId(groupId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowTask> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
