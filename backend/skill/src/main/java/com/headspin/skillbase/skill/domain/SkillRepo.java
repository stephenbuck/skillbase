package com.headspin.skillbase.skill.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface SkillRepo {

    @Transactional
    public void insert(
            @NotNull @Valid Skill skill);

    @Transactional
    public void delete(
            @NotNull @Valid Skill skill);

    @Transactional
    public Skill update(
            @NotNull @Valid Skill skill);

    @Transactional
    public void deleteById(
            @NotNull UUID id);

    public Optional<Skill> findById(
            @NotNull UUID id);

    public List<Skill> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit);

    public List<Skill> findAllByCategoryId(
            @NotNull UUID categoryId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit);

    public List<Skill> findAllByTitleLike(
            @NotNull String pattern,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit);
}