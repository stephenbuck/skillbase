package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCategoryRepo {

    public UUID insert(@NotNull /* BOZO @Valid */ CatalogCategory category);

    public void delete(@NotNull UUID category_id);

    public CatalogCategory update(@NotNull @Valid CatalogCategory category);

    public Optional<CatalogCategory> findById(@NotNull UUID category_id);

    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public List<CatalogCategory> findCategoryCategories(@NotNull UUID category_id, String sort, Integer offset, Integer limit);

    public List<CatalogSkill> findCategorySkills(@NotNull UUID category_id, String sort, Integer offset, Integer limit);

    public void insertCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    public void deleteCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    public void insertCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id);

    public void deleteCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id);

    public Long count();
}