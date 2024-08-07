package com.headspin.skillbase.member.infrastructure.repo;

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

import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberGroupRepo;
import com.headspin.skillbase.member.domain.MemberUser;

@RequestScoped
public class MemberGroupRepoJPA implements MemberGroupRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberGroupRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid MemberGroup group) {
        em.persist(group);
        return group.group_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID group_id) {
        em.remove(em.find(MemberGroup.class, group_id));
    }

    @Override
    @Transactional
    public MemberGroup update(@NotNull @Valid MemberGroup group) {
        return em.merge(group);
    }

    @Override
    public Optional<MemberGroup> findById(@NotNull UUID group_id) {
        return Optional.ofNullable(em.find(MemberGroup.class, group_id));
    }

    @Override
    public List<MemberGroup> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT g FROM MemberGroup g ORDER BY :sort", MemberGroup.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "group_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberUser> findGroupUsers(@NotNull UUID id, String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT u FROM MemberUser u ORDER BY :sort", MemberUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "group_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    @Transactional
    public void insertGroupUser(@NotNull UUID id, @NotNull UUID user_id) {
    }

    @Override
    @Transactional
    public void deleteGroupUser(@NotNull UUID id, @NotNull UUID user_id) {
    }
    
    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM MemberGroup g", Long.class)
                .getSingleResult().longValue();
    }
}
