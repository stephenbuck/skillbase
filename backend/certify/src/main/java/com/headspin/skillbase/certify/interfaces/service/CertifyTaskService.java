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
import com.headspin.skillbase.certify.domain.CertifyEvent;

@Stateless
@DeclareRoles({"Admin", "User"})
public class CertifyTaskService {

    @Inject
    private CertifyTaskRepo repo;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({"Admin", "User"})
    public void insert(
            @NotNull @Valid CertifyTask task) {
        repo.insert(task);
        CertifyEvent.build("com.headspin.skillbase.certify.task.inserted");
    }

    @Transactional
    @RolesAllowed({"Admin", "User"})
    public void delete(
            @NotNull @Valid CertifyTask task) {
        repo.delete(task);
        CertifyEvent.build("com.headspin.skillbase.certify.task.deleted");
    }

    @Transactional
    @RolesAllowed({"Admin", "User"})
    public CertifyTask update(
            @NotNull @Valid CertifyTask task) {
        CertifyTask updated = repo.update(task);
        CertifyEvent.build("com.headspin.skillbase.certify.task.updated");
        return updated;
    }

    @Transactional
    public void deleteById(
            @NotNull UUID id) {
        repo.deleteById(id);
        CertifyEvent.build("com.headspin.skillbase.certify.task.deleted");
    }

    @RolesAllowed({"Admin", "User"})
    public Optional<CertifyTask> findById(
            @NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({"Admin", "User"})
    public List<CertifyTask> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({"Admin", "User"})
    public List<CertifyTask> findAllBySkillId(
            @NotNull UUID skillId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllBySkillId(skillId, sort, offset, limit);
    }

    @RolesAllowed({"Admin", "User"})
    public List<CertifyTask> findAllByUserId(
            @NotNull UUID userId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
