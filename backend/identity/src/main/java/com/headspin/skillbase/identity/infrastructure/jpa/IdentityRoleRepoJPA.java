package com.headspin.skillbase.identity.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.headspin.skillbase.identity.domain.IdentityRole;
import com.headspin.skillbase.identity.domain.IdentityRoleRepo;

/*
 * IdentityRoleRepoJPA implements the IdentityRoleRepo
 * interface using JPA and PostgreSQL.
 */

@Slf4j
@RequestScoped
public class IdentityRoleRepoJPA implements IdentityRoleRepo {

    @PersistenceContext(name = "skillbase_identity")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull IdentityRole role) {
        log.info("insert()");
        em.persist(role);
        return role.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public IdentityRole update(@NotNull IdentityRole role) {
        log.info("update({})", role.id);
        return em.merge(role);
    }

    @Override
    public Optional<IdentityRole> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(IdentityRole.class, id));
    }

    @Override
    public List<IdentityRole> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT r FROM IdentityRole r ORDER BY :sort", IdentityRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<IdentityRole> findAllByGroupId(@NotNull UUID groupId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByGroupId({})", groupId);
        return em
                .createQuery("SELECT r FROM IdentityRole r WHERE r.group_id = :groupId ORDER BY :sort",
                        IdentityRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<IdentityRole> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId({})", userId);
        return em
                .createQuery("SELECT r FROM IdentityRole r WHERE r.user_id = :userId ORDER BY :sort",
                        IdentityRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        log.info("count()");
        return em.createQuery("SELECT COUNT(*) FROM IdentityRole r", Long.class)
                .getSingleResult().longValue();
    }
}
