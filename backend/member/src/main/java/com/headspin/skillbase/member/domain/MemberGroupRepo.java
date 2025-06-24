package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberGroupRepo {

    UUID insert(@NotNull @Valid MemberGroup group);

    void delete(@NotNull UUID group_id);

    MemberGroup update(@NotNull @Valid MemberGroup group);

    Optional<MemberGroup> findById(@NotNull UUID group_id);

    List<MemberGroup> findAll(String sort, Integer offset, Integer limit);

    List<MemberUser> findGroupUsers(@NotNull UUID group_id, String sort, Integer offset, Integer limit);

    void insertGroupUser(@NotNull UUID group_id, @NotNull UUID user_id);

    void deleteGroupUser(@NotNull UUID group_id, @NotNull UUID user_id);

    Long count();
}