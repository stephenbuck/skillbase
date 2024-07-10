package com.headspin.skillbase.catalog.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import lombok.extern.slf4j.Slf4j;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogEvent;
import com.headspin.skillbase.catalog.infrastructure.kafka.CatalogProducerProviderKafka;
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

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "User" })
public class CatalogCategoryService {

    /*
    @Resource
    private SessionContext ctx;
    */
    
    @Inject
    private CatalogCategoryRepo repo;

    /*
    @Inject
    CatalogProducerProvider prod; // = null; // new CatalogProducerProviderKafka();
    */

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid CatalogCategory user) {
        UUID id = repo.insert(user);
//        prod.produce(CatalogEvent.buildEvent(user.id, CatalogEvent.CATALOG_CATEGORY_UPDATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
//        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_CATEGORY_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public CatalogCategory update(@NotNull @Valid CatalogCategory user) {
        CatalogCategory updated = repo.update(user);
//        prod.produce(CatalogEvent.buildEvent(user.id, CatalogEvent.CATALOG_CATEGORY_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Admin" })
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    //    @RolesAllowed({ "Admin" })
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }
}
