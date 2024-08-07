package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogSkillRepo {

    public UUID insert(@NotNull @Valid CatalogSkill skill);

    public void delete(@NotNull UUID skill_id);

    public CatalogSkill update(@NotNull @Valid CatalogSkill skill);

    public Optional<CatalogSkill> findById(@NotNull UUID skill_id);

    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public List<CatalogCredential> findSkillCredentials(@NotNull UUID skill_id, String sort, Integer offset,
            Integer limit);

    public void insertSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id);

    public void deleteSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id);

    public Long count();
}