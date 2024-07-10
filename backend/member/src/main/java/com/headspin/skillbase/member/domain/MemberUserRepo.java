package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberUserRepo {

    @Transactional
    public UUID insert(@NotNull @Valid MemberUser user);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public MemberUser update(@NotNull @Valid MemberUser user);

    public Optional<MemberUser> findById(@NotNull UUID id);

    public List<MemberUser> findAll(String sort, Integer offset, Integer limit);

    public Long count();
}