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

import com.headspin.skillbase.identity.domain.IdentityUser;
import com.headspin.skillbase.identity.domain.IdentityUserRepo;

@Slf4j
@RequestScoped
public class IdentityUserRepoJPA implements IdentityUserRepo {

    @PersistenceContext(name = "skillbase_identity")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(@NotNull IdentityUser user) {
        log.info("insert(" + user + ")");
        em.persist(user);
    }

    @Override
    @Transactional
    public IdentityUser update(@NotNull IdentityUser user) {
        log.info("update(" + user + ")");
        return em.merge(user);
    }

    @Override
    @Transactional
    public void delete(@NotNull IdentityUser user) {
        log.info("delete(" + user + ")");
        em.remove(user);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull UUID id) {
        log.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    public Optional<IdentityUser> findById(@NotNull UUID id) {
        log.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(IdentityUser.class, id));
    }

    @Override
    public List<IdentityUser> findAll(@Null String sort, @Null Integer offset, @Null Integer limit) {
        log.info("findAll()");
        return em.createQuery("SELECT u FROM identity_user u ORDER BY :sort", IdentityUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<IdentityUser> findAllByGroupId(@NotNull UUID groupId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByGroupId(" + groupId + ")");
        return em
                .createQuery("SELECT u FROM identity_user u WHERE u.group_id = groupId ORDER BY :sort",
                        IdentityUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }

    @Override
    public List<IdentityUser> findAllByRoleId(@NotNull UUID roleId, @Null String sort, @Null Integer offset,
            @Null Integer limit) {
        log.info("findAllByRoleId(" + roleId + ")");
        return em
                .createQuery("SELECT u FROM identity_user u WHERE u.role_id = groupId ORDER BY :sort",
                        IdentityUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10)).getResultList();
    }
}
