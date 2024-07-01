package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.catalog.domain.CatalogEvent;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;
import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

// @Slf4j
@Stateless
@DeclareRoles({ "Admin", "User" })
public class CatalogSkillService {

    @Inject
    private CatalogSkillRepo repo;

    @Inject
    private CatalogProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        UUID id = repo.insert(skill);
        prod.produce(CatalogEvent.buildEvent(skill.id(), CatalogEvent.CATALOG_EVENT_SKILL_INSERTED));
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_EVENT_SKILL_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        CatalogSkill updated = repo.update(skill);
        prod.produce(CatalogEvent.buildEvent(skill.id(), CatalogEvent.CATALOG_EVENT_SKILL_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Optional<CatalogSkill> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogSkill> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogSkill> findAllByCategoryId(@NotNull UUID categoryId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByCategoryId(categoryId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
