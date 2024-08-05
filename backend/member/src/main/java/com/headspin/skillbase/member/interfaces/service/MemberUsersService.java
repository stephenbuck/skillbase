package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberGroup;
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

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberUser user) {
        UUID id = repo.insert(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_CREATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(user.id))
                .add("is_enabled", user.is_enabled)
                .add("user_name", user.user_name)
                .add("first_name", user.first_name)
                .add("last_name", user.last_name)
                .add("email", user.email)
                .add("phone", user.phone)
                .add("note", user.note)
                .add("created_at", String.valueOf(user.createdAt))
                .build());
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_DELETED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build());
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberUser update(@NotNull @Valid MemberUser user) {
        MemberUser updated = repo.update(user);
        evnt.produce(
            MemberEvent.MEMBER_EVENT_TOPIC,
            MemberEvent.MEMBER_USER_UPDATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(updated.id))
                .add("is_enabled", updated.is_enabled)
                .add("user_name", updated.user_name)
                .add("first_name", updated.first_name)
                .add("last_name", updated.last_name)
                .add("email", updated.email)
                .add("phone", updated.phone)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.createdAt))
                .add("updated_at", String.valueOf(updated.updatedAt))
                .build());
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
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        auth.test();
        return 0;
    }
}
