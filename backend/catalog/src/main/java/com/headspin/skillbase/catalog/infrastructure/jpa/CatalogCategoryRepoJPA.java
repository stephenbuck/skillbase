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

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;

@Slf4j
@RequestScoped
public class CatalogCategoryRepoJPA implements CatalogCategoryRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        log.info("insert()");
        em.persist(category);
        return category.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        em.remove(em.find(CatalogCategory.class, id));
    }

    @Override
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        log.info("update({})", category);
        return em.merge(category);
    }

    @Override
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(CatalogCategory.class, id));
    }

    @Override
    public List<CatalogCategory> findAll() { // @Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("repo.findAll()");
        return em.createQuery("SELECT c FROM CatalogCategory c", CatalogCategory.class)
 //               .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
//                .setFirstResult(Objects.requireNonNullElse(offset, 0))
 //               .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCategory> findAllByParentId(@NotNull UUID parentId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByParentId({})", parentId);
        return em
                .createQuery("SELECT c FROM CatalogCategory c WHERE c.parentId = :parentId ORDER BY :sort",
                        CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByTitleLike({})", pattern);
        return em.createQuery("SELECT c FROM CatalogCategory c WHERE c.title LIKE ':pattern'", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        log.info("count()");
        return em.createQuery("SELECT COUNT(*) FROM CatalogCategory c", Long.class)
                .getSingleResult().longValue();
    }
}
