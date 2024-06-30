package com.headspin.skillbase.identity;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/*
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
*/

/*
server.error.whitelabel.enabled=false
spring.mvc.favicon.enabled=false
server.port = 8082

keycloak.client-id=jakarta-school
keycloak.client-secret=197bc3b4-64b0-452f-9bdb-fcaea0988e90
keycloak.scope=openid, profile
keycloak.authorization-grant-type=password

keycloak.authorization-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/auth
keycloak.user-info-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/userinfo
keycloak.token-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/token
keycloak.logout=http://localhost:8080/auth/realms/education/protocol/openid-connect/logout
keycloak.jwk-set-uri=http://localhost:8080/auth/realms/education/protocol/openid-connect/certs
keycloak.certs-id=vdaec4Br3ZnRFtZN-pimK9v1eGd3gL2MHu8rQ6M5SiE

logging.level.root=INFO
*/

@Slf4j
public class IdentityKeycloakTest {

    @Test
    void demoTestMethod() {
        Keycloak admin = KeycloakBuilder.builder().serverUrl("http://127.0.0.0:18080").realm("master")
                .clientId("admin-cli").grantType(OAuth2Constants.PASSWORD).username("admin").password("admin").build();

        RealmResource realm = admin.realm("master");
        log.info("realm = {}", realm);

        UsersResource users = realm.users();
        List<UserRepresentation> all = users.list();
        log.info("Users list {}", all.stream().map(user -> user.getUsername()).collect(Collectors.toList()));

        UserRepresentation ur = new UserRepresentation();
        ur.setId(UUID.randomUUID().toString());
        ur.setEmail("stephenbuck@mac.com");
        ur.setUsername("stephenbuck");
        ur.setFirstName("Stephen");
        ur.setLastName("Buck");
        log.info("ur = {}", ur);

        Response resp = users.create(ur);
        log.info("resp = {}", resp);

        /*
         * / FederatedIdentityRepresentation fi = new FederatedIdentityRepresentation(); fi.setUserId("");
         * fi.setUserName("foo"); fi.setIdentityProvider("bar"); users.addFederatedIdentity(fi);
         */

        UserResource user = users.get("<id>");

        GroupsResource groups = realm.groups();

        RolesResource roles = realm.roles();
    }
}