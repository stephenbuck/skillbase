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
import com.headspin.skillbase.certify.providers.CertifyProducerProvider;
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({ "Admin", "User" })
public class CertifyModelService {

    @Inject
    private CertifyModelRepo repo;

    @Inject
    private CertifyProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public UUID insert(@NotNull @Valid CertifyModel model) {
        UUID id = repo.insert(model);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_MODEL_INSERTED));
        return id;
    }

    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CertifyEvent.buildEvent(id, CertifyEvent.CERTIFY_EVENT_MODEL_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin", "User" })
    public CertifyModel update(@NotNull @Valid CertifyModel model) {
        CertifyModel updated = repo.update(model);
        prod.produce(CertifyEvent.buildEvent(model.id, CertifyEvent.CERTIFY_EVENT_MODEL_UPDATED));
        return updated;
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
