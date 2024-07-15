package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberProcessRepo {

    @Transactional
    public UUID insert(@NotNull @Valid MemberProcess process);

    @Transactional
    public boolean delete(@NotNull UUID id);

    @Transactional
    public MemberProcess update(@NotNull @Valid MemberProcess process);

    public Optional<MemberProcess> findById(@NotNull UUID id);

    public List<MemberProcess> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}