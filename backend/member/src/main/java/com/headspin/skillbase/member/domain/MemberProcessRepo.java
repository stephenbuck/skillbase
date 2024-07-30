package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberProcessRepo {

    public UUID insert(@NotNull @Valid MemberProcess process);

    public void delete(@NotNull UUID id);

    public MemberProcess update(@NotNull @Valid MemberProcess process);

    public Optional<MemberProcess> findById(@NotNull UUID id);

    public List<MemberProcess> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}