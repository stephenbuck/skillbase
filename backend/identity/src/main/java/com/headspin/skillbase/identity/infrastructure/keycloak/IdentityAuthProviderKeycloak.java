package com.headspin.skillbase.identity.infrastructure.keycloak;

import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.providers.IdentityAuthProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

/*
 * IdentityAuthProviderKeycloak implements the IdentityAuthProvider
 * interface using the Keycloak IAM. The domain entities User,
 * Group and Role have peer objects on the Keycloak side and
 * this provider translates between the two.
 */

@Slf4j
@ApplicationScoped
public class IdentityAuthProviderKeycloak implements IdentityAuthProvider {

    private RealmResource getRealm() {

        Keycloak admin = KeycloakBuilder.builder().serverUrl("http://localhost:18080").realm("master")
                .clientId("admin-cli").grantType(OAuth2Constants.PASSWORD).username("admin").password("admin").build();

        RealmResource realm = admin.realm("master");

        return realm;
    }

    @Override
    public void insertUser(UUID id, IdentityUser user) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        UserRepresentation urep = new UserRepresentation();
        urep.setId(String.valueOf(id));
        urep.setEmail(user.email);
        urep.setUsername(user.userName);
        urep.setFirstName(user.userName);
        urep.setLastName(user.lastName);
        urep.setEnabled(true);
        users.create(urep);
    }

    @Override
    public void deleteUser(UUID id) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        users.delete(String.valueOf(id));
    }

    @Override
    public void updateUser(IdentityUser user) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        UserResource ures = users.get(String.valueOf(user.id));
        UserRepresentation urep = ures.toRepresentation();
        ures.update(urep);
    }

    @Override
    public void insertGroup(UUID id, IdentityGroup group) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupRepresentation grep = new GroupRepresentation();
        grep.setId(String.valueOf(id));
        grep.setParentId("");
        grep.setName(group.title);
        grep.setPath("");
        grep.setSubGroupCount(0L);
        Response resp = groups.add(grep);
        log.info("resp = {}", resp);
    }

    @Override
    public void deleteGroup(UUID id) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupResource gres = groups.group(String.valueOf(id));
        gres.remove();
    }

    @Override
    public void updateGroup(IdentityGroup group) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupResource gres = groups.group(String.valueOf(group.id));
        GroupRepresentation grep = gres.toRepresentation();
        gres.update(grep);
    }

    @Override
    public void insertRole(UUID id, IdentityRole role) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleRepresentation rrep = new RoleRepresentation();
        rrep.setId(String.valueOf(id));
        rrep.setName(role.title);
        roles.create(rrep);
    }

    @Override
    public void deleteRole(UUID id) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleResource rres = roles.get(String.valueOf(id));
        rres.remove();
    }

    @Override
    public void updateRole(IdentityRole role) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleResource rres = roles.get(String.valueOf(role.id));
        RoleRepresentation rrep = rres.toRepresentation();
        rres.update(rrep);
    }
}
