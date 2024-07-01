package com.headspin.skillbase.certify.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertifyModelRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CertifyModel model);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CertifyModel update(@NotNull @Valid CertifyModel model);

    public Optional<CertifyModel> findById(@NotNull UUID id);

    public List<CertifyModel> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CertifyModel> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CertifyModel> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public Long count();
}