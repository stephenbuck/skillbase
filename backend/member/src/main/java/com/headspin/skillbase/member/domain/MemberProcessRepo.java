package com.headspin.skillbase.member.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface MemberProcessRepo {

    UUID insert(@NotNull @Valid MemberProcess process);

    void delete(@NotNull UUID process_id);

    MemberProcess update(@NotNull @Valid MemberProcess process);

    Optional<MemberProcess> findById(@NotNull UUID process_id);

    List<MemberProcess> findAll(String sort, Integer offset, Integer limit);

    Long count();
}