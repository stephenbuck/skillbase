package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.domain.MemberRole;
import com.headspin.skillbase.member.domain.MemberRoleRepo;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

/*
 * MemberRoleService is a stateless service provider for
 * the Role domain. It integrates the MemberRoleRepo,
 * MemberAuthProvider, and MemberEventProducer services
 * and acts as the main transaction and authorization point.
 *
 * One of the main responsibilities of this service is to
 * maintain the relationship between the User, Group, and Role
 * entities with their counterparts managed by the external
 * MemberAuthProvider (e.g. Keycloak).
 */

@PermitAll
//@DeclareRoles({ "Admin", "User" })
@ApplicationScoped
public class MemberRoleService {

    @Inject
    private MemberRoleRepo repo;

    /*
    @Inject
    private MemberAuthProvider auth;
    */

    /*
    @Inject
    private MemberProducerProvider prod;
    */

    /*
    @Resource
    SessionContext ctx;
    */

    public MemberRoleService() {
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull MemberRole role) {
        UUID id = repo.insert(role);
//        auth.insertRole(id, role);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_ROLE_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        auth.deleteRole(id);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_ROLE_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public MemberRole update(@NotNull MemberRole role) {
        MemberRole updated = repo.update(role);
//        auth.updateRole(role);
//        prod.produce(MemberEvent.buildEvent(role.id, MemberEvent.MEMBER_ROLE_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<MemberRole> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberRole> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberRole> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberRole> findAllByGroupId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }
}
