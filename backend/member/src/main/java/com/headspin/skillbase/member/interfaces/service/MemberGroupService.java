package com.headspin.skillbase.member.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
import com.headspin.skillbase.member.infrastructure.auth.MemberAuthProviderKeycloak;
import com.headspin.skillbase.member.infrastructure.config.MemberConfigProviderDefault;
import com.headspin.skillbase.member.infrastructure.feature.MemberFeatureProviderFlipt;
import com.headspin.skillbase.member.infrastructure.messaging.MemberEventProducer;
import com.headspin.skillbase.member.providers.MemberAuthProvider;
import com.headspin.skillbase.member.providers.MemberConfigProvider;
import com.headspin.skillbase.member.providers.MemberFeatureProvider;
import com.headspin.skillbase.member.providers.MemberProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class MemberGroupService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private MemberGroupRepo repo;

    private MemberConfigProvider conf = new MemberConfigProviderDefault();
    private MemberFeatureProvider feat = new MemberFeatureProviderFlipt();
    private MemberProducerProvider prod = new MemberEventProducer();
    private MemberAuthProvider auth = new MemberAuthProviderKeycloak();

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid MemberGroup group) {
        UUID id = repo.insert(group);
        prod.produce(MemberEvent.buildEvent(group.id, MemberEvent.MEMBER_GROUP_CREATED));
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(MemberEvent.buildEvent(id, MemberEvent.MEMBER_GROUP_DELETED));
        return result;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public MemberGroup update(@NotNull @Valid MemberGroup group) {
        MemberGroup updated = repo.update(group);
        prod.produce(MemberEvent.buildEvent(group.id, MemberEvent.MEMBER_GROUP_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<MemberGroup> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<MemberUser> findGroupUsers(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return repo.findGroupUsers(id, sort, offset, limit);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public boolean insertGroupUser(@NotNull UUID id, @NotNull UUID user_id) {
        return repo.insertGroupUser(id, user_id);
    }

//    @RolesAllow({ "Admin" })
    @Transactional
    public boolean deleteGroupUser(@NotNull UUID id, @NotNull UUID user_id) {
        return repo.deleteGroupUser(id, user_id);
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
        prod.produce(MemberEvent.buildEvent(UUID.randomUUID(), MemberEvent.MEMBER_GROUP_UPDATED));
        return 0;
    }
}
