package com.headspin.skillbase.catalog.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogEvent;
import com.headspin.skillbase.catalog.infrastructure.etcd.CatalogConfigProviderEtcd;
import com.headspin.skillbase.catalog.infrastructure.flipt.CatalogFeatureProviderFlipt;
import com.headspin.skillbase.catalog.infrastructure.kafka.CatalogProducerProviderKafka;
import com.headspin.skillbase.catalog.providers.CatalogConfigProvider;
import com.headspin.skillbase.catalog.providers.CatalogFeatureProvider;
import com.headspin.skillbase.catalog.providers.CatalogProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogCategoryService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private CatalogCategoryRepo repo;

    CatalogProducerProvider prod = new CatalogProducerProviderKafka();

    CatalogFeatureProvider feat = new CatalogFeatureProviderFlipt();

    CatalogConfigProvider conf = new CatalogConfigProviderEtcd();

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        UUID id = repo.insert(category);
        prod.produce(CatalogEvent.buildEvent(category.id, CatalogEvent.CATALOG_CATEGORY_UPDATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_CATEGORY_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        CatalogCategory updated = repo.update(category);
        prod.produce(CatalogEvent.buildEvent(category.id, CatalogEvent.CATALOG_CATEGORY_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Member" })
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    //    @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Member" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        Optional<String> dog = conf.getValue("dog", String.class);
        log.info("conf.dog = {}", dog.orElse("no dog"));
//        String val = feat.getValue("dog");
//        log.info("feat.dog = {}", val);
        prod.produce(CatalogEvent.buildEvent(UUID.randomUUID(), CatalogEvent.CATALOG_CATEGORY_UPDATED));
        return repo.count();
    }
}
