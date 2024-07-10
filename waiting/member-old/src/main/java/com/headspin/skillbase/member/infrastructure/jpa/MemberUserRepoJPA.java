package com.headspin.skillbase.member.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

/*
 * MemberUserRepoJPA implements the MemberUserRepo
 * interface using JPA and PostgreSQL.
 */

@RequestScoped
public class MemberUserRepoJPA implements MemberUserRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberUserRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull MemberUser user) {
        em.persist(user);
        return user.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public MemberUser update(@NotNull MemberUser user) {
        return em.merge(user);
    }

    @Override
    public Optional<MemberUser> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(MemberUser.class, id));
    }

    @Override
    public List<MemberUser> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT u FROM MemberUser u ORDER BY :sort", MemberUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberUser> findAllByGroupId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT u FROM MemberUser u WHERE u.group_id = groupId ORDER BY :sort",
                        MemberUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberUser> findAllByRoleId(@NotNull UUID roleId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT u FROM MemberUser u WHERE u.role_id = groupId ORDER BY :sort",
                        MemberUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM MemberUser u", Long.class)
                .getSingleResult().longValue();
    }
}
