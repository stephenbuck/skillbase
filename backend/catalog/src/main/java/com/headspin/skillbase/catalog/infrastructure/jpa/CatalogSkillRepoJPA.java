package com.headspin.skillbase.catalog.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;

@Slf4j
@RequestScoped
public class CatalogSkillRepoJPA implements CatalogSkillRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    @Transactional
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        log.info("insert()");
        em.persist(skill);
        return skill.id();
    }

    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        log.info("update({})", skill.id());
        return em.merge(skill);
    }

    @Transactional
    public void delete(@NotNull @Valid CatalogSkill skill) {
        log.info("delete({})", skill.id());
        em.remove(skill);
    }

    @Transactional
    public void deleteById(@NotNull UUID id) {
        log.info("deleteById({})", id);
        em.remove(em.find(CatalogSkill.class, id));
    }

    public Optional<CatalogSkill> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CatalogSkill.class, id));
    }

    public List<CatalogSkill> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT s FROM catalog_skill s ORDER BY :sort", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    public List<CatalogSkill> findAllByCategoryId(@NotNull UUID categoryId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByCategoryId({})", categoryId);
        return em
                .createQuery("SELECT s FROM catalog_skill s WHERE s.category_id = :categoryId ORDER BY :sort",
                        CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByTitleLike({})", pattern);
        return em.createQuery("SELECT s FROM catalog_skill s WHERE s.title LIKE ':pattern'", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
