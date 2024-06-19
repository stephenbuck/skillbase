package com.headspin.skillbase.user.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.user.domain.User;
import com.headspin.skillbase.user.domain.UserEvent;
import com.headspin.skillbase.user.domain.UserRepo;

import jakarta.annotation.Resource;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Stateless
@DeclareRoles({"Admin", "User"})
public class UserService {

    @Inject
    private UserRepo repo;

    @Resource
    SessionContext ctx;

    @Transactional
    @RolesAllowed({"Admin"})
    void insert(
        @NotNull User user) {
        repo.insert(user);
        UserEvent.build("com.headspin.skillbase.user.inserted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    User update(
        @NotNull User user) {
        User updated = repo.update(user);
        UserEvent.build("com.headspin.skillbase.user.updated");
        return updated;
    }

    @Transactional
    @RolesAllowed({"Admin"})
    void delete(
        @NotNull User user) {
        repo.delete(user);
        UserEvent.build("com.headspin.skillbase.user.deleted");
    }

    @Transactional
    @RolesAllowed({"Admin"})
    void deleteById(
        @NotNull UUID id) {
        repo.deleteById(id);
        UserEvent.build("com.headspin.skillbase.user.deleted");
    }

    @RolesAllowed({"Admin"})
    public Optional<User> findById(
        @NotNull UUID id) {
        return repo.findById(id);
    }

    @RolesAllowed({"Admin"})
    public List<User> findAll(
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit
    ) {
        return repo.findAll(sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<User> findAllByUserNameLike(
        @NotNull String pattern,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit) {
        return repo.findAllByUserNameLike(pattern, sort, offset, limit);
    }

    @RolesAllowed({"Admin"})
    public List<User> findAllByGroupId(
        @NotNull UUID id,
        @Null String sort,
        @Null Integer offset,
        @Null Integer limit) {
        return repo.findAllByGroupId(id, sort, offset, limit);
    }
}
