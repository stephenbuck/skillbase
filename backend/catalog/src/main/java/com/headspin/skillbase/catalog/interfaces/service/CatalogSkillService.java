package com.headspin.skillbase.catalog.interfaces.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogEvent;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;
import com.headspin.skillbase.catalog.infrastructure.config.CatalogConfigProviderDefault;
import com.headspin.skillbase.catalog.infrastructure.feature.CatalogFeatureProviderFlipt;
import com.headspin.skillbase.catalog.infrastructure.producer.CatalogProducerProviderKafka;
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

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class CatalogSkillService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private CatalogSkillRepo repo;

    private CatalogConfigProvider conf = new CatalogConfigProviderDefault();
    private CatalogFeatureProvider feat = new CatalogFeatureProviderFlipt();
    private CatalogProducerProvider prod = new CatalogProducerProviderKafka();

    @Transactional
//    @RolesAllowed({ "Admin" })
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        UUID id = repo.insert(skill);
        prod.produce(CatalogEvent.buildEvent(skill.id, CatalogEvent.CATALOG_SKILL_CREATED));
        return id;
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        prod.produce(CatalogEvent.buildEvent(id, CatalogEvent.CATALOG_SKILL_DELETED));
    }

    @Transactional
//    @RolesAllowed({ "Admin" })
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        CatalogSkill updated = repo.update(skill);
        prod.produce(CatalogEvent.buildEvent(skill.id, CatalogEvent.CATALOG_SKILL_UPDATED));
        return updated;
    }

//    @RolesAllowed({ "Member" })
    public Optional<CatalogSkill> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

//    @RolesAllowed({ "Member" })
    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return repo.findAllByTitleLike(pattern, sort, offset, limit);
    }

//    @RolesAllowed({ "Member" })
    public List<CatalogCredential> findSkillCredentials(@NotNull UUID id, String sort, Integer offset,
    Integer limit) {
        return repo.findSkillCredentials(id, sort, offset, limit);
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
        prod.produce(CatalogEvent.buildEvent(UUID.randomUUID(), CatalogEvent.CATALOG_SKILL_UPDATED));
        return 0;
    }
}
