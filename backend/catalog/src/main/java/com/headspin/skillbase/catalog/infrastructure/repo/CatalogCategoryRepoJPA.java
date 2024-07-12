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
        return category.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        em.remove(em.find(CatalogCategory.class, id));
    }

    @Override
    @Transactional
    public CatalogCategory update(@NotNull @Valid CatalogCategory category) {
        return em.merge(category);
    }

    @Override
    public Optional<CatalogCategory> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(CatalogCategory.class, id));
    }

    @Override
    public List<CatalogCategory> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCategory c ORDER BY :sort", CatalogCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCategory> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCategory c WHERE c.title LIKE ':pattern' ORDER BY :sort", CatalogCategory.class)
                .setParameter("pattern", Objects.requireNonNullElse(sort, "%"))
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findCategorySkills(@NotNull UUID id, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s WHERE s.category_id = :category_id ORDER BY :sort", CatalogSkill.class)
                .setParameter("category_id", id)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogCategory c", Long.class)
                .getSingleResult().longValue();
    }
}
