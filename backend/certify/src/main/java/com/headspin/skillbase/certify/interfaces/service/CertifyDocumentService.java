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

import com.headspin.skillbase.certify.domain.CertifyDocument;
import com.headspin.skillbase.certify.domain.CertifyDocumentRepo;
import com.headspin.skillbase.certify.domain.CertifyEvent;
import com.headspin.skillbase.certify.providers.CertifyProducerProvider;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyDocumentService {

    @Inject
    private CertifyDocumentRepo repo;

    @Inject
    private CertifyProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyDocument document) {
        UUID id = repo.insert(document);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_DOCUMENT_INSERTED));
        return id;
    }

    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_DOCUMENT_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyDocument update(@NotNull @Valid CertifyDocument document) {
        CertifyDocument updated = repo.update(document);
        prod.produce(CertifyEvent.buildEvent(document.id, CertifyEvent.CERTIFY_EVENT_DOCUMENT_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin", "User" })
    public Optional<CertifyDocument> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyDocument> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyDocument> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyDocument> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
