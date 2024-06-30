package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityProvider;
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
@DeclareRoles({ "Admin", "User" })
public class IdentityUserService {

    @Inject
    private IdentityUserRepo repo;

    @Inject
    private IdentityProvider prov;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull IdentityUser user) {
        UUID id = repo.insert(user);
        prov.insertUser(id, user);
        IdentityEvent.build(id, "com.headspin.skillbase.identity.user.inserted");
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prov.deleteUser(id);
        IdentityEvent.build(id, "com.headspin.skillbase.identity.user.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityUser update(@NotNull IdentityUser user) {
        IdentityUser updated = repo.update(user);
        prov.updateUser(updated);
        IdentityEvent.build(user.id(), "com.headspin.skillbase.identity.user.updated");
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Optional<IdentityUser> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityUser> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityUser> findAllByGroupId(@NotNull UUID id, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByGroupId(id, sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityUser> findAllByRoleId(@NotNull UUID id, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByRoleId(id, sort, offset, limit);
    }
}
