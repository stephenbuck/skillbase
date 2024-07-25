package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.infrastructure.config.CatalogConfigProviderDefault;
import com.headspin.skillbase.catalog.infrastructure.feature.CatalogFeatureProviderFlipt;
import com.headspin.skillbase.catalog.infrastructure.messaging.CatalogEventProducerKafka;
import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;
import com.headspin.skillbase.catalog.providers.CatalogFeatureProvider;
import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogCategoryService {

    @Resource
    private SessionContext ctx;

    @Inject
    private CatalogCategoryRepo repo;

    CatalogConfigProvider conf = new CatalogConfigProviderDefault();
    CatalogFeatureProvider feat = new CatalogFeatureProviderFlipt();
    CatalogProducerProvider prod = new CatalogEventProducerKafka();

    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        UUID id = repo.insert(category);
        prod.produce(CatalogEvent.buildEvent(category.id, CatalogEvent.CATALOG_CATEGORY_UPDATED,
                new CatalogEvent.CategoryCreated(category.id, category.title)));
        return id;
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_CATEGORY_DELETED,
                new CatalogEvent.CategoryDeleted(id)));
        return result;
    }

    // @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        CatalogCategory updated = repo.update(category);
        prod.produce(CatalogEvent.buildEvent(category.id, CatalogEvent.CATALOG_CATEGORY_UPDATED,
                new CatalogEvent.CategoryUpdated(category.id, category.title)));
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
        conf.test();
        feat.test();
        prod.test();
        return 0;
    }
}
