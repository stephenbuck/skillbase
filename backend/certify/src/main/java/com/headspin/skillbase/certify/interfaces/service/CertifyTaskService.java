package com.headspin.skillbase.certify.interfaces.service;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.certify.domain.CertifyTask;
import com.headspin.skillbase.certify.domain.CertifyTaskRepo;
import com.headspin.skillbase.certify.providers.CertifyProducerProvider;
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyTaskService {

    @Inject
    private CertifyTaskRepo repo;

    @Inject
    private CertifyProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyTask task) {
        UUID id = repo.insert(task);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_TASK_INSERTED));
        return id;
    }

    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_TASK_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyTask update(@NotNull @Valid CertifyTask task) {
        CertifyTask updated = repo.update(task);
        prod.produce(CertifyEvent.buildEvent(task.id(), CertifyEvent.CERTIFY_EVENT_TASK_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin", "User" })
    public Optional<CertifyTask> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyTask> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyTask> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyTask> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
