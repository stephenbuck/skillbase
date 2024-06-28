package com.headspin.skillbase.identity.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface IdentityGroupRepo {

    @Transactional
    public void insert(@NotNull IdentityGroup group);

    @Transactional
    public void delete(@NotNull IdentityGroup group);

    @Transactional
    public IdentityGroup update(@NotNull IdentityGroup group);

    @Transactional
    public void deleteById(@NotNull UUID id);

    public Optional<IdentityGroup> findById(@NotNull UUID id);

    public List<IdentityGroup> findAll(@NotNull String sort, @Null Integer offset, @Null Integer limit);

    public List<IdentityGroup> findAllByRoleId(@NotNull UUID roleId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<IdentityGroup> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}