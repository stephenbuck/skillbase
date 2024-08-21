package com.headspin.skillbase.storage.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface StorageCategoryRepo {

    public UUID insert(@NotNull @Valid StorageCategory category);

    public void delete(@NotNull UUID category_id);

    public StorageCategory update(@NotNull @Valid StorageCategory category);

    public Optional<StorageCategory> findById(@NotNull UUID category_id);

    public List<StorageCategory> findAll(String sort, Integer offset, Integer limit);

    public List<StorageCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public List<StorageCategory> findCategoryCategories(@NotNull UUID category_id, String sort, Integer offset, Integer limit);

    public void insertCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    public void deleteCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id);

    public Long count();
}