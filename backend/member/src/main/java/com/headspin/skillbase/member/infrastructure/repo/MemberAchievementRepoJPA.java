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
import com.headspin.skillbase.member.domain.MemberAchievementRepo;

@RequestScoped
public class MemberAchievementRepoJPA implements MemberAchievementRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberAchievementRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid MemberAchievement achievement) {
        em.persist(achievement);
        return achievement.id;
    }

    @Override
    @Transactional
    public boolean delete(@NotNull UUID id) {
        em.remove(em.find(MemberAchievement.class, id));
        return true;
    }

    @Override
    @Transactional
    public MemberAchievement update(@NotNull @Valid MemberAchievement achievement) {
        return em.merge(achievement);
    }

    @Override
    public Optional<MemberAchievement> findById(@NotNull UUID id) {
        return Optional.ofNullable(em.find(MemberAchievement.class, id));
    }

    @Override
    public List<MemberAchievement> findAll(String sort, Integer offset, Integer limit) {
        return em.createQuery("SELECT a FROM MemberAchievement a ORDER BY :sort", MemberAchievement.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(*) FROM MemberAchievement a", Long.class)
                .getSingleResult().longValue();
    }
}
