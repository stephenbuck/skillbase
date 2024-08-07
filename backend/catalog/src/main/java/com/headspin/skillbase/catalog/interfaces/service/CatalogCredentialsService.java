package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogCredentialRepo;
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
    private CatalogConfigProvider conf;

    @Inject
    private CatalogFeaturesProvider feat;

    @Inject
    private CatalogEventsProvider evnt;

    /**
     * Inserts a new catalog credential.
     *
     * @param credential The new credential.
     * @return The id of the new credential.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCredential credential) {
        UUID credential_id = repo.insert(credential);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_CREATED,
            Json.createObjectBuilder()
                .add("credential_id", String.valueOf(credential.credential_id))
                .add("skill_id", String.valueOf(credential.skill_id))
                .add("is_enabled", credential.is_enabled)
                .add("title", credential.title)
                .add("note", credential.note)
                .add("bpmn", String.valueOf(credential.bpmn))
                .add("created_at", String.valueOf(credential.created_at))
                .add("updated_at", String.valueOf(credential.updated_at))
                .build());
        return credential_id;
    }

    /**
     * Deletes a catalog credential given an id.
     *
     * @param credential_id The requested credential id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID credential_id) {
        repo.delete(credential_id);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_DELETED,
            Json.createObjectBuilder()
                .add("credential_id", String.valueOf(credential_id))
                .build());
    }

    /**
     * Updates an existing catalog credential.
     *
     * @param credential The updated credential.
     * @return The updated credential.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCredential update(@NotNull @Valid CatalogCredential credential) {
        CatalogCredential updated = repo.update(credential);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_UPDATED,
            Json.createObjectBuilder()
                .add("credential_id", String.valueOf(updated.credential_id))
                .add("skill_id", String.valueOf(updated.skill_id))
                .add("is_enabled", updated.is_enabled)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("bpmn", String.valueOf(updated.bpmn))
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a catalog credential given an id.
     *
     * @param id The requested credential id.
     * @return An optional credential definition.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<CatalogCredential> findById(@NotNull UUID credential_id) {
        return repo.findById(credential_id);
    }

    /**
     * Returns a list of all catalog credentials.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a list of all catalog credentials with matching title.
     *
     * @param pattern The title pattern.
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of catalog credentials.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public boolean start(@NotNull UUID credential_id) {
        return true;
    }

    /**
     * Returns a count of catalog credentials.
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
        feat.test();
        evnt.test();
        return 0;
    }
}
