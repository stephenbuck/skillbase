package com.headspin.skillbase.identity.infrastructure.keycloak;

import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityProvider;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityUser;

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

@Slf4j
public class IdentityProviderKeycloak implements IdentityProvider {

    private RealmResource getRealm() {

        Keycloak admin = KeycloakBuilder.builder().serverUrl("http://127.0.0.0:18080").realm("master")
                .clientId("admin-cli").grantType(OAuth2Constants.PASSWORD).username("admin").password("admin").build();

        RealmResource realm = admin.realm("master");

        return realm;
    }

    @Override
    public void insertUser(UUID id, IdentityUser user) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        UserRepresentation urep = new UserRepresentation();
        urep.setId(id.toString());
        urep.setEmail(user.email());
        urep.setUsername(user.userName());
        urep.setFirstName(user.userName());
        urep.setLastName(user.lastName());
        urep.setEnabled(true);
        users.create(urep);
    }

    @Override
    public void deleteUser(UUID id) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        users.delete(id.toString());
    }

    @Override
    public void updateUser(IdentityUser user) {
        RealmResource realm = getRealm();
        UsersResource users = realm.users();
        UserResource ures = users.get(user.id().toString());
        UserRepresentation urep = ures.toRepresentation();
        ures.update(urep);
    }

    @Override
    public void insertGroup(UUID id, IdentityGroup group) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupRepresentation grep = new GroupRepresentation();
        grep.setId(id.toString());
        grep.setName(group.title());
        groups.add(grep);
    }

    @Override
    public void deleteGroup(UUID id) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupResource gres = groups.group(id.toString());
        gres.remove();
    }

    @Override
    public void updateGroup(IdentityGroup group) {
        RealmResource realm = getRealm();
        GroupsResource groups = realm.groups();
        GroupResource gres = groups.group(group.id().toString());
        GroupRepresentation grep = gres.toRepresentation();
        gres.update(grep);
    }

    @Override
    public void insertRole(UUID id, IdentityRole role) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleRepresentation rrep = new RoleRepresentation();
        rrep.setId(id.toString());
        rrep.setName(role.title());
        roles.create(rrep);
    }

    @Override
    public void deleteRole(UUID id) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleResource rres = roles.get(id.toString());
        rres.remove();
    }

    @Override
    public void updateRole(IdentityRole role) {
        RealmResource realm = getRealm();
        RolesResource roles = realm.roles();
        RoleResource rres = roles.get(role.id().toString());
        RoleRepresentation rrep = rres.toRepresentation();
        rres.update(rrep);
    }
}
