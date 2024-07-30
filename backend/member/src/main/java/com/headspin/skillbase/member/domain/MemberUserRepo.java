package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberUserRepo {

    public UUID insert(@NotNull @Valid MemberUser user);

    public void delete(@NotNull UUID id);

    public MemberUser update(@NotNull @Valid MemberUser user);

    public Optional<MemberUser> findById(@NotNull UUID id);

    public List<MemberUser> findAll(String sort, Integer offset, Integer limit);

    public List<MemberAchievement> findUserAchievements(@NotNull UUID id, String sort, Integer offset, Integer limit);

    public List<MemberGroup> findUserGroups(@NotNull UUID id, String sort, Integer offset, Integer limit);

    public UUID insertUserAchievement(@NotNull UUID id, @NotNull UUID achievement_id);

    public void deleteUserAchievement(@NotNull UUID id, @NotNull UUID achievement_id);

    public Long count();
}