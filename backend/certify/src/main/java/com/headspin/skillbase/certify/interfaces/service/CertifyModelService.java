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

import com.headspin.skillbase.certify.domain.CertifyModel;
import com.headspin.skillbase.certify.domain.CertifyModelRepo;
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyModelService {

    @Inject
    private CertifyModelRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public void insert(@NotNull @Valid CertifyModel model) {
        repo.insert(model);
        CertifyEvent.build("com.headspin.skillbase.certify.model.inserted");
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public void delete(@NotNull @Valid CertifyModel model) {
        repo.delete(model);
        CertifyEvent.build("com.headspin.skillbase.certify.model.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyModel update(@NotNull @Valid CertifyModel model) {
        CertifyModel updated = repo.update(model);
        CertifyEvent.build("com.headspin.skillbase.certify.model.updated");
        return updated;
    }

    @Transactional
    public void deleteById(@NotNull UUID id) {
        repo.deleteById(id);
        CertifyEvent.build("com.headspin.skillbase.certify.model.deleted");
    }

    @RolesAllowed({ "Admin", "User" })
    public Optional<CertifyModel> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyModel> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyModel> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin", "User" })
    public List<CertifyModel> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
