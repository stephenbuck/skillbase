package com.headspin.skillbase.certify.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertifyTaskRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CertifyTask task);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CertifyTask update(@NotNull @Valid CertifyTask task);

    public Optional<CertifyTask> findById(@NotNull UUID id);

    public List<CertifyTask> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CertifyTask> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CertifyTask> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public Long count();
}