package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCategoryRepo {

    public UUID insert(@NotNull @Valid CatalogCategory category);

    public void delete(@NotNull UUID id);

    public CatalogCategory update(@NotNull @Valid CatalogCategory category);

    public Optional<CatalogCategory> findById(@NotNull UUID id);

    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public List<CatalogCategory> findCategoryCategories(@NotNull UUID id, String sort, Integer offset, Integer limit);

    public List<CatalogSkill> findCategorySkills(@NotNull UUID id, String sort, Integer offset, Integer limit);

    public boolean insertCategoryCategory(@NotNull UUID id, @NotNull UUID category_id);

    public boolean deleteCategoryCategory(@NotNull UUID id, @NotNull UUID category_id);

    public boolean insertCategorySkill(@NotNull UUID id, @NotNull UUID skill_id);

    public boolean deleteCategorySkill(@NotNull UUID id, @NotNull UUID skill_id);

    public Long count();
}