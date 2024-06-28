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

import com.headspin.skillbase.certify.domain.CertifyCert;
import com.headspin.skillbase.certify.domain.CertifyCertRepo;
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyCertService {

    @Inject
    private CertifyCertRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyCert cert) {
        repo.insert(cert);
        CertifyEvent.build("com.headspin.skillbase.certify.cert.inserted");
        return cert.id();
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull @Valid CertifyCert cert) {
        repo.delete(cert);
        CertifyEvent.build("com.headspin.skillbase.certify.cert.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyCert update(@NotNull @Valid CertifyCert cert) {
        CertifyCert updated = repo.update(cert);
        CertifyEvent.build("com.headspin.skillbase.certify.cert.updated");
        return updated;
    }

    @Transactional
    public void deleteById(@NotNull UUID id) {
        repo.deleteById(id);
        CertifyEvent.build("com.headspin.skillbase.certify.cert.deleted");
    }

    @RolesAllowed({ "Admin", "User" })
    public Optional<CertifyCert> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyCert> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyCert> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyCert> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
