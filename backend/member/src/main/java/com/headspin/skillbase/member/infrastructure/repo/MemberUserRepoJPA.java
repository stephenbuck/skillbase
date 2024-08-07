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

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberGroup;
import com.headspin.skillbase.member.domain.MemberUser;
import com.headspin.skillbase.member.domain.MemberUserRepo;

@RequestScoped
public class MemberUserRepoJPA implements MemberUserRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberUserRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid MemberUser user) {
        em.persist(user);
        return user.user_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID user_id) {
        em.remove(em.find(MemberUser.class, user_id));
    }

    @Override
    @Transactional
    public MemberUser update(@NotNull @Valid MemberUser user) {
        return em.merge(user);
    }

    @Override
    public Optional<MemberUser> findById(@NotNull UUID user_id) {
        return Optional.ofNullable(em.find(MemberUser.class, user_id));
    }

    @Override
    public List<MemberUser> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT u FROM MemberUser u ORDER BY :sort", MemberUser.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "user_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberAchievement> findUserAchievements(@NotNull UUID user_id, String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT a FROM MemberAchievement a ORDER BY :sort", MemberAchievement.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "user_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<MemberGroup> findUserGroups(@NotNull UUID user_id, String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT g FROM MemberGroup g ORDER BY :sort", MemberGroup.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "user_id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Transactional
    public void insertUserAchievement(@NotNull UUID user_id, @NotNull UUID achievement_id) {
    }

    @Transactional
    public void deleteUserAchievement(@NotNull UUID user_id, @NotNull UUID achievement_id) {
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM MemberUser u", Long.class)
                .getSingleResult().longValue();
    }
}
