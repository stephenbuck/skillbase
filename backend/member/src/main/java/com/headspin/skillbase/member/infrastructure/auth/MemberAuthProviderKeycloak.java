package com.headspin.skillbase.member.infrastructure.auth;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.providers.MemberAuthProvider;

/*
 * MemberAuthProviderKeycloak implements the MemberAuthProvider
 * interface using the Keycloak IAM. The domain entities User,
 * Group and Role have peer objects on the Keycloak side and
 * this provider translates between the two.
 */

@Slf4j
@ApplicationScoped
public class MemberAuthProviderKeycloak implements MemberAuthProvider {

    public MemberAuthProviderKeycloak() {
    }


    @Override
    public void test() {
        log.info("test");
    }
    
    @Override
    public void insertUser(UUID id, MemberUser user) {
    }

    @Override
    public void deleteUser(UUID id) {
    }

    @Override
    public void updateUser(MemberUser user) {
    }

    @Override
    public void insertGroup(UUID id, MemberGroup group) {
    }

    @Override
    public void deleteGroup(UUID id) {
    }

    @Override
    public void updateGroup(MemberGroup group) {
    }
}
