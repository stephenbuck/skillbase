package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberAchievementRepo;
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
public class MemberAchievementService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private MemberAchievementRepo repo;

    private MemberConfigProvider conf = new MemberConfigProviderDefault();
    private MemberFeatureProvider feat = new MemberFeatureProviderFlipt();
    private MemberProducerProvider prod = new MemberEventProducerKafka();
    private MemberAuthProvider auth = new MemberAuthProviderKeycloak();

    private void produceAchievementCreatedEvent(MemberAchievement achievement) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_ACHIEVEMENT_CREATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(achievement.id))
                .add("title", achievement.title)
                .build()));
    }

    private void produceAchievementDeletedEvent(UUID id) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_ACHIEVEMENT_DELETED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build()));
    }

    private void produceAchievementUpdatedEvent(MemberAchievement achievement) {
        prod.produce(new MemberEvent(
            MemberEvent.MEMBER_ACHIEVEMENT_UPDATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(achievement.id))
                .add("title", achievement.title)
                .build()));
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberAchievement achievement) {
        UUID id = repo.insert(achievement);
        produceAchievementCreatedEvent(achievement);
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        produceAchievementDeletedEvent(id);
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement) {
        MemberAchievement updated = repo.update(achievement);
        produceAchievementUpdatedEvent(updated);
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<MemberAchievement> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    //    @RolesAllowed({ "Admin" })
    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
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
        return 0;
    }
}
