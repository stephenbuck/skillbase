package com.headspin.skillbase.catalog.interfaces.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogCredentialRepo;
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
 * Catalog credentials service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogCredentialsService {

    @Resource
    private SessionContext ctx;

    @Inject
    private CatalogCredentialRepo repo;

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
     * Put a catalog credential in the cache.
     *
     * @param credential The credential.
     * @since 1.0
     */
    private void cachePut(@NotNull final CatalogCredential credential) {
        try {
            final String key = String.valueOf(credential.credential_id);
            final String val = CatalogCredential.toJson(credential);
            cache.set(key, val);
        } catch (Exception e) {
            log.error("Cache put failed", e);
        }
    }

    /**
     * Get a catalog credential from the cache.
     *
     * @param credential_id The credential id.
     * @since 1.0
     */
    private CatalogCredential cacheGet(@NotNull final UUID credential_id) {
        try {
            final String key = String.valueOf(credential_id);
            final String val = cache.get(key);
            return CatalogCredential.fromJson(val);
        } catch (Exception e) {
            log.error("Cache get failed", e);
            return null;
        }
    }

    /**
     * Delete a catalog credential from the cache.
     *
     * @param credential_id The credential id.
     * @since 1.0
     */
    private void cacheDelete(@NotNull final UUID credential_id) {
        try {
            final String key = String.valueOf(credential_id);
            cache.delete(key);
        } catch (Exception e) {
            log.error("Cache delete failed", e);
        }
    }

    /**
     * Insert a catalog credential.
     *
     * @param credential The new credential.
     * @return The id of the new credential.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final CatalogCredential credential) throws Exception {

        // Insert the object
        final UUID credential_id = repo.insert(credential);

        // Produce the created event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CREDENTIAL_CREATED,
                CatalogCredential.toJson(credential));

        // Update the cache
        cachePut(credential);

        // Return the object id
        return credential_id;
    }

    /**
     * Delete a catalog credential.
     *
     * @param credential_id The requested credential id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID credential_id) throws Exception {

        // Delete the object
        repo.delete(credential_id);

        // Produce the deleted event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CREDENTIAL_DELETED,
                "{}");

        // Update the cache
        cacheDelete(credential_id);
    }

    /**
     * Update a catalog credential.
     *
     * @param credential The updated credential.
     * @return The updated credential.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCredential update(@NotNull @Valid final CatalogCredential credential) throws Exception {

        // Update the object
        final CatalogCredential updated = repo.update(credential);

        // Produce the updated event
        evnt.produce(
                CatalogEvent.CATALOG_EVENT_TOPIC,
                CatalogEvent.CATALOG_CREDENTIAL_UPDATED,
                CatalogCredential.toJson(updated));

        // Update the cache
        cachePut(updated);

        // Return the updated object
        return updated;
    }

    /**
     * Find a catalog credential by id.
     *
     * @param id The requested credential id.
     * @return An optional credential definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<CatalogCredential> findById(@NotNull final UUID credential_id) {

        // Try to return the cached version
        final CatalogCredential cached = cacheGet(credential_id);
        if (cached != null) {
            return Optional.of(cached);
        }

        // If object found, update the cache
        final Optional<CatalogCredential> result = repo.findById(credential_id);
        if (result.isPresent()) {
            cachePut(result.get());
        }

        // Return the result
        return result;
    }

    /**
     * Find all catalog credentials.
     *
     * @param sort   Sort field.
     * @param offset Offset of first result.
     * @param limit  Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Find all catalog credentials with matching title.
     *
     * @param pattern The title pattern.
     * @param sort    Sort field.
     * @param offset  Offset of first result.
     * @param limit   Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAllByTitleLike(@NotNull final String pattern, final String sort,
            final Integer offset,
            final Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    // @RolesAllowed({ "Admin" })
    public boolean start(@NotNull final UUID credential_id) {
        return true;
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

    @Retry
    @Timeout
    public String uploadBPMN(@NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type)
            throws Exception {
        return stor.uploadObject(input, size, type);
    }

    @Retry
    @Timeout
    public CommonStorageProvider.CommonStorageObject downloadBPMN(@NotNull final String bpmn_id) throws Exception {
        return stor.downloadObject(bpmn_id);
    }

    @Retry
    @Timeout
    public void deleteBPMN(@NotNull final String bpmn_id) throws Exception {
        stor.deleteObject(bpmn_id);
    }

    /**
     * Return a count of catalog credentials.
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
