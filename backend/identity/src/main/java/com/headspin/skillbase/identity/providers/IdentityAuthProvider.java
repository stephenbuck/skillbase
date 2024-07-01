package com.headspin.skillbase.identity.providers;

import java.util.UUID;

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityUser;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface IdentityAuthProvider {

    public void insertUser(UUID id, IdentityUser user);

    public void deleteUser(UUID id);

    public void updateUser(IdentityUser user);

    public void insertGroup(UUID id, IdentityGroup group);

    public void deleteGroup(UUID id);

    public void updateGroup(IdentityGroup group);

    public void insertRole(UUID id, IdentityRole role);

    public void deleteRole(UUID id);

    public void updateRole(IdentityRole role);

}
