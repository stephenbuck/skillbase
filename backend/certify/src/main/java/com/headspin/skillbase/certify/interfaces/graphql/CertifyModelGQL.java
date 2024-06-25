package com.headspin.skillbase.certify.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.certify.domain.CertifyModel;
import com.headspin.skillbase.certify.interfaces.service.CertifyModelService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertifyModelGQL {

    @Inject
    private CertifyModelService service;

    @Mutation("insert")
    @Description("insert")
    public void insert(@Name("model") CertifyModel model) {
        service.insert(model);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("model") CertifyModel model) {
        service.update(model);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") UUID id) {
        service.deleteById(id);
    }

    @Query("findById")
    @Description("findById")
    public CertifyModel findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CertifyModel> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllBySkillId")
    @Description("findAllBySkillId")
    public List<CertifyModel> findAllBySkillId(@Name("skill_id") UUID skillId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllBySkillId(skillId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<CertifyModel> findAllByUserId(@Name("user_id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
