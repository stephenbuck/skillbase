package com.headspin.skillbase.catalog.infrastructure;

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

    @Transactional
    public void insert(
            @NotNull @Valid CatalogCategory category) {
        log.info("insert(" + category + ")");
        em.persist(category);
    }

    @Transactional
    public CatalogCategory update(
            @NotNull @Valid CatalogCategory category) {
        log.info("update(" + category + ")");
        return em.merge(category);
    }

    @Transactional
    public void delete(
            @NotNull @Valid CatalogCategory category) {
        log.info("delete(" + category + ")");
        em.remove(category);
    }

    @Transactional
    public void deleteById(
            @NotNull UUID id) {
        log.info("deleteById(" + id + ")");
        em.remove(em.find(CatalogCategory.class, id));
    }

    public Optional<CatalogCategory> findById(
            @NotNull UUID id) {
        log.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(CatalogCategory.class, id));
    }

    public List<CatalogCategory> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT c FROM catalog_category c ORDER BY :sort", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    public List<CatalogCategory> findAllByParentId(
            @NotNull UUID parentId,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByParentId(" + parentId + ")");
        return em.createQuery("SELECT c FROM catalog_category c WHERE c.parent_id = :parentId ORDER BY :sort", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    public List<CatalogCategory> findAllByTitleLike(
            @NotNull String pattern,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByTitleLike(" + pattern + ")");
        return em.createQuery("SELECT c FROM catalog_category c WHERE c.title LIKE ':pattern'", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }
}
