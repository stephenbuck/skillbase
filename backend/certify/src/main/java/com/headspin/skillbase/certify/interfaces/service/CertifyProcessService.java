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

import com.headspin.skillbase.certify.domain.CertifyProcess;
import com.headspin.skillbase.certify.domain.CertifyProcessRepo;
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyProcessService {

    @Inject
    private CertifyProcessRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyProcess process) {
        UUID id = repo.insert(process);
        CertifyEvent.build(id, "com.headspin.skillbase.certify.process.inserted");
        return id;
    }

    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        CertifyEvent.build(id, "com.headspin.skillbase.certify.process.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyProcess update(@NotNull @Valid CertifyProcess process) {
        CertifyProcess updated = repo.update(process);
        CertifyEvent.build(process.id(), "com.headspin.skillbase.certify.process.updated");
        return updated;
    }

    @RolesAllowed({ "Admin", "User" })
    public Optional<CertifyProcess> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyProcess> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyProcess> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyProcess> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
