package com.headspin.skillbase.storage.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.storage.domain.StorageCategory;
import com.headspin.skillbase.storage.domain.StorageCategoryRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestScoped
public class StorageCategoryRepoJPA implements StorageCategoryRepo {

    @PersistenceContext(name = "skillbase_storage")
    private EntityManager em;

    public StorageCategoryRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid final StorageCategory category) {
        em.persist(category);
        return category.category_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID category_id) {
        em.remove(em.find(StorageCategory.class, category_id));
    }

    @Override
    @Transactional
    public StorageCategory update(@NotNull @Valid final StorageCategory category) {
        return em.merge(category);
    }

    @Override
    public Optional<StorageCategory> findById(@NotNull final UUID category_id) {
        return Optional.ofNullable(em.find(StorageCategory.class, category_id));
    }

    @Override
    public List<StorageCategory> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT c FROM StorageCategory c ORDER BY :sort", StorageCategory.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<StorageCategory> findAllByTitleLike(@NotNull final String pattern, final String sort, final Integer offset,
            final Integer limit) {
        return em.createQuery("SELECT c FROM StorageCategory c WHERE c.title LIKE ':pattern' ORDER BY :sort", StorageCategory.class)
                .setParameter("pattern", Objects.requireNonNullElse(sort, "%"))
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<StorageCategory> findCategoryCategories(@NotNull final UUID category_id, final String sort, final Integer offset,
            final Integer limit) {
        return em.createQuery("SELECT c FROM StorageCategory c WHERE c.parent_id = :category_id ORDER BY :sort", StorageCategory.class)
                .setParameter("category_id", category_id)
                .setParameter("sort", Objects.requireNonNullElse(sort, "category_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    @Transactional
    public void insertCategoryCategory(@NotNull final UUID category_id, @NotNull final UUID subcategory_id) {
    }

    @Override
    @Transactional
    public void deleteCategoryCategory(@NotNull final UUID category_id, @NotNull final UUID subcategory_id) {
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM StorageCategory c", Long.class)
                .getSingleResult().longValue();
    }
}
