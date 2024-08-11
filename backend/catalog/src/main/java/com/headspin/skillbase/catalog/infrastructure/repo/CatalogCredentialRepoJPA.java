package com.headspin.skillbase.catalog.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogCredentialRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestScoped
public class CatalogCredentialRepoJPA implements CatalogCredentialRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    public CatalogCredentialRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid final CatalogCredential credential) {
        em.persist(credential);
        return credential.credential_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID credential_id) {
        em.remove(em.find(CatalogCredential.class, credential_id));
    }

    @Override
    @Transactional
    public CatalogCredential update(@NotNull @Valid final CatalogCredential credential) {
        return em.merge(credential);
    }

    @Override
    public Optional<CatalogCredential> findById(@NotNull final UUID credential_id) {
        return Optional.ofNullable(em.find(CatalogCredential.class, credential_id));
    }

    @Override
    public List<CatalogCredential> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCredential c ORDER BY :sort", CatalogCredential.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "credential_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCredential> findAllByTitleLike(@NotNull final String pattern, final String sort, final Integer offset,
            final Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCredential c WHERE c.title LIKE ':pattern' ORDER BY :sort", CatalogCredential.class)
                .setParameter("pattern", Objects.requireNonNullElse(sort, "%"))
                .setParameter("sort", Objects.requireNonNullElse(sort, "credential_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogCredential c", Long.class)
                .getSingleResult().longValue();
    }
}
