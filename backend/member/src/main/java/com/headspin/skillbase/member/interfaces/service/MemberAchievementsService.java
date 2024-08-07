package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

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
@LoginConfig(authMethod = "MP-JWT", realmName = "skillbase")
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

    /**
     * Inserts a new member achievement.
     *
     * @param achievement The new achievement.
     * @return The id of the new achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberAchievement achievement) {
        UUID achievement_id = repo.insert(achievement);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_ACHIEVEMENT_CREATED,
            Json.createObjectBuilder()
                .add("achievement_id", String.valueOf(achievement.achievement_id))
                .add("user_id", String.valueOf(achievement.user_id))
                .add("state", achievement.state)
                .add("title", achievement.title)
                .add("note", achievement.note)
                .add("created_at", String.valueOf(achievement.created_at))
                .add("updated_at", String.valueOf(achievement.updated_at))
                .build());
        return achievement_id;
    }

    /**
     * Deletes a member achievement given an id.
     *
     * @param achievement_id The requested achievement id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID achievement_id) {
        repo.delete(achievement_id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_ACHIEVEMENT_DELETED,
            Json.createObjectBuilder()
                .add("achievement_id", String.valueOf(achievement_id))
                .build());
    }

    /**
     * Updates an existing member achievement.
     *
     * @param achievement The updated achievement.
     * @return The updated achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement) {
        MemberAchievement updated = repo.update(achievement);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_ACHIEVEMENT_UPDATED,
            Json.createObjectBuilder()
                .add("achievement_id", String.valueOf(updated.achievement_id))
                .add("user_id", String.valueOf(updated.user_id))
                .add("state", updated.state)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a member achievement given an id.
     *
     * @param achievement_id The requested achievement id.
     * @return An optional member achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<MemberAchievement> findById(@NotNull UUID achievement_id) {
        return repo.findById(achievement_id);
    }

    /**
     * Returns a list of all member achievements.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of member achievements.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a count of member achievements.
     *
     * @return The count.
     * @since 1.0
     */
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
