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

import com.headspin.skillbase.identity.domain.IdentityGroup;
import com.headspin.skillbase.identity.domain.IdentityGroupRepo;

@Slf4j
@RequestScoped
public class IdentityGroupRepoJPA implements IdentityGroupRepo {

    @PersistenceContext(name = "skillbase_identity")
    private EntityManager em;

    @Override
    @Transactional
    public UUID insert(@NotNull IdentityGroup group) {
        log.info("insert()");
        em.persist(group);
        return group.id();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("delete({})", id);
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public IdentityGroup update(@NotNull IdentityGroup group) {
        log.info("update({})", group.id());
        return em.merge(group);
    }

    @Override
    public Optional<IdentityGroup> findById(@NotNull UUID id) {
        log.info("findById({})", id);
        return Optional.ofNullable(em.find(IdentityGroup.class, id));
    }

    @Override
    public List<IdentityGroup> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT g FROM identity_group g ORDER BY :sort", IdentityGroup.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<IdentityGroup> findAllByRoleId(@NotNull UUID roleId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByRoleId({})", roleId);
        return em
                .createQuery("SELECT g FROM identity_group g WHERE g.role_id = :userId ORDER BY :sort",
                        IdentityGroup.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<IdentityGroup> findAllByUserId(@NotNull UUID userId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByUserId({})", userId);
        return em
                .createQuery("SELECT g FROM identity_group g WHERE g.user_id = :userId ORDER BY :sort",
                        IdentityGroup.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
