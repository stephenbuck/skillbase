package com.headspin.skillbase.member.infrastructure.auth;

import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.providers.MemberAuthProvider;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

/**
 * Keycloak implementation of the member auth provider interface.
 * 
 * MemberAuthProviderKeycloak implements the MemberAuthProvider
 * interface using the Keycloak IAM. The domain entities User,
 * Group and Role have peer objects on the Keycloak side and
 * this provider translates between the two.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Alternative
@ApplicationScoped
public class MemberAuthProviderKeycloak implements MemberAuthProvider {

    private final Keycloak admin;
    private final RealmResource realm;
    private final UsersResource users;

    @Inject
    public MemberAuthProviderKeycloak(
            @ConfigProperty(name = "com.headspin.skillbase.member.auth.keycloak.url") final String configUrl,
            @ConfigProperty(name = "com.headspin.skillbase.member.auth.keycloak.realm") final String configRealm,
            @ConfigProperty(name = "com.headspin.skillbase.member.auth.keycloak.client") final String configClient,
            @ConfigProperty(name = "com.headspin.skillbase.member.auth.keycloak.username") final String configUsername,
            @ConfigProperty(name = "com.headspin.skillbase.member.auth.keycloak.password") final String configPassword) {
        this.admin = KeycloakBuilder.builder()
                .serverUrl(configUrl)
                .realm(configRealm)
                .clientId(configClient)
                .grantType(OAuth2Constants.PASSWORD)
                .username(configUsername)
                .password(configPassword)
                .build();
        this.realm = admin.realm(configRealm);
        this.users = realm.users();
    }

    @Override
    public void insertUser(final UUID id, final MemberUser user) {
    }

    @Override
    public void deleteUser(final UUID user_id) {
        users.delete(String.valueOf(user_id));
    }

    @Override
    public void updateUser(final MemberUser user) {
    }

    @Override
    public void insertGroup(final UUID id, final MemberGroup group) {
    }

    @Override
    public void deleteGroup(final UUID group_id) {
    }

    @Override
    public void updateGroup(final MemberGroup group) {
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
