package com.headspin.skillbase.user.interfaces;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.user.domain.User;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class UserGraphQL {

    @Inject
    private UserService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(
            @Name("user") User user) {
        service.insert(user);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(
            @Name("id") UUID id) {
        service.deleteById(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(
            @Name("user") User user) {
        service.update(user);
    }

    @Query("findById")
    @Description("findById")
    public User findById(
            @Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<User> findAll(
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllByUserNameLike")
    @Description("findAllByUserNameLike")
    public List<User> findAllByUserNameLike(
            @Name("pattern") String pattern,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAllByUserNameLike(pattern, sort, offset, limit);
    }

    @Query("findAllByGroupId")
    @Description("findAllByGroupId")
    public List<User> findAllByGroupId(
            @Name("id") UUID id,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("limit") Integer limit) {
        return service.findAllByGroupId(id, sort, offset, limit);
    }
}
