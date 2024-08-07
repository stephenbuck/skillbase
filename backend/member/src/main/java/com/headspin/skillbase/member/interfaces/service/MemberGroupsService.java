package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
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
    private MemberConfigProvider conf;

    @Inject
    private MemberFeaturesProvider feat;

    @Inject
    private MemberEventsProvider evnt;

    @Inject
    private MemberAuthProvider auth;

    /**
     * Inserts a new member group.
     *
     * @param group The new group.
     * @return The id of the new group.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberGroup group) {
        UUID group_id = repo.insert(group);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_CREATED,
            Json.createObjectBuilder()
                .add("group_id", String.valueOf(group.group_id))
                .add("title", group.title)
                .add("note", group.note)
                .add("created_at", String.valueOf(group.created_at))
                .add("updated_at", String.valueOf(group.updated_at))
                .build());
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
    public void delete(@NotNull UUID group_id) {
        repo.delete(group_id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_DELETED,
            Json.createObjectBuilder()
                .add("group_id", String.valueOf(group_id))
                .build());
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
    public MemberGroup update(@NotNull @Valid MemberGroup group) {
        MemberGroup updated = repo.update(group);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_GROUP_UPDATED,
            Json.createObjectBuilder()
                .add("group_id", String.valueOf(updated.group_id))
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
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
    public Optional<MemberGroup> findById(@NotNull UUID group_id) {
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
    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findGroupUsers(@NotNull UUID group_id, String sort, Integer offset, Integer limit) {
        return repo.findGroupUsers(group_id, sort, offset, limit);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public void insertGroupUser(@NotNull UUID group_id, @NotNull UUID user_id) {
        repo.insertGroupUser(group_id, user_id);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public void deleteGroupUser(@NotNull UUID group_id, @NotNull UUID user_id) {
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
        conf.test();
        feat.test();
        evnt.test();
        auth.test();
        return 0;
    }
}
