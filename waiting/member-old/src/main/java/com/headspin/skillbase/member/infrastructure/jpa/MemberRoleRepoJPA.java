package com.headspin.skillbase.member.infrastructure.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberRole;
import com.headspin.skillbase.member.domain.MemberRoleRepo;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

/*
 * MemberRoleRepoJPA implements the MemberRoleRepo
 * interface using JPA and PostgreSQL.
 */

@RequestScoped
public class MemberRoleRepoJPA implements MemberRoleRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberRoleRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull MemberRole role) {
        em.persist(role);
        return role.id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public MemberRole update(@NotNull MemberRole role) {
        return em.merge(role);
    }

    @Override
    public Optional<MemberRole> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(MemberRole.class, id));
    }

    @Override
    public List<MemberRole> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT r FROM MemberRole r ORDER BY :sort", MemberRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberRole> findAllByGroupId(@NotNull UUID groupId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT r FROM MemberRole r WHERE r.group_id = :groupId ORDER BY :sort",
                        MemberRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberRole> findAllByUserId(@NotNull UUID userId, String sort, Integer offset,
            Integer limit) {
        return em
                .createQuery("SELECT r FROM MemberRole r WHERE r.user_id = :userId ORDER BY :sort",
                        MemberRole.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 1))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM MemberRole r", Long.class)
                .getSingleResult().longValue();
    }
}
