package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogEvent;
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
public class CatalogCategoryService {

    @Inject
    private CatalogCategoryRepo repo;

    @Inject
    CatalogProducerProvider prod;

    @Resource
    private SessionContext ctx;

    @Transactional
    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        UUID id = repo.insert(category);
        prod.produce(CatalogEvent.buildEvent(category.id(), CatalogEvent.CATALOG_EVENT_CATEGORY_UPDATED));
        return id;
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_EVENT_CATEGORY_DELETED));
    }

    @Transactional
    @RolesAllowed({ "Admin" })
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        CatalogCategory updated = repo.update(category);
        prod.produce(CatalogEvent.buildEvent(category.id(), CatalogEvent.CATALOG_EVENT_CATEGORY_UPDATED));
        return updated;
    }

    @RolesAllowed({ "Admin" })
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogCategory> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogCategory> findAllByParentId(@NotNull UUID parentId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByParentId(parentId, sort, offset, limit);
    }

    @RolesAllowed({ "Admin" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }
}
