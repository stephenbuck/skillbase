package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberAchievementRepo;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
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
    private MemberAuthProvider auth;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonStorageProvider stor;

    /**
     * Insert a member achievement.
     *
     * @param achievement The new achievement.
     * @return The id of the new achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final MemberAchievement achievement) throws Exception {
        final UUID achievement_id = repo.insert(achievement);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_CREATED,
                MemberAchievement.toJson(achievement));
        return achievement_id;
    }

    /**
     * Delete a member achievement.
     *
     * @param achievement_id The requested achievement id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID achievement_id) throws Exception {
        repo.delete(achievement_id);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_DELETED,
                "{}");
    }

    /**
     * Update a member achievement.
     *
     * @param achievement The updated achievement.
     * @return The updated achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public MemberAchievement update(@NotNull @Valid final MemberAchievement achievement) throws Exception {
        final MemberAchievement updated = repo.update(achievement);
        evnt.produce(
                MemberEvent.MEMBER_EVENT_TOPIC,
                MemberEvent.MEMBER_ACHIEVEMENT_UPDATED,
                MemberAchievement.toJson(achievement));
        return updated;
    }

    /**
     * Find a member achievement by id.
     *
     * @param achievement_id The requested achievement id.
     * @return An optional member achievement.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<MemberAchievement> findById(@NotNull final UUID achievement_id) throws Exception {
        return repo.findById(achievement_id);
    }

    /**
     * Find all member achievements.
     *
     * @param sort   Sort field.
     * @param offset Offset of first result.
     * @param limit  Limit of results returned.
     * @return A list of member achievements.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<MemberAchievement> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Return a count of member achievements.
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
        auth.test();
        conf.test();
        evnt.test();
        feat.test();
        stor.test();
        return 0;
    }
}
