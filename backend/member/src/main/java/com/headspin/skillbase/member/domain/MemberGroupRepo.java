package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberGroupRepo {

    @Transactional
    public UUID insert(@NotNull @Valid MemberGroup group);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public MemberGroup update(@NotNull @Valid MemberGroup group);

    public Optional<MemberGroup> findById(@NotNull UUID id);

    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit);

    public List<MemberUser> findGroupUsers(@NotNull UUID id, String sort, Integer offset, Integer limit);

    @Transactional
    public void insertGroupUser(@NotNull UUID id, @NotNull UUID user_id);

    @Transactional
    public void deleteGroupUser(@NotNull UUID id, @NotNull UUID user_id);

    public Long count();
}