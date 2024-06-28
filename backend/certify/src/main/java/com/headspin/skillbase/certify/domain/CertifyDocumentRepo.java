package com.headspin.skillbase.certify.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertifyDocumentRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CertifyDocument document);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CertifyDocument update(@NotNull @Valid CertifyDocument document);

    public Optional<CertifyDocument> findById(@NotNull UUID id);

    public List<CertifyDocument> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CertifyDocument> findAllBySkillId(@NotNull UUID skillId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CertifyDocument> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}