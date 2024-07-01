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
import com.headspin.skillbase.certify.providers.CertifyProducerProvider;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyCertService {

    @Inject
    private CertifyCertRepo repo;

    @Inject
    private CertifyProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyCert cert) {
        UUID id = repo.insert(cert);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_CERT_INSERTED));
        return id;
    }

    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_CERT_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyCert update(@NotNull @Valid CertifyCert cert) {
        CertifyCert updated = repo.update(cert);
        prod.produce(CertifyEvent.buildEvent(cert.id(), CertifyEvent.CERTIFY_EVENT_CERT_UPDATED));
        return updated;
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
