package com.headspin.skillbase.member.infrastructure.auth;

import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
public class MemberAuthProviderKeycloak implements MemberAuthProvider {

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.keycloak.url")
    private String configUrl;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.keycloak.realm")
    private String configRealm;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.keycloak.client")
    private String configClient;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.keycloak.username")
    private String configUsername;

    @Inject
    @ConfigProperty(name = "com.headspin.skillbase.member.keycloak.password")
    private String configPassword;

    private Keycloak admin;
    private RealmResource realm;
    private UsersResource users;

    public MemberAuthProviderKeycloak() {

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
    public void insertUser(UUID id, MemberUser user) {
    }

    @Override
    public void deleteUser(UUID user_id) {
        users.delete(String.valueOf(user_id));
    }

    @Override
    public void updateUser(MemberUser user) {
    }

    @Override
    public void insertGroup(UUID id, MemberGroup group) {
    }

    @Override
    public void deleteGroup(UUID group_id) {
    }

    @Override
    public void updateGroup(MemberGroup group) {
    }

    @Override
    public void test() {
        log.info("test:");
    }
}
