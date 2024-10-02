package com.headspin.skillbase.catalog.interfaces.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.common.providers.CommonCacheProvider;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonStorageProvider;
import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
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
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonStorageProvider stor;

    @Inject
    private CommonCacheProvider cache;

    /**
     * Put a catalog category in the cache.
     *
     * @param category The category.
     * @since 1.0
     */
    private void cachePut(@NotNull final CatalogCategory category) {
        try {
            final String key = String.valueOf(category.category_id);
            final String val = CatalogCategory.toJson(category);
            cache.set(key, val);
        } catch (Exception e) {
            log.error("Cache put failed", e);
        }
    }

    /**
     * Get a catalog category from the cache.
     *
     * @param category_id The category id.
     * @since 1.0
     */
    private CatalogCategory cacheGet(@NotNull final UUID category_id) {
        try {
            final String key = String.valueOf(category_id);
            final String val = cache.get(key);
            return CatalogCategory.fromJson(val);
        } catch (Exception e) {
            log.error("Cache get failed", e);
            return null;
        }
    }

    /**
     * Delete a catalog category from the cache.
     *
     * @param category_id The category id.
     * @since 1.0
     */
    private void cacheDelete(@NotNull final UUID category_id) {
        try {
            final String key = String.valueOf(category_id);
            cache.delete(key);
        } catch (Exception e) {
            log.error("Cache delete failed", e);
        }
    }

    /**
     * Insert a catalog category.
     *
     * @param category The new category.
     * @return The id of the new category.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull /* BOZO @Valid */ final CatalogCategory category) throws Exception {

        // Insert the object
        final UUID category_id = repo.insert(category);

        // Produce the created event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CATEGORY_CREATED,
                CatalogCategory.toJson(category));

        // Update the cache
        cachePut(category);

        // Return the object id
        return category_id;
    }

    /**
     * Delete a catalog category.
     *
     * @param category_id The requested category id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID category_id) {

        // Delete the object
        repo.delete(category_id);

        // Produce the deleted event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CATEGORY_DELETED,
                "{}");

        // Update the cache
        cacheDelete(category_id);
    }

    /**
     * Update a catalog category.
     *
     * @param category The updated category.
     * @return The updated category.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCategory update(@NotNull @Valid final CatalogCategory category) throws Exception {

        // Update the object
        final CatalogCategory updated = repo.update(category);

        // Produce the updated event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CATEGORY_UPDATED,
                CatalogCategory.toJson(updated));

        // Update the cache
        cachePut(updated);

        // Return the updated object
        return updated;
    }

    /**
     * Find a catalog category by id.
     *
     * @param category_id The requested category id.
     * @return An optional category definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public Optional<CatalogCategory> findById(@NotNull final UUID category_id) throws Exception {

        // Try to return the cached version
        final CatalogCategory cached = cacheGet(category_id);
        if (cached != null) {
            return Optional.of(cached);
        }

        // If object found, update the cache
        final Optional<CatalogCategory> result = repo.findById(category_id);
        if (result.isPresent()) {
            cachePut(result.get());
        }

        // Return the result
        return result;
    }

    /**
     * Find all catalog categories.
     *
     * @param sort   Sort field.
     * @param offset Offset of first result.
     * @param limit  Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Find all catalog categories with matching title.
     *
     * @param pattern The title pattern.
     * @param sort    Sort field.
     * @param offset  Offset of first result.
     * @param limit   Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull final String pattern, final String sort,
            final Integer offset,
            final Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    /**
     * Find all catalog category subcategories.
     *
     * @param category_id The requested category id.
     * @param sort        Sort field.
     * @param offset      Offset of first result.
     * @param limit       Limit of results returned.
     * @return A list of catalog categories.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogCategory> findCategoryCategories(@NotNull final UUID category_id, final String sort,
            final Integer offset, final Integer limit) {
        return repo.findCategoryCategories(category_id, sort, offset, limit);
    }

    /**
     * Find all catalog category skills.
     *
     * @param category_id The requested category id.
     * @param sort        Sort field.
     * @param offset      Offset of first result.
     * @param limit       Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
    // @RolesAllowed({ "Member" })
    public List<CatalogSkill> findCategorySkills(@NotNull final UUID category_id, final String sort,
            final Integer offset, final Integer limit) {
        return repo.findCategorySkills(category_id, sort, offset, limit);
    }

    /**
     * Insert a catalog category subcategory.
     *
     * @param category_id    The requested category id.
     * @param subcategory_id The requested subcategory_id.
     * @return True if successful.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void insertCategoryCategory(@NotNull final UUID category_id, @NotNull final UUID subcategory_id) {
        repo.insertCategoryCategory(category_id, subcategory_id);
    }

    /**
     * Delete a catalog category subcategory.
     *
     * @param category_id    The requested category id.
     * @param subcategory_id The requested subcategory_id.
     * @return True if successful.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void deleteCategoryCategory(@NotNull final UUID category_id, @NotNull final UUID subcategory_id) {
        repo.deleteCategoryCategory(category_id, subcategory_id);
    }

    @Retry
    @Timeout
    public String uploadImage(@NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type)
            throws Exception {
        return stor.uploadObject(input, size, type);
    }

    @Retry
    @Timeout
    public CommonStorageProvider.CommonStorageObject downloadImage(@NotNull final String image_id) throws Exception {
        return stor.downloadObject(image_id);
    }

    @Retry
    @Timeout
    public void deleteImage(@NotNull final String image_id) throws Exception {
        stor.deleteObject(image_id);
    }

    /**
     * Return a count of catalog categories.
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
        evnt.test();
        feat.test();
        stor.test();
        cache.test();
        return 0;
    }
}
