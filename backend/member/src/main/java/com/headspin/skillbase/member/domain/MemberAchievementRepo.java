package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberAchievementRepo {

    public UUID insert(@NotNull @Valid MemberAchievement achievement);

    public void delete(@NotNull UUID achievement_id);

    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement);

    public Optional<MemberAchievement> findById(@NotNull UUID achievement_id);

    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}