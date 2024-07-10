package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;
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
 * MemberUserService is a stateless service provider for
 * the User domain. It integrates the MemberUserRepo,
 * MemberAuthProvider, and MemberEventProducer services
 * and acts as the main transaction and authorization point.
 */

@PermitAll
//@DeclareRoles({ "Admin", "User" })
@ApplicationScoped
public class MemberUserService {

    @Inject
    private MemberUserRepo repo;

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
    
    public MemberUserService() {
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull MemberUser user) {
        UUID id = repo.insert(user);
//        auth.insertUser(id, user);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_USER_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        auth.deleteUser(id);
//        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_USER_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public MemberUser update(@NotNull MemberUser user) {
        MemberUser updated = repo.update(user);
//        auth.updateUser(updated);
//        prod.produce(MemberEvent.buildEvent(user.id, MemberEvent.MEMBER_USER_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<MemberUser> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findAllByGroupId(@NotNull UUID id, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByGroupId(id, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findAllByRoleId(@NotNull UUID id, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByRoleId(id, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }
}
