package com.headspin.skillbase.certify.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.certify.domain.CertifyTask;
import com.headspin.skillbase.certify.interfaces.service.CertifyTaskService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertifyTaskGQL {

    @Inject
    private CertifyTaskService service;

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("process") CertifyTask process) {
        return service.insert(process);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("process") CertifyTask process) {
        service.update(process);
    }

    @Query("findById")
    @Description("findById")
    public CertifyTask findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CertifyTask> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllBySkillId")
    @Description("findAllBySkillId")
    public List<CertifyTask> findAllBySkillId(@Name("skill_id") UUID skillId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllBySkillId(skillId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<CertifyTask> findAllByUserId(@Name("user_id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
