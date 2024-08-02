package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.infrastructure.auth.MemberAuthProviderKeycloak;
import com.headspin.skillbase.member.infrastructure.config.MemberConfigProviderDefault;
import com.headspin.skillbase.member.infrastructure.feature.MemberFeatureProviderFlipt;
import com.headspin.skillbase.member.infrastructure.messaging.MemberEventProducerKafka;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.member.providers.MemberConfigProvider;
import com.headspin.skillbase.member.providers.MemberFeatureProvider;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class MemberUserService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private MemberUserRepo repo;

    private MemberConfigProvider conf = new MemberConfigProviderDefault();
    private MemberFeatureProvider feat = new MemberFeatureProviderFlipt();
    private MemberProducerProvider prod = new MemberEventProducerKafka();
    private MemberAuthProvider auth = new MemberAuthProviderKeycloak();

    private void produceUserCreatedEvent(MemberUser user) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_USER_CREATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(user.id))
                .build()));
    }

    private void produceUserDeletedEvent(UUID id) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_USER_DELETED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build()));
    }

    private void produceUserUpdatedEvent(MemberUser user) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_USER_UPDATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(user.id))
                .build()));
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberUser user) {
        UUID id = repo.insert(user);
        produceUserCreatedEvent(user);
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        produceUserDeletedEvent(id);
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberUser update(@NotNull @Valid MemberUser user) {
        MemberUser updated = repo.update(user);
        produceUserUpdatedEvent(updated);
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
    public List<MemberAchievement> findUserAchievements(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return repo.findUserAchievements(id, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findUserGroups(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return repo.findUserGroups(id, sort, offset, limit);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public UUID insertUserAchievement(@NotNull UUID id, @NotNull UUID achievement_id) {
        return repo.insertUserAchievement(id, achievement_id);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public void deleteUserAchievement(@NotNull UUID id, @NotNull UUID achievement_id) {
        repo.deleteUserAchievement(id, achievement_id);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
    public Integer test() {
        conf.test();
        feat.test();
        prod.test();
        auth.test();
        produceUserDeletedEvent(UUID.randomUUID());
        return 0;
    }
}
