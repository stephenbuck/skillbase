package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogSkillRepo {

    public UUID insert(@NotNull @Valid CatalogSkill skill);

    public boolean delete(@NotNull UUID id);

    public CatalogSkill update(@NotNull @Valid CatalogSkill skill);

    public Optional<CatalogSkill> findById(@NotNull UUID id);

    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit);

    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit);

    public List<CatalogCredential> findSkillCredentials(@NotNull UUID id, String sort, Integer offset,
            Integer limit);

    public boolean insertSkillCredential(@NotNull UUID id, @NotNull UUID credential_id);

    public boolean deleteSkillCredential(@NotNull UUID id, @NotNull UUID credential_id);

    public Long count();
}