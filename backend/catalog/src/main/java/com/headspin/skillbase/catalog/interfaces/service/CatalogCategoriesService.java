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

    /**
     * Inserts a new catalog category.
     *
     * @param category The new category.
     * @return The id of the new category.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        UUID category_id = repo.insert(category);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_CREATED,
            Json.createObjectBuilder()
                .add("category_id", String.valueOf(category.category_id))
                .add("parent_id", String.valueOf(category.parent_id))
                .add("is_enabled", category.is_enabled)
                .add("title", category.title)
                .add("note", category.note)
                .add("created_at", String.valueOf(category.created_at))
                .add("updated_at", String.valueOf(category.updated_at))
                .build());
        return category_id;
    }

    /**
     * Deletes a catalog category given an id.
     *
     * @param category_id The requested category id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID category_id) {
        repo.delete(category_id);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_DELETED,
            Json.createObjectBuilder()
                .add("category_id", String.valueOf(category_id))
                .build());
    }

    /**
     * Updates an existing catalog category.
     *
     * @param category The updated category.
     * @return The updated category.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        CatalogCategory updated = repo.update(category);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CATEGORY_UPDATED,
            Json.createObjectBuilder()
                .add("category_id", String.valueOf(updated.category_id))
                .add("parent_id", String.valueOf(updated.parent_id))
                .add("is_enabled", updated.is_enabled)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a catalog category given an id.
     *
     * @param category_id The requested category id.
     * @return An optional category definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public Optional<CatalogCategory> findById(@NotNull UUID category_id) {
        return repo.findById(category_id);
    }

    /**
     * Returns a list of all catalog categories.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a list of all catalog categories with matching title.
     *
     * @param pattern The title pattern.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    /**
     * Returns a list of all catalog subcategories given a category id.
     *
     * @param category_id The requested category id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findCategoryCategories(@NotNull UUID category_id, String sort, Integer offset, Integer limit) {
        return repo.findCategoryCategories(category_id, sort, offset, limit);
    }

    /**
     * Returns a list of all catalog skills given a category id.
     *
     * @param category_id The requested category id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogSkill> findCategorySkills(@NotNull UUID category_id, String sort, Integer offset, Integer limit) {
        return repo.findCategorySkills(category_id, sort, offset, limit);
    }

    /**
     * Inserts a new category subcategory.
     *
     * @param category_id The requested category id.
     * @param subcategory_id The requested subcategory_id.
     * @return True if successful.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void insertCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id) {
        repo.insertCategoryCategory(category_id, subcategory_id);
    }

    /**
     * Deletes an existing category subcategory.
     *
     * @param category_id The requested category id.
     * @param subcategory_id The requested subcategory_id.
     * @return True if successful.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void deleteCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id) {
        repo.deleteCategoryCategory(category_id, subcategory_id);
    }

    /**
     * Returns a count of catalog categories.
     *
     * @return The count.
     * @since 1.0
     */
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
