package com.headspin.skillbase.cert.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CertRepo {

    @Transactional
    void insert(
        @NotNull @Valid Cert cert);

    @Transactional
    void delete(
        @NotNull @Valid Cert cert);

    @Transactional
    Cert update(
        @NotNull @Valid Cert cert);
    
    @Transactional
    void deleteById(
        @NotNull UUID id);

    Optional<Cert> findById(
        @NotNull UUID id);

    List<Cert> findAll(
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit);

    List<Cert> findAllBySkillId(
        @NotNull UUID skillId,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit);

    List<Cert> findAllByUserId(
        @NotNull UUID userId,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit);
}