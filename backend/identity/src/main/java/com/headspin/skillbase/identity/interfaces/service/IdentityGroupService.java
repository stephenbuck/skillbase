package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityGroupRepo;

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
public class IdentityGroupService {

    @Inject
    private IdentityGroupRepo repo;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull IdentityGroup group) {
        UUID id = repo.insert(group);
        IdentityEvent.build(id, "com.headspin.skillbase.identity.group.inserted");
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        IdentityEvent.build(id, "com.headspin.skillbase.identity.group.deleted");
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityGroup update(@NotNull IdentityGroup group) {
        IdentityGroup updated = repo.update(group);
        IdentityEvent.build(group.id(), "com.headspin.skillbase.identity.group.updated");
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Optional<IdentityGroup> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityGroup> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityGroup> findAllByRoleId(@NotNull UUID roleId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByRoleId(roleId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<IdentityGroup> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }
}
