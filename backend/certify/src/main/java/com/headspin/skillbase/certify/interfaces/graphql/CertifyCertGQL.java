package com.headspin.skillbase.certify.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.certify.domain.CertifyCert;
import com.headspin.skillbase.certify.interfaces.service.CertifyCertService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertifyCertGQL {

    @Inject
    private CertifyCertService service;

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("cert") CertifyCert cert) {
        return service.insert(cert);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("cert") CertifyCert cert) {
        service.update(cert);
    }

    @Query("findById")
    @Description("findById")
    public CertifyCert findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CertifyCert> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllBySkillId")
    @Description("findAllBySkillId")
    public List<CertifyCert> findAllBySkillId(@Name("skill_id") UUID skillId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllBySkillId(skillId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<CertifyCert> findAllByUserId(@Name("user_id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
