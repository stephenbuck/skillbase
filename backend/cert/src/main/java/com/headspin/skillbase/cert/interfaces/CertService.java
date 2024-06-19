package com.headspin.skillbase.cert.interfaces;

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

import com.headspin.skillbase.cert.domain.Cert;
import com.headspin.skillbase.cert.domain.CertEvent;
import com.headspin.skillbase.cert.domain.CertRepo;

@Stateless
@DeclareRoles({"Admin", "User"})
public class CertService {

    @Inject
    private CertRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({"Admin", "User"})
    void insert(
            @NotNull @Valid Cert cert) {
        repo.insert(cert);
        CertEvent.build("com.headspin.skillbase.cert.inserted");
    }

    @Transactional
    @RolesAllowed({"Admin", "User"})
    void delete(
            @NotNull @Valid Cert cert) {
        repo.delete(cert);
        CertEvent.build("com.headspin.skillbase.cert.deleted");
    }

    @Transactional
    @RolesAllowed({"Admin", "User"})
    Cert update(
            @NotNull @Valid Cert cert) {
        Cert updated = repo.update(cert);
        CertEvent.build("com.headspin.skillbase.cert.updated");
        return updated;
    }

    @Transactional
    void deleteById(
            @NotNull UUID id) {
        repo.deleteById(id);
        CertEvent.build("com.headspin.skillbase.cert.deleted");
    }

    @RolesAllowed({"Admin", "User"})
    public Optional<Cert> findById(
            @NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({"Admin", "User"})
    public List<Cert> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({"Admin", "User"})
    public List<Cert> findAllBySkillId(
            @NotNull UUID skillId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({"Admin", "User"})
    public List<Cert> findAllByUserId(
            @NotNull UUID userId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
