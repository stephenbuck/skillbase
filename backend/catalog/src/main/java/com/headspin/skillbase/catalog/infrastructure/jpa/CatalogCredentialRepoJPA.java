package com.headspin.skillbase.catalog.infrastructure.jpa;

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

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogCredentialRepo;

@RequestScoped
public class CatalogCredentialRepoJPA implements CatalogCredentialRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    public CatalogCredentialRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CatalogCredential credential) {
        em.persist(credential);
        return credential.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        em.remove(em.find(CatalogCredential.class, id));
    }

    @Override
    @Transactional
    public CatalogCredential update(@NotNull @Valid CatalogCredential credential) {
        return em.merge(credential);
    }

    @Override
    public Optional<CatalogCredential> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(CatalogCredential.class, id));
    }

    @Override
    public List<CatalogCredential> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCredential c ORDER BY :sort", CatalogCredential.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCredential> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT s FROM CatalogCredential s WHERE s.title LIKE ':pattern' ORDER BY :sort LIMIT :limit OFFSET :offset", CatalogCredential.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogCredential s", Long.class)
                .getSingleResult().longValue();
    }
}