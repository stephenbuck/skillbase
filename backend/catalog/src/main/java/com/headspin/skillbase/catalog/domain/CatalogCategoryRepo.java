package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CatalogCategoryRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category);

    public Optional<CatalogCategory> findById(@NotNull UUID id);

    public List<CatalogCategory> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CatalogCategory> findAllByParentId(@NotNull UUID parentId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public Long count();
}