package com.headspin.skillbase.catalog.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.faulttolerance.Retry;

import com.headspin.skillbase.catalog.domain.CatalogCredential;
import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestScoped
public class CatalogSkillRepoJPA implements CatalogSkillRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    public CatalogSkillRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid final CatalogSkill skill) {
        em.persist(skill);
        return skill.skill_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID skill_id) {
        em.remove(em.find(CatalogSkill.class, skill_id));
    }

    @Override
    @Transactional
    @Retry(retryOn = {OptimisticLockException.class}, maxRetries = 10, delay = 100)
    public CatalogSkill update(@NotNull @Valid final CatalogSkill skill) {
        return em.merge(skill);
    }

    @Override
    public Optional<CatalogSkill> findById(@NotNull final UUID skill_id) {
        return Optional.ofNullable(em.find(CatalogSkill.class, skill_id));
    }

    @Override
    public List<CatalogSkill> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s ORDER BY :sort", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "skill_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findAllByTitleLike(@NotNull final String pattern, final String sort, final Integer offset,
            final Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s WHERE s.title LIKE ':pattern' ORDER BY :sort", CatalogSkill.class)
                .setParameter("pattern", Objects.requireNonNullElse(sort, "%"))
                .setParameter("sort", Objects.requireNonNullElse(sort, "skill_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogCredential> findSkillCredentials(@NotNull final UUID skill_id, final String sort, final Integer offset,
            final Integer limit) {
        return em.createQuery("SELECT c FROM CatalogCredential c WHERE c.skill_id = :skill_id ORDER BY :sort", CatalogCredential.class)
                .setParameter("skill_id", skill_id)
                .setParameter("sort", Objects.requireNonNullElse(sort, "skill_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    @Transactional
    public void insertSkillCredential(@NotNull final UUID skill_id, @NotNull final UUID credential_id) {
    }

    @Override
    @Transactional
    public void deleteSkillCredential(@NotNull final UUID skill_id, @NotNull final UUID credential_id) {
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogSkill s", Long.class)
                .getSingleResult().longValue();
    }
}
