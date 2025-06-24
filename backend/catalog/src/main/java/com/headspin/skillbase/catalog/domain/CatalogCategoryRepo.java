package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogCategoryRepo {

    UUID insert(@NotNull /* BOZO @Valid */ CatalogCategory category);

    void delete(@NotNull UUID category_id);

    CatalogCategory update(@NotNull @Valid CatalogCategory category);

    Optional<CatalogCategory> findById(@NotNull UUID category_id);

    List<CatalogCategory> findAll(String sort, Integer offset, Integer limit);

    List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    List<CatalogCategory> findCategoryCategories(@NotNull UUID category_id, String sort, Integer offset, Integer limit);

    List<CatalogSkill> findCategorySkills(@NotNull UUID category_id, String sort, Integer offset, Integer limit);

    void insertCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    void deleteCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    void insertCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id);

    void deleteCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id);

    Long count();
}