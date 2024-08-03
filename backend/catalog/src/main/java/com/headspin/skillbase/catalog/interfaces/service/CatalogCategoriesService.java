package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;
import com.headspin.skillbase.catalog.providers.CatalogFeaturesProvider;
import com.headspin.skillbase.catalog.providers.CatalogEventsProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Catalog categories service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogCategoriesService {

    @Resource
    private SessionContext ctx;

    @Inject
    private CatalogCategoryRepo repo;

    @Inject
    private CatalogConfigProvider conf;

    @Inject
    private CatalogFeaturesProvider feat;

    @Inject
    private CatalogEventsProvider evnt;

    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        UUID id = repo.insert(category);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_CREATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(category.id))
                .add("title", category.title)
                .build());
        return id;
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_DELETED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build());
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        CatalogCategory updated = repo.update(category);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_UPDATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(updated.id))
                .add("title", updated.title)
                .build());
        return updated;
    }

    // @RolesAllowed({ "Member" })
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findCategoryCategories(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return repo.findCategoryCategories(id, sort, offset, limit);
    }

    // @RolesAllowed({ "Member" })
    public List<CatalogSkill> findCategorySkills(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return repo.findCategorySkills(id, sort, offset, limit);
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public boolean insertCategoryCategory(@NotNull UUID id, @NotNull UUID category_id) {
        return repo.insertCategoryCategory(id, category_id);
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public boolean deleteCategoryCategory(@NotNull UUID id, @NotNull UUID category_id) {
        return repo.deleteCategoryCategory(id, category_id);
    }

    // @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

    // @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        return 0;
    }
}
