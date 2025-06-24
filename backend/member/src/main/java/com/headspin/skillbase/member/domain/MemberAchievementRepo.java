package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberAchievementRepo {

    UUID insert(@NotNull @Valid MemberAchievement achievement);

    void delete(@NotNull UUID achievement_id);

    MemberAchievement update(@NotNull @Valid MemberAchievement achievement);

    Optional<MemberAchievement> findById(@NotNull UUID achievement_id);

    List<MemberAchievement> findAll(String sort, Integer offset, Integer limit);

    Long count();
}