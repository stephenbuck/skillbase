package com.headspin.skillbase.identity.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityEvent;
import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.domain.IdentityUserRepo;
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
 * IdentityUserService is a stateless service provider for
 * the User domain. It integrates the IdentityUserRepo,
 * IdentityAuthProvider, and IdentyEventProducer services
 * and acts as the main transaction and authorization point.
 */

@Slf4j
@Stateless
@ApplicationScoped
@DeclareRoles({ "Admin", "User" })
public class IdentityUserService {

    @Inject
    private IdentityUserRepo repo;

    @Inject
    private IdentityAuthProvider auth;

    @Inject
    private IdentityProducerProvider prod;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull IdentityUser user) {
        UUID id = repo.insert(user);
        auth.insertUser(id, user);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_USER_INSERTED));
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        auth.deleteUser(id);
        prod.produce(IdentityEvent.buildEvent(id, IdentityEvent.IDENTITY_EVENT_USER_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public IdentityUser update(@NotNull IdentityUser user) {
        IdentityUser updated = repo.update(user);
        auth.updateUser(updated);
        prod.produce(IdentityEvent.buildEvent(user.id, IdentityEvent.IDENTITY_EVENT_USER_UPDATED));
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

    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }
}
