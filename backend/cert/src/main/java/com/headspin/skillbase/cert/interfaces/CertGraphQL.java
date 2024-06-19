package com.headspin.skillbase.cert.interfaces;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.cert.domain.Cert;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertGraphQL {

    @Inject
    private CertService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(
            @Name("cert") Cert cert) {
        service.insert(cert);
    }

    @Mutation("update")
    @Description("update")
    public void update(
            @Name("cert") Cert cert) {
        service.update(cert);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(
            @Name("id") UUID id) {
        service.deleteById(id);
    }

    @Query("findById")
    @Description("findById")
    public Cert findById(
            @Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<Cert> findAll(
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllBySkillId")
    @Description("findAllBySkillId")
    public List<Cert> findAllBySkillId(
            @Name("skill_id") UUID skillId,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAllBySkillId(skillId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<Cert> findAllByUserId(
            @Name("user_id") UUID userId,
            @Name("sort") String sort,
            @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
