package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberAchievementRepo {

    @Transactional
    public UUID insert(@NotNull @Valid MemberAchievement achievement);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement);

    public Optional<MemberAchievement> findById(@NotNull UUID id);

    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}