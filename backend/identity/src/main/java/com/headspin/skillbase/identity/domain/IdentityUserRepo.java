package com.headspin.skillbase.identity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface IdentityUserRepo {

    @Transactional
    public void insert(@NotNull IdentityUser user);

    @Transactional
    public void delete(@NotNull IdentityUser user);

    @Transactional
    public IdentityUser update(@NotNull IdentityUser user);

    @Transactional
    public void deleteById(@NotNull UUID id);

    public Optional<IdentityUser> findById(@NotNull UUID id);

    public List<IdentityUser> findAll(@NotNull String sort, @Null Integer offset, @Null Integer limit);

    public List<IdentityUser> findAllByGroupId(@NotNull UUID groupId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<IdentityUser> findAllByRoleId(@NotNull UUID roleId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}