package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityRoleRepo;
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
 * IdentityRoleService is a stateless service provider for
 * the Role domain. It integrates the IdentityRoleRepo,
 * IdentityAuthProvider, and IdentyEventProducer services
 * and acts as the main transaction and authorization point.
 *
 * One of the main responsibilities of this service is to
 * maintain the relationship between the User, Group, and Role
 * entities with their counterparts managed by the external
 * IdentityAuthProvider (e.g. Keycloak).
 */

@Slf4j
@Stateless
@ApplicationScoped
@DeclareRoles({ "Admin", "User" })
public class IdentityRoleService {

    @Inject
    private IdentityRoleRepo repo;

    @Inject
    private IdentityAuthProvider auth;

    @Inject
    private IdentityProducerProvider prod;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull IdentityRole role) {
        UUID id = repo.insert(role);
        auth.insertRole(id, role);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_ROLE_INSERTED));
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        auth.deleteRole(id);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_ROLE_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityRole update(@NotNull IdentityRole role) {
        IdentityRole updated = repo.update(role);
        auth.updateRole(role);
        prod.produce(IdentityEvent.buildEvent(role.id(), IdentityEvent.IDENTITY_EVENT_ROLE_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
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

    public void ping(String msg) {
        log.info("ping = {}", msg);
    }
}
