package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberAchievementRepo;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.member.providers.MemberConfigProvider;
import com.headspin.skillbase.member.providers.MemberFeaturesProvider;
import com.headspin.skillbase.member.providers.MemberEventsProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Member achievements service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class MemberAchievementsService {

    @Resource
    private SessionContext ctx;

    @Inject
    private MemberAchievementRepo repo;

    @Inject
    private MemberConfigProvider conf;

    @Inject
    private MemberFeaturesProvider feat;

    @Inject
    private MemberEventsProvider evnt;

    @Inject
    private MemberAuthProvider auth;

    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberAchievement achievement) {
        UUID id = repo.insert(achievement);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_CREATED,
                Json.createObjectBuilder()
                        .add("id", String.valueOf(achievement.id))
                        .add("title", achievement.title)
                        .build());
        return id;
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_DELETED,
                Json.createObjectBuilder()
                        .add("id", String.valueOf(id))
                        .build());
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement) {
        MemberAchievement updated = repo.update(achievement);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_UPDATED,
                Json.createObjectBuilder()
                        .add("id", String.valueOf(updated.id))
                        .add("title", updated.title)
                        .build());
        return updated;
    }

    // @RolesAllowed({ "Admin" })
    public Optional<MemberAchievement> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    // @RolesAllowed({ "Admin" })
    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    // @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

    // @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        auth.test();
        return 0;
    }
}
