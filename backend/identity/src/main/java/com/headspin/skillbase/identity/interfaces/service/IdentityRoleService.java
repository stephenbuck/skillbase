package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityRoleRepo;

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
public class IdentityRoleService {

    @Inject
    private IdentityRoleRepo repo;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public void insert(@NotNull IdentityRole role) {
        repo.insert(role);
        IdentityEvent.build("com.headspin.skillbase.identity.role.inserted");
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityRole update(@NotNull IdentityRole role) {
        IdentityRole updated = repo.update(role);
        IdentityEvent.build("com.headspin.skillbase.identity.role.updated");
        return updated;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull IdentityRole role) {
        repo.delete(role);
        IdentityEvent.build("com.headspin.skillbase.identity.role.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void deleteById(@NotNull UUID id) {
        repo.deleteById(id);
        IdentityEvent.build("com.headspin.skillbase.identity.role.deleted");
    }

    @RolesAllowed({ "Admin" })
    public Optional<IdentityRole> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityRole> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityRole> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityRole> findAllByGroupId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
