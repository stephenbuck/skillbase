package com.headspin.skillbase.member.infrastructure.repo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.member.domain.MemberAchievement;
import com.headspin.skillbase.member.domain.MemberAchievementRepo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestScoped
public class MemberAchievementRepoJPA implements MemberAchievementRepo {

    @PersistenceContext(name = "skillbase_member")
    private EntityManager em;

    public MemberAchievementRepoJPA() {
    }

    @Override
    @Transactional
    public UUID insert(@NotNull @Valid final MemberAchievement achievement) {
        em.persist(achievement);
        return achievement.achievement_id;
    }

    @Override
    @Transactional
    public void delete(@NotNull final UUID achievement_id) {
        em.remove(em.find(MemberAchievement.class, achievement_id));
    }

    @Override
    @Transactional
    public MemberAchievement update(@NotNull @Valid final MemberAchievement achievement) {
        return em.merge(achievement);
    }

    @Override
    public Optional<MemberAchievement> findById(@NotNull final UUID achievement_id) {
        return Optional.ofNullable(em.find(MemberAchievement.class, achievement_id));
    }

    @Override
    public List<MemberAchievement> findAll(final String sort, final Integer offset, final Integer limit) {
        return em.createQuery("SELECT a FROM MemberAchievement a ORDER BY :sort", MemberAchievement.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "achievement_id"))
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
