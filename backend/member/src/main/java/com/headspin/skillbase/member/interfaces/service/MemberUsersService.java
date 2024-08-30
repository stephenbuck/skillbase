package com.headspin.skillbase.member.interfaces.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;
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
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Member users service.
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
public class MemberUsersService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberUserRepo repo;

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
     * Inserts a new member user.
     *
     * @param user The new user.
     * @return The id of the new user.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final MemberUser user) {
        final UUID user_id = repo.insert(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_CREATED,
            Json.createObjectBuilder()
                .add("user_id", String.valueOf(user.user_id))
                .add("is_enabled", user.is_enabled)
                .add("user_name", user.user_name)
                .add("first_name", user.first_name)
                .add("last_name", user.last_name)
                .add("email", user.email)
                .add("phone", user.phone)
                .add("note", user.note)
                .add("image_id", user.image_id)
                .add("created_at", String.valueOf(user.created_at))
                .add("updated_at", String.valueOf(user.updated_at))
                .build());
        return user_id;
    }

    /**
     * Deletes a member user given an id.
     *
     * @param user_id The requested user id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID user_id) {
        repo.delete(user_id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_DELETED,
            Json.createObjectBuilder()
                .add("user_id", String.valueOf(user_id))
                .build());
    }

    /**
     * Updates an existing member user.
     *
     * @param user The updated user.
     * @return The updated user.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberUser update(@NotNull @Valid final MemberUser user) {
        final MemberUser updated = repo.update(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_UPDATED,
            Json.createObjectBuilder()
                .add("user_id", String.valueOf(updated.user_id))
                .add("is_enabled", updated.is_enabled)
                .add("user_name", updated.user_name)
                .add("first_name", updated.first_name)
                .add("last_name", updated.last_name)
                .add("email", updated.email)
                .add("phone", updated.phone)
                .add("note", updated.note)
                .add("image_id", updated.image_id)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Uploads (or replaces) a member image.
     *
     * @param user_id The requested user id.
     * @param input The input stream of the image.
     * @return The image id.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    @Transactional
    public String uploadImage(@NotNull final UUID user_id, @NotNull final InputStream input, @NotNull final Long size) throws Exception {
        final MemberUser user = repo.findById(user_id).get();
        user.image_id = stor.uploadObject(input, size);
        repo.update(user);
        return user.image_id;
    }

    /**
     * Downloads a member image.
     *
     * @param user_id The requested user id.
     * @return The image input stream.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public InputStream downloadImage(@NotNull final UUID user_id) throws Exception {
        final MemberUser user = repo.findById(user_id).get();
        return stor.downloadObject(user.image_id);
    }

    /**
     * Deletes a member image.
     *
     * @param user_id The requested user id.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    @Transactional
    public void deleteImage(@NotNull final UUID user_id) throws Exception {
        final MemberUser user = findById(user_id).get();
        stor.deleteObject(user.image_id);
        user.image_id = null;
        repo.update(user);
    }

    /**
     * Returns a member user given an id.
     *
     * @param user_id The requested user id.
     * @return An optional member user.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public Optional<MemberUser> findById(@NotNull final UUID user_id) {
        return repo.findById(user_id);
    }

    /**
     * Returns a list of all member users.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of member users.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public List<MemberUser> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a list of all achievements for a member user.
     *
     * @param user_id The requested user id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of member achievements.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<MemberAchievement> findUserAchievements(@NotNull final UUID user_id, final String sort, final Integer offset, final Integer limit) {
        return repo.findUserAchievements(user_id, sort, offset, limit);
    }

    /**
     * Returns a list of all groups for a member user.
     *
     * @param user_id The requested user id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of member groups.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findUserGroups(@NotNull final UUID user_id, final String sort, final Integer offset, final Integer limit) {
        return repo.findUserGroups(user_id, sort, offset, limit);
    }

    /**
     * Inserts an achievement given a member user id.
     *
     * @param user_id The requested user id.
     * @param achievement_id The requested achievement id.
     * @return TBD
     * @since 1.0
     */
//    @RolesAllow({ "Admin" })
    @Transactional
    public void insertUserAchievement(@NotNull final UUID user_id, @NotNull final UUID achievement_id) {
        repo.insertUserAchievement(user_id, achievement_id);
    }

    /**
     * Deletes an achievement given a member user id.
     *
     * @param user_id The requested user id.
     * @param achievement_id The requested achievement id.
     * @return TBD
     * @since 1.0
     */
//    @RolesAllow({ "Admin" })
    @Transactional
    public void deleteUserAchievement(@NotNull final UUID user_id, @NotNull final UUID achievement_id) {
        repo.deleteUserAchievement(user_id, achievement_id);
    }

    /**
     * Returns a count of member users.
     *
     * @return The count.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
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
