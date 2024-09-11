package com.headspin.skillbase.member.interfaces.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonSearchProvider;
import com.headspin.skillbase.common.providers.CommonStorageProvider;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;
import com.headspin.skillbase.member.providers.MemberAuthProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
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

    @Inject
    private CommonSearchProvider srch;

    /**
     * Inserts a new member user.
     *
     * @param user The new user.
     * @return The id of the new user.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final MemberUser user) throws Exception {
        final UUID user_id = repo.insert(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_CREATED,
            MemberUser.toJson(user));
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
    public void delete(@NotNull final UUID user_id) throws Exception {
        repo.delete(user_id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_DELETED,
            "{}");
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
    public MemberUser update(@NotNull @Valid final MemberUser user) throws Exception {
        final MemberUser updated = repo.update(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_UPDATED,
            MemberUser.toJson(user));
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
    public Optional<MemberUser> findById(@NotNull final UUID user_id) throws Exception {
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
     * Uploads a member user image.
     *
     * @param user_id The requested user id.
     * @param input The image input stream.
     * @param size The size of the image (or -1 if unknown).
     * @param type The media type of the image (e.g. image/jpeg).
     * @return The id of the new image.
     * @since 1.0
     */
    @Retry
    @Timeout
    @Transactional
    //    @RolesAllowed({ "Admin" })
    public String uploadImage(@NotNull final UUID user_id, @NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type) throws Exception {

        // Fetch the user
        final MemberUser user = findById(user_id).get();

        // Save the old image
        final String old_image_id = user.image_id;

        // Upload the new image
        final String new_image_id = stor.uploadObject(input, size, type);
        
        // Update the user with the new image
        try {
            user.image_id = new_image_id;
            update(user);
        }

        // On exception, delete the new image and rethrow
        catch (Exception e) {
            stor.deleteObject(new_image_id);
            throw e;
        }

        // Delete the old image (it's an update)
        try {
            if (old_image_id != null) {
                stor.deleteObject(old_image_id);
            }
        }

        // On exception, just log, but don't rethrow
        catch (Exception e) {
            log.error("Update to delete old image", e);
        }

        // Return the new image id
        return new_image_id;
    }

    /**
     * Downloads a member user image.
     *
     * @param user_id The requested user id.
     * @return The storage object of the image.
     * @since 1.0
     */
    @Retry
    @Timeout
    //    @RolesAllowed({ "Admin" })
    public CommonStorageProvider.CommonStorageObject downloadImage(@NotNull final UUID user_id) throws Exception {
        return stor.downloadObject(findById(user_id).get().image_id);
    }

    /**
     * Deletes a member user image.
     *
     * @param user_id The requested user id.
     * @since 1.0
     */
    @Retry
    @Timeout
    @Transactional
    //    @RolesAllowed({ "Admin" })
    public void deleteImage(@NotNull final UUID user_id) throws Exception {

        // Fetch the user
        final MemberUser user = findById(user_id).get();

        // Save the old image id
        final String old_image_id = user.image_id;

        // Update the user image_id to null
        user.image_id = null;
        update(user);

        // Delete the old image
        try {
            if (old_image_id != null) {
                stor.deleteObject(old_image_id);
            }
        }

        // On exception, just log, but don't rethrow
        catch (Exception e) {
            log.error("Update to delete old image", e);
        }
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
        srch.test();
        stor.test();
        return 0;
    }
}
