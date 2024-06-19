package com.headspin.skillbase.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public interface UserRepo {

    @Transactional
    void insert(
        @NotNull User user);

    @Transactional
    void delete(
        @NotNull User user);

    @Transactional
    User update(
        @NotNull User user);

    @Transactional
    void deleteById(
        @NotNull UUID id);

    Optional<User> findById(
        @NotNull UUID id);

    List<User> findAll(
        @NotNull String sort,
        @Null Integer offset, 
        @Null Integer limit);

    List<User> findAllByUserNameLike(
        @NotNull String pattern, 
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit);

    List<User> findAllByGroupId(
        @NotNull UUID id,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit);
}