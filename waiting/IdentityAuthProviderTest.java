package com.headspin.skillbase.identity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.infrastructure.keycloak.IdentityAuthProviderKeycloak;
import com.headspin.skillbase.identity.providers.IdentityAuthProvider;

import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Disabled

/*
@Slf4j
@ExtendWith(WeldJunit5AutoExtension.class)
@AddPackages(IdentityAuthProvider.class)
@AddPackages(IdentityAuthProviderKeycloak.class)
*/
public class IdentityAuthProviderTest {
/*

    // @Inject
    // private IdentityAuthProvider auth;
    private IdentityAuthProviderKeycloak provider = new IdentityAuthProviderKeycloak();

    private IdentityGroup genGroup(int index) {

        UUID id = UUID.randomUUID();
        UUID parentId = null;
        String peerId = String.valueOf(id);
        String title = "group-" + index;
        String note = "note-" + index;
        byte[] icon = null;
        Date insertedAt = new Date();
        Date updatedAt = insertedAt;

        return new IdentityGroup(id, peerId, parentId, title, note, icon, insertedAt, updatedAt);
    }

    private IdentityRole genRole(int index) {

        UUID id = UUID.randomUUID();
        String peerId = String.valueOf(id);
        String title = "role-" + index;
        String note = "note-" + index;
        byte[] icon = null;
        Date insertedAt = new Date();
        Date updatedAt = insertedAt;

        return new IdentityRole(id, peerId, title, note, icon, insertedAt, updatedAt);
    }

    private IdentityUser genUser(int index) {

        UUID id = UUID.randomUUID();

        String peerId = null;
        String state = "";
        String userName = "user" + index;
        String firstName = "first-" + index;
        String lastName = "last" + index;
        String email = userName + "@skillbase.com";
        String phone = "000-000-0000";
        String note = "";
        byte[] icon = null;
        Date insertedAt = new Date();
        Date updatedAt = insertedAt;

        return new IdentityUser(id, peerId, state, userName, firstName, lastName, email, phone, note, icon, insertedAt,
                updatedAt);
    }

    private GroupRepresentation groupToRep(IdentityGroup group) {

        GroupRepresentation gr = new GroupRepresentation();

        gr.setId(String.valueOf(group.id()));
        gr.setParentId(String.valueOf(group.parentId()));
        gr.setName(group.title());

        return gr;
    }

    private RoleRepresentation roleToRep(IdentityRole role) {

        RoleRepresentation rr = new RoleRepresentation();

        rr.setId(String.valueOf(role.id()));
        rr.setName(role.title());
        rr.setDescription(role.note());

        return rr;
    }

    private UserRepresentation userToRep(IdentityUser user) {

        UserRepresentation ur = new UserRepresentation();

        ur.setId(String.valueOf(user.id()));
        ur.setEmail(user.email());
        ur.setUsername(user.userName());
        ur.setFirstName(user.firstName());
        ur.setLastName(user.lastName());

        return ur;
    }

    @Test
    public void testProvider() {
        assertNotNull(provider, "Provider not found");
    }

    @Test
    public void testInsert() {

        // IdentityRole role = genRole(index); provider.insertRole(role.id(), role);

        for (int gindex = 1; gindex < 10; gindex++) {
            IdentityGroup group = genGroup(gindex);
            provider.insertGroup(group.id(), group);
        }
    }
    */
}