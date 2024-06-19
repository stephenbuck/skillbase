package com.headspin.skillbase.user.infrastructure;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import com.headspin.skillbase.user.domain.User;
import com.headspin.skillbase.user.domain.UserRepo;

@RequestScoped
public class UserRepoJPA implements UserRepo {

    private static final Logger logger = Logger.getLogger(UserRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-user")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(
            @NotNull User user) {
        logger.info("insert(" + user + ")");
        em.persist(user);
    }

    @Override
    @Transactional
    public User update(
            @NotNull User user) {
        logger.info("update(" + user + ")");
        return em.merge(user);
    }

    @Override
    @Transactional
    public void delete(
            @NotNull User user) {
        logger.info("delete(" + user + ")");
        em.remove(user);
    }

    @Override
    @Transactional
    public void deleteById(
            @NotNull UUID id) {
        logger.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    public Optional<User> findById(
            @NotNull UUID id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll(
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAll()");
        return em.createQuery("SELECT u FROM user u ORDER BY :sort", User.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<User> findAllByGroupId(
            @NotNull UUID id,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllByGroupId(" + id + ")");
        return em.createQuery("SELECT u FROM user u ORDER BY :sort", User.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }

    @Override
    public List<User> findAllByUserNameLike(
            @NotNull String pattern,
            @Null String sort,
            @Null Integer offset,
            @Null Integer limit) {
        logger.info("findAllByUserNameLike(" + pattern + ")");
        return em.createQuery("SELECT u FROM user u WHERE u.user_name LIKE ':pattern' ORDER BY :sort", User.class)
                .setParameter("sort", Objects.requireNonNullElse(sort, "id"))
                .setFirstResult(Objects.requireNonNullElse(offset, 0))
                .setMaxResults(Objects.requireNonNullElse(limit, 10))
                .getResultList();
    }
}
