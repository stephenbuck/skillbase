package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface CatalogSkillRepo {

    @Transactional
    public void insert(@NotNull @Valid CatalogSkill skill);

    @Transactional
    public void delete(@NotNull @Valid CatalogSkill skill);

    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill);

    @Transactional
    public void deleteById(@NotNull UUID id);

    public Optional<CatalogSkill> findById(@NotNull UUID id);

    public List<CatalogSkill> findAll(@Null String sort, @Null Integer offset, @Null Integer limit);

    public List<CatalogSkill> findAllByCategoryId(@NotNull UUID categoryId, @Null String sort, @Null Integer offset,
            @Null Integer limit);

    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit);
}