package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCategoryRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory user);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory user);

    public Optional<CatalogCategory> findById(@NotNull UUID id);

    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public Long count();
}