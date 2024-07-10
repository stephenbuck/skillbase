package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
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
 * MemberGroupService is a stateless service provider for
 * the Group domain. It integrates the MemberGroupRepo,
 * MemberAuthProvider, and MemberEventProducer services
 * and acts as the main transaction and authorization point.
 */

@PermitAll
// @DeclareRoles({ "Admin", "User" })
@ApplicationScoped
public class MemberGroupService {

    @Inject
    private MemberGroupRepo repo;

    public MemberGroupService() {
    }

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

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull MemberGroup group) {
        UUID id = repo.insert(group);
//        auth.insertGroup(id, group);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_ROLE_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        auth.deleteGroup(id);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_ROLE_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public MemberGroup update(@NotNull MemberGroup group) {
        MemberGroup updated = repo.update(group);
//        auth.updateGroup(updated);
//        prod.produce(MemberEvent.buildEvent(group.id, MemberEvent.MEMBER_ROLE_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<MemberGroup> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findAllByRoleId(@NotNull UUID roleId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByRoleId(roleId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByUserId(userId, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }
}
