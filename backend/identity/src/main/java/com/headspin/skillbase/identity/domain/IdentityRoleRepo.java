package com.headspin.skillbase.identity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface IdentityRoleRepo {

    @Transactional
    public UUID insert(@NotNull IdentityRole role);

    @Transactional
    public void delete(@NotNull IdentityRole role);

    @Transactional
    public IdentityRole update(@NotNull IdentityRole role);

    @Transactional
    public void deleteById(@NotNull UUID id);

    public Optional<IdentityRole> findById(@NotNull UUID id);

    public List<IdentityRole> findAll(@NotNull String sort, @Null Integer offset, @Null Integer limit);

    public List<IdentityRole> findAllByGroupId(@NotNull UUID groupId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<IdentityRole> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}