package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCredentialRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CatalogCredential credential);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CatalogCredential update(@NotNull @Valid CatalogCredential credential);

    public Optional<CatalogCredential> findById(@NotNull UUID id);

    public List<CatalogCredential> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogCredential> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public Long count();
}