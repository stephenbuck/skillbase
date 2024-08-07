package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberGroupRepo {

    public UUID insert(@NotNull @Valid MemberGroup group);

    public void delete(@NotNull UUID group_id);

    public MemberGroup update(@NotNull @Valid MemberGroup group);

    public Optional<MemberGroup> findById(@NotNull UUID group_id);

    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit);

    public List<MemberUser> findGroupUsers(@NotNull UUID group_id, String sort, Integer offset, Integer limit);

    public void insertGroupUser(@NotNull UUID group_id, @NotNull UUID user_id);

    public void deleteGroupUser(@NotNull UUID group_id, @NotNull UUID user_id);

    public Long count();
}