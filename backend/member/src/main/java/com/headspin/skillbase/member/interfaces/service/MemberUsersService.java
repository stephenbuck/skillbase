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
import com.headspin.skillbase.member.providers.MemberConfigProvider;
import com.headspin.skillbase.member.providers.MemberEventsProvider;
import com.headspin.skillbase.member.providers.MemberFeaturesProvider;
import com.headspin.skillbase.member.providers.MemberFilesProvider;

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
    private MemberConfigProvider conf;

    @Inject
    private MemberFeaturesProvider feat;

    @Inject
    private MemberEventsProvider evnt;

    @Inject
    private MemberAuthProvider auth;

    @Inject
    private MemberFilesProvider fsys;

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
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Uploads (or replaces) a member image.
     *
     * @param user_id The requested user id.
     * @param type The MIME type of the image.
     * @param input The input stream of the image.
     * @return The updated member user.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public MemberUser uploadImage(@NotNull final UUID user_id, @NotNull final String type, @NotNull final InputStream input) throws IOException {
        final MemberUser current = findById(user_id).get();
        // final UUID dstUuid = UUID.randomUUID();
        // fsys.upload(user_id, input, dstUuid);
        // BOZO current.image_id = fsys.upload(user_id, input);
        // BOZO current.image_type = type;
        final MemberUser updated = repo.update(current);
        return updated;
    }

    /**
     * Downloads a member image.
     *
     * @param user_id The requested user id.
     * @param The image MIME type.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public String downloadImage(@NotNull final UUID user_id, @NotNull final OutputStream output) throws IOException {
        /*
        final MemberUser current = findById(user_id).get();
        if (current.image_id != null && current.image_type != null) {
            fsys.download(user_id, current.image_id, output);
            return current.image_type;
        }
        */
        return null;
    }

    /**
     * Deletes a member image.
     *
     * @param user_id The requested user id.
     * @return The updated member user.
     * @since 1.0
     */
    //    @RolesAllowed({ "Admin" })
    public MemberUser deleteImage(@NotNull final UUID user_id) throws IOException {
        final MemberUser current = findById(user_id).get();
        // BOZO current.image_id = null;
        // BOZO current.image_type = null;
        final MemberUser updated = repo.update(current);
        return updated;
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
        conf.test();
        feat.test();
        evnt.test();
        auth.test();
        return 0;
    }
}
