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

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyDocumentService {

    @Inject
    private CertifyDocumentRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyDocument document) {
        UUID id = repo.insert(document);
        CertifyEvent.build("com.headspin.skillbase.certify.document.inserted");
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull @Valid CertifyDocument document) {
        repo.delete(document);
        CertifyEvent.build("com.headspin.skillbase.certify.document.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyDocument update(@NotNull @Valid CertifyDocument document) {
        CertifyDocument updated = repo.update(document);
        CertifyEvent.build("com.headspin.skillbase.certify.document.updated");
        return updated;
    }

    @Transactional
    public void deleteById(@NotNull UUID id) {
        repo.deleteById(id);
        CertifyEvent.build("com.headspin.skillbase.certify.document.deleted");
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
