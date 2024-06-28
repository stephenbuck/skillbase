package com.headspin.skillbase.certify.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertifyProcessRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CertifyProcess cert);

    @Transactional
    public void delete(@NotNull @Valid CertifyProcess cert);

    @Transactional
    public CertifyProcess update(@NotNull @Valid CertifyProcess cert);

    @Transactional
    public void deleteById(@NotNull UUID id);

    public Optional<CertifyProcess> findById(@NotNull UUID id);

    public List<CertifyProcess> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CertifyProcess> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CertifyProcess> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}