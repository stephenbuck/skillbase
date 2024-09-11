package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
import com.headspin.skillbase.member.domain.MemberUser;
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
 * Member groups service.
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
public class MemberGroupsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private JsonWebToken jwt;

    @Inject
    private MemberGroupRepo repo;

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
     * Inserts a new member group.
     *
     * @param group The new group.
     * @return The id of the new group.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final MemberGroup group) throws Exception {
        final UUID group_id = repo.insert(group);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_CREATED,
            MemberGroup.toJson(group));
        return group_id;
    }

    /**
     * Deletes a member group given an id.
     *
     * @param group_id The requested group id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID group_id) throws Exception {
        repo.delete(group_id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_DELETED,
            "{}");
    }

    /**
     * Updates an existing member group.
     *
     * @param group The updated group.
     * @return The updated group.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberGroup update(@NotNull @Valid final MemberGroup group) throws Exception {
        final MemberGroup updated = repo.update(group);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_UPDATED,
            MemberGroup.toJson(group));
        return updated;
    }

    /**
     * Returns a member group given an id.
     *
     * @param group_id The requested group id.
     * @return An optional member group.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<MemberGroup> findById(@NotNull final UUID group_id) throws Exception {
        return repo.findById(group_id);
    }

    /**
     * Returns a list of all member groups.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of member groups.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findGroupUsers(@NotNull final UUID group_id, final String sort, final Integer offset, final Integer limit) {
        return repo.findGroupUsers(group_id, sort, offset, limit);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public void insertGroupUser(@NotNull final UUID group_id, @NotNull final UUID user_id) {
        repo.insertGroupUser(group_id, user_id);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public void deleteGroupUser(@NotNull final UUID group_id, @NotNull final UUID user_id) {
        repo.deleteGroupUser(group_id, user_id);
    }

    /**
     * Returns a count of member groups.
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
