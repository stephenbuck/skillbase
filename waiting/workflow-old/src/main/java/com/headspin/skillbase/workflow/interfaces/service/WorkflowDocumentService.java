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

import com.headspin.groupbase.workflow.domain.WorkflowDocument;
import com.headspin.groupbase.workflow.domain.WorkflowDocumentRepo;
import com.headspin.groupbase.workflow.domain.WorkflowEvent;
import com.headspin.groupbase.workflow.providers.WorkflowProducerProvider;

@Stateless
@PermitAll
@DeclareRoles({ "Admin", "User" })
public class WorkflowDocumentService {

    @Inject
    private WorkflowDocumentRepo repo;

    /*
    @Inject
    private WorkflowProducerProvider prod;

    @Resource
    private SessionContext ctx;
    */

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid WorkflowDocument document) {
        UUID id = repo.insert(document);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_DOCUMENT_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_DOCUMENT_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public WorkflowDocument update(@NotNull @Valid WorkflowDocument document) {
        WorkflowDocument updated = repo.update(document);
//        prod.produce(WorkflowEvent.buildEvent(document.id, WorkflowEvent.WORKFLOW_DOCUMENT_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin", "User" })
    public Optional<WorkflowDocument> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowDocument> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowDocument> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllBySkillId(groupId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowDocument> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
