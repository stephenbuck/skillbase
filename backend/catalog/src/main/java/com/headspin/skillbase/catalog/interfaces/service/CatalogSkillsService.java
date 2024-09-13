package com.headspin.skillbase.catalog.interfaces.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;
import com.headspin.skillbase.common.providers.CommonCacheProvider;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonSearchProvider;
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
 * Catalog skills service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogSkillsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private CatalogSkillRepo repo;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonSearchProvider srch;

    @Inject
    private CommonStorageProvider stor;

    @Inject
    private CommonCacheProvider cache;

    private void cacheSet(@NotNull final CatalogSkill skill) {
        try {
            final String key = String.valueOf(skill.skill_id);
            final String val = CatalogSkill.toJson(skill);
            cache.set(key, val);
        }
        catch (Exception e) {
            log.error("Cache set failed", e);
        }
    }

    private CatalogSkill cacheGet(@NotNull final UUID skill_id) {
        try {
            final String key = String.valueOf(skill_id);
            final String val = cache.get(key);
            return CatalogSkill.fromJson(val);
        }
        catch (Exception e) {
            log.error("Cache get failed", e);
            return null;
        }
    }

    private void cacheDelete(@NotNull final UUID skill_id) {
        try {
            final String key = String.valueOf(skill_id);
            cache.delete(key);
        }
        catch (Exception e) {
            log.error("Cache delete failed", e);
        }
    }

    /**
     * Inserts a new catalog skill.
     *
     * @param skill The new skill.
     * @return The id of the new skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final CatalogSkill skill) throws Exception {

        // Insert the object
        final UUID skill_id = repo.insert(skill);

        // Produce the created event
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_CREATED,
            CatalogSkill.toJson(skill));

        // Update the cache
        cacheSet(skill);

        // Return the object id
        return skill_id;
    }

    /**
     * Deletes a catalog skill given an id.
     *
     * @param skill_id The requested skill id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID skill_id) throws Exception {

        // Delete the object
        repo.delete(skill_id);

        // Produce the deleted event
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_DELETED,
            "{}");

        // Update the cache
        cacheDelete(skill_id);
    }

    /**
     * Updates an existing catalog skill.
     *
     * @param skill The updated skill.
     * @return The updated skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogSkill update(@NotNull @Valid final CatalogSkill skill) throws Exception {

        // Update the object
        final CatalogSkill updated = repo.update(skill);

        // Produce the updated event
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_SKILL_UPDATED,
            CatalogSkill.toJson(updated));

        // Update the cache
        cacheSet(updated);

        // Return the updated object
        return updated;
    }

    /**
     * Returns a catalog skill given an id.
     *
     * @param skill_id The requested skill id.
     * @return An optional catalog skill.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public Optional<CatalogSkill> findById(@NotNull final UUID skill_id) {

        // Try to return the cached version
        final CatalogSkill cached = cacheGet(skill_id);
        if (cached != null) {
            return Optional.of(cached);
        }

        // If object found, update the cache
        final Optional<CatalogSkill> result = repo.findById(skill_id);
        if (result.isPresent()) {
            cacheSet(result.get());
        }

        // Return the result
        return result;
    }

    /**
     * Returns a list of all catalog skills.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a list of all catalog skills with matching title.
     *
     * @param pattern The requested title pattern.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog skills.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAllByTitleLike(@NotNull final String pattern, final String sort, final Integer offset,
            final Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

    /**
     * Returns a list of all catalog credentials given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
//    @RolesAllowed({ "Member" })
    public List<CatalogCredential> findSkillCredentials(@NotNull final UUID skill_id, final String sort, final Integer offset,
    final Integer limit) {
        return repo.findSkillCredentials(skill_id, sort, offset, limit);
    }

    /**
     * Inserts a new credential given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param credential_id The requested credential id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void insertSkillCredential(@NotNull final UUID skill_id, @NotNull final UUID credential_id) {
        repo.insertSkillCredential(skill_id, credential_id);
    }

    /**
     * Deletes an existing credential given a skill id.
     *
     * @param skill_id The requested skill id.
     * @param credential_id The requested credential id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void deleteSkillCredential(@NotNull final UUID skill_id, @NotNull final UUID credential_id) {
        repo.deleteSkillCredential(skill_id, credential_id);
    }

    /**
     * Search for skills matching a given keyword.
     *
     * @param keyword The keyword to search for.
     * @return The list of matching skills.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<String> search(@NotNull final String keyword, final String sort, final Integer offset,
    final Integer limit) {
        log.info("search()");
        return srch.search(keyword, sort, offset, limit);
    }

    /**
     * Uploads a catalog skill image.
     *
     * @param skill_id The requested skill id.
     * @param input The image input stream.
     * @param size The size of the image (or -1 if unknown).
     * @param type The media type of the image (e.g. image/jpeg).
     * @return The id of the new image.
     * @since 1.0
     */
    @Retry
    @Timeout
    @Transactional
    //    @RolesAllowed({ "Admin" })
    public String uploadImage(@NotNull final UUID skill_id, @NotNull final InputStream input, @NotNull final Long size, @NotNull final MediaType type) throws Exception {

        // Fetch the skill
        final CatalogSkill skill = findById(skill_id).get();

        // Save the old image
        final String old_image_id = skill.image_id;

        // Upload the new image
        final String new_image_id = stor.uploadObject(input, size, type);
        
        // Update the skill with the new image
        try {
            skill.image_id = new_image_id;
            update(skill);
        }

        // On exception, delete the new image and rethrow
        catch (Exception e) {
            stor.deleteObject(new_image_id);
            throw e;
        }

        // Delete the old image (it's an update)
        try {
            if (old_image_id != null) {
                stor.deleteObject(old_image_id);
            }
        }

        // On exception, just log, but don't rethrow
        catch (Exception e) {
            log.error("Update to delete old image", e);
        }

        // Return the new image id
        return new_image_id;
    }

    /**
     * Downloads a catalog skill image.
     *
     * @param skill_id The requested skill id.
     * @return The storage object of the image.
     * @since 1.0
     */
    @Retry
    @Timeout
    //    @RolesAllowed({ "Admin" })
    public CommonStorageProvider.CommonStorageObject downloadImage(@NotNull final UUID skill_id) throws Exception {
        return stor.downloadObject(findById(skill_id).get().image_id);
    }

    /**
     * Deletes a catalog skill image.
     *
     * @param skill_id The requested skill id.
     * @since 1.0
     */
    @Retry
    @Timeout
    @Transactional
    //    @RolesAllowed({ "Admin" })
    public void deleteImage(@NotNull final UUID skill_id) throws Exception {

        // Fetch the skill
        final CatalogSkill skill = findById(skill_id).get();

        // Save the old image id
        final String old_image_id = skill.image_id;

        // Update the skill image_id to null
        skill.image_id = null;
        update(skill);

        // Delete the old image
        try {
            if (old_image_id != null) {
                stor.deleteObject(old_image_id);
            }
        }

        // On exception, just log, but don't rethrow
        catch (Exception e) {
            log.error("Update to delete old image", e);
        }
    }

    /**
     * Returns a count of catalog skills.
     *
     * @return The count.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        srch.test();
        stor.test();
        cache.test();
        return 0;
    }
}
