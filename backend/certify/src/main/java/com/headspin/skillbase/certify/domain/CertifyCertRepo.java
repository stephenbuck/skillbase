package com.headspin.skillbase.certify.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertifyCertRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CertifyCert cert);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CertifyCert update(@NotNull @Valid CertifyCert cert);

    public Optional<CertifyCert> findById(@NotNull UUID id);

    public List<CertifyCert> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CertifyCert> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CertifyCert> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}