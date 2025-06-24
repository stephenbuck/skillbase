package com.headspin.skillbase.catalog.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CatalogSkillRepo {

        UUID insert(@NotNull @Valid CatalogSkill skill);

        void delete(@NotNull UUID skill_id);

        CatalogSkill update(@NotNull @Valid CatalogSkill skill);

        Optional<CatalogSkill> findById(@NotNull UUID skill_id);

        List<CatalogSkill> findAll(String sort, Integer offset, Integer limit);

        List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
                        Integer limit);

        List<CatalogCredential> findSkillCredentials(@NotNull UUID skill_id, String sort, Integer offset,
                        Integer limit);

        void insertSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id);

        void deleteSkillCredential(@NotNull UUID skill_id, @NotNull UUID credential_id);

        Long count();
}