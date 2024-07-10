package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogSkillRepo {

    @Transactional
    public UUID insert(@NotNull @Valid CatalogSkill group);

    @Transactional
    public void delete(@NotNull UUID id);

    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill group);

    public Optional<CatalogSkill> findById(@NotNull UUID id);

    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public Long count();
}