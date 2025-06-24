package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCredentialRepo {

    UUID insert(@NotNull @Valid CatalogCredential credential);

    void delete(@NotNull UUID credential_id);

    CatalogCredential update(@NotNull @Valid CatalogCredential credential);

    Optional<CatalogCredential> findById(@NotNull UUID credential_id);

    List<CatalogCredential> findAll(String sort, Integer offset, Integer limit);

    List<CatalogCredential> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    Long count();
}