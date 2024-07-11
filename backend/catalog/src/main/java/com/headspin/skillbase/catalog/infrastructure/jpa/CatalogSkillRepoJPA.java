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

import com.headspin.skillbase.catalog.domain.CatalogSkill;
import com.headspin.skillbase.catalog.domain.CatalogSkillRepo;

@RequestScoped
public class CatalogSkillRepoJPA implements CatalogSkillRepo {

    @PersistenceContext(name = "skillbase_catalog")
    private EntityManager em;

    public CatalogSkillRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid CatalogSkill skill) {
        em.persist(skill);
        return skill.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        em.remove(em.find(CatalogSkill.class, id));
    }

    @Override
    @Transactional
    public CatalogSkill update(@NotNull @Valid CatalogSkill skill) {
        return em.merge(skill);
    }

    @Override
    public Optional<CatalogSkill> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(CatalogSkill.class, id));
    }

    @Override
    public List<CatalogSkill> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s ORDER BY :sort", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<CatalogSkill> findAllByTitleLike(@NotNull String pattern, String sort, Integer offset,
            Integer limit) {
        return em.createQuery("SELECT s FROM CatalogSkill s WHERE s.title LIKE ':pattern' ORDER BY :sort LIMIT :limit OFFSET :offset", CatalogSkill.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM CatalogSkill s", Long.class)
                .getSingleResult().longValue();
    }
}
