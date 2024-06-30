package com.headspin.skillbase.identity.domain;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public interface IdentityProvider {

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
