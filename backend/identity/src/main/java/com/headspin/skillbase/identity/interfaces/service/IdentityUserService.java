package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.domain.IdentityUserRepo;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Stateless
@DeclareRoles({"Admin", "User"})
public class IdentityUserService {

    @Inject
    private IdentityUserRepo repo;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({"Admin"})
    public void insert(
        @NotNull IdentityUser user) {
        repo.insert(user);
        IdentityEvent.build("com.headspin.skillbase.identity.user.inserted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    public IdentityUser update(
        @NotNull IdentityUser user) {
        IdentityUser updated = repo.update(user);
        IdentityEvent.build("com.headspin.skillbase.identity.user.updated");
        return updated;
    }

    @Transactional
    @RolesAllowed({"Admin"})
    public void delete(
        @NotNull IdentityUser user) {
        repo.delete(user);
        IdentityEvent.build("com.headspin.skillbase.identity.user.deleted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    public void deleteById(
        @NotNull UUID id) {
        repo.deleteById(id);
        IdentityEvent.build("com.headspin.skillbase.identity.user.deleted");
    }

    @RolesAllowed({"Admin"})
    public Optional<IdentityUser> findById(
        @NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({"Admin"})
    public List<IdentityUser> findAll(
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit
    ) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<IdentityUser> findAllByGroupId(
        @NotNull UUID id,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit) {
        return repo.findAllByGroupId(id, sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<IdentityUser> findAllByRoleId(
        @NotNull UUID id,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit) {
        return repo.findAllByRoleId(id, sort, offset, limit);
    }
}
