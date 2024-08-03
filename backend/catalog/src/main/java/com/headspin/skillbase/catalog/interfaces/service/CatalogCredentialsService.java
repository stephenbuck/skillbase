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

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCredential credential) {
        UUID id = repo.insert(credential);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_CREATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(credential.id))
                .add("skill_id", String.valueOf(credential.skill_id))
                .add("is_enabled", credential.is_enabled)
                .add("title", credential.title)
                .add("note", credential.note)
                .add("created_at", String.valueOf(credential.createdAt))
                .add("updated_at", String.valueOf(credential.updatedAt))
                .build());
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_DELETED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build());
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCredential update(@NotNull @Valid CatalogCredential credential) {
        CatalogCredential updated = repo.update(credential);
        evnt.produce(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            CatalogEvent.CATALOG_CREDENTIAL_UPDATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(updated.id))
                .add("skill_id", String.valueOf(updated.skill_id))
                .add("is_enabled", updated.is_enabled)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.createdAt))
                .add("updated_at", String.valueOf(updated.updatedAt))
                .build());
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<CatalogCredential> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<CatalogCredential> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public boolean start(@NotNull UUID id) {
        return true;
    }

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
