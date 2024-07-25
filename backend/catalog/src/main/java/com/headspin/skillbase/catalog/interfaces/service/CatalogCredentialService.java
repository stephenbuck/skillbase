package com.headspin.skillbase.catalog.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.infrastructure.config.CatalogConfigProviderDefault;
import com.headspin.skillbase.catalog.infrastructure.feature.CatalogFeatureProviderFlipt;
import com.headspin.skillbase.catalog.infrastructure.messaging.CatalogEventProducerKafka;
import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogCredentialRepo;
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
public class CatalogCredentialService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private CatalogCredentialRepo repo;

    private CatalogConfigProvider conf = new CatalogConfigProviderDefault();
    private CatalogFeatureProvider feat = new CatalogFeatureProviderFlipt();
    private CatalogProducerProvider prod = new CatalogEventProducerKafka();

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCredential credential) {
        UUID id = repo.insert(credential);
        prod.produce(CatalogEvent.buildEvent(credential.id, CatalogEvent.CATALOG_CREDENTIAL_CREATED, new CatalogEvent.CredentialCreated(credential.id, credential.skill_id, credential.title)));
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public boolean delete(@NotNull UUID id) {
        boolean result = repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_CREDENTIAL_DELETED, new CatalogEvent.CredentialDeleted(id)));
        return result;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public CatalogCredential update(@NotNull @Valid CatalogCredential credential) {
        CatalogCredential updated = repo.update(credential);
        prod.produce(CatalogEvent.buildEvent(credential.id, CatalogEvent.CATALOG_CREDENTIAL_UPDATED, new CatalogEvent.CredentialUpdated(credential.id, credential.title)));
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
        conf.test();
        feat.test();
        prod.test();
        return 0;
    }
}
