package com.headspin.skillbase.member.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberEvent;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
import com.headspin.skillbase.member.infrastructure.kafka.MemberProducerProviderKafka;
import com.headspin.skillbase.member.infrastructure.keycloak.MemberAuthProviderKeycloak;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
//import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class MemberGroupService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private MemberGroupRepo repo;

//    @Inject
    private MemberProducerProvider prod = new MemberProducerProviderKafka();

//    @Inject
    private MemberAuthProvider auth = new MemberAuthProviderKeycloak();

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid MemberGroup member) {
        UUID id = repo.insert(member);
        prod.produce(MemberEvent.buildEvent(member.id, MemberEvent.MEMBER_GROUP_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_GROUP_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public MemberGroup update(@NotNull @Valid MemberGroup member) {
        MemberGroup updated = repo.update(member);
        prod.produce(MemberEvent.buildEvent(member.id, MemberEvent.MEMBER_GROUP_UPDATED));
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
    public Long count() {
        auth.test();
        prod.produce(MemberEvent.buildEvent(UUID.randomUUID(), MemberEvent.MEMBER_GROUP_UPDATED));
        return repo.count();
    }
}
