package com.headspin.skillbase.catalog.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.headspin.skillbase.catalog.domain.CatalogCategory;
import com.headspin.skillbase.catalog.domain.CatalogCategoryRepo;
import com.headspin.skillbase.catalog.domain.CatalogSkill;

@RequestScoped
public class CatalogCategoryRepoJPA implements CatalogCategoryRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    public CatalogCategoryRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCategory category) {
        em.persist(category);
        return category.category_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID category_id) {
        em.remove(em.find(CatalogCategory.class, category_id));
    }

    @Override
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        return em.merge(category);
    }

    @Override
    public Optional<CatalogCategory> findById(@NotNull UUID category_id) {
        return Optional.ofNullable(em.find(CatalogCategory.class, category_id));
    }

    @Override
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCategory c ORDER BY :sort", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCategory c WHERE c.title LIKE ':pattern' ORDER BY :sort", CatalogCategory.class)
                .setParameter("pattern", Objects.requireNonNullElse(sort, "%"))
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCategory> findCategoryCategories(@NotNull UUID category_id, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCategory c WHERE c.parent_id = :category_id ORDER BY :sort", CatalogCategory.class)
                .setParameter("category_id", category_id)
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findCategorySkills(@NotNull UUID category_id, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s WHERE s.category_id = :category_id ORDER BY :sort", CatalogSkill.class)
                .setParameter("category_id", category_id)
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    @Transactional
    public void insertCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id) {
    }

    @Override
    @Transactional
    public void deleteCategoryCategory(@NotNull UUID category_id, @NotNull UUID subcategory_id) {
    }

    @Override
    @Transactional
    public void insertCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id) {
    }

    @Override
    @Transactional
    public void deleteCategorySkill(@NotNull UUID category_id, @NotNull UUID skill_id) {
    }
    
    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogCategory c", Long.class)
                .getSingleResult().longValue();
    }
}
