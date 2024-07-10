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

import com.headspin.groupbase.workflow.domain.WorkflowCert;
import com.headspin.groupbase.workflow.domain.WorkflowCertRepo;
import com.headspin.groupbase.workflow.domain.WorkflowEvent;
import com.headspin.groupbase.workflow.providers.WorkflowProducerProvider;

@Stateless
@PermitAll
@DeclareRoles({ "Admin", "User" })
public class WorkflowCertService {

    @Inject
    private WorkflowCertRepo repo;

/*
    @Inject
    private WorkflowProducerProvider prod;

    @Resource
    private SessionContext ctx;
*/

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid WorkflowCert cert) {
        UUID id = repo.insert(cert);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_CERT_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(WorkflowEvent.buildEvent(id, WorkflowEvent.WORKFLOW_CERT_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin", "User" })
    public WorkflowCert update(@NotNull @Valid WorkflowCert cert) {
        WorkflowCert updated = repo.update(cert);
//        prod.produce(WorkflowEvent.buildEvent(cert.id, WorkflowEvent.WORKFLOW_CERT_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin", "User" })
    public Optional<WorkflowCert> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowCert> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowCert> findAllBySkillId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllBySkillId(groupId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin", "User" })
    public List<WorkflowCert> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
