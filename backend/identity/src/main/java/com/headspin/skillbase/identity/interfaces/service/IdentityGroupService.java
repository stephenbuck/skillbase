package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityGroupRepo;
import com.headspin.skillbase.identity.providers.IdentityAuthProvider;
import com.headspin.skillbase.identity.providers.IdentityProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;

/*
 * IdentityGroupService is a stateless service provider for
 * the Group domain. It integrates the IdentityGroupRepo,
 * IdentityAuthProvider, and IdentyEventProducer services
 * and acts as the main transaction and authorization point.
 */

@Slf4j
@Stateless
@ApplicationScoped
@DeclareRoles({ "Admin", "User" })
public class IdentityGroupService {

    @Inject
    private IdentityGroupRepo repo;

    @Inject
    private IdentityAuthProvider auth;

    @Inject
    private IdentityProducerProvider prod;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull IdentityGroup group) {
        UUID id = repo.insert(group);
        auth.insertGroup(id, group);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_ROLE_INSERTED));
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        auth.deleteGroup(id);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_ROLE_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityGroup update(@NotNull IdentityGroup group) {
        IdentityGroup updated = repo.update(group);
        auth.updateGroup(updated);
        prod.produce(IdentityEvent.buildEvent(group.id(), IdentityEvent.IDENTITY_EVENT_ROLE_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
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

    public void ping(String msg) {
        log.info("ping = {}", msg);
    }
}
