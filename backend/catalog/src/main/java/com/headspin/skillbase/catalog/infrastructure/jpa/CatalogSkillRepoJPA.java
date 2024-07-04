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

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        log.info("insert()");
        em.persist(skill);
        return skill.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        em.remove(em.find(CatalogSkill.class, id));
    }

    @Override
    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        log.info("update({})", skill.id);
        return em.merge(skill);
    }

    @Override
    public Optional<CatalogSkill> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CatalogSkill.class, id));
    }

    @Override
    public List<CatalogSkill> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT s FROM CatalogSkill s ORDER BY :sort", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findAllByCategoryId(@NotNull UUID categoryId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByCategoryId({})", categoryId);
        return em
                .createQuery("SELECT s FROM CatalogSkill s WHERE s.categoryId = :categoryId ORDER BY :sort",
                        CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByTitleLike({})", pattern);
        return em.createQuery("SELECT s FROM CatalogSkill s WHERE s.title LIKE ':pattern'", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        log.info("count()");
        return em.createQuery("SELECT COUNT(*) FROM CatalogSkill s", Long.class)
                .getSingleResult().longValue();
    }
}
