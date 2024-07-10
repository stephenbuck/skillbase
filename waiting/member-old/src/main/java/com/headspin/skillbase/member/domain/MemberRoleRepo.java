package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

public interface MemberRoleRepo {

    @Transactional
    public UUID insert(@NotNull MemberRole role);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public MemberRole update(@NotNull MemberRole role);

    public Optional<MemberRole> findById(@NotNull UUID id);

    public List<MemberRole> findAll(@NotNull String sort, Integer offset, Integer limit);

    public List<MemberRole> findAllByGroupId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit);

    public List<MemberRole> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit);

    public Long count();
}