package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberUserRepo {

    UUID insert(@NotNull @Valid MemberUser user);

    void delete(@NotNull UUID user_id);

    MemberUser update(@NotNull @Valid MemberUser user);

    Optional<MemberUser> findById(@NotNull UUID user_id);

    List<MemberUser> findAll(String sort, Integer offset, Integer limit);

    List<MemberAchievement> findUserAchievements(@NotNull UUID user_id, String sort, Integer offset, Integer limit);

    List<MemberGroup> findUserGroups(@NotNull UUID user_id, String sort, Integer offset, Integer limit);

    void insertUserAchievement(@NotNull UUID user_id, @NotNull UUID achievement_id);

    void deleteUserAchievement(@NotNull UUID user_id, @NotNull UUID achievement_id);

    Long count();
}