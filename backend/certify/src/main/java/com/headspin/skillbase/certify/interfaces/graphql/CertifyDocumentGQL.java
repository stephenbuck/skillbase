package com.headspin.skillbase.certify.interfaces.graphql;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;

import com.headspin.skillbase.certify.domain.CertifyDocument;
import com.headspin.skillbase.certify.interfaces.service.CertifyDocumentService;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertifyDocumentGQL {

    @Inject
    private CertifyDocumentService service;

    @Mutation("insert")
    @Description("insert")
    public UUID insert(@Name("document") CertifyDocument document) {
        return service.insert(document);
    }

    @Mutation("delete")
    @Description("delete")
    public void delete(@Name("id") UUID id) {
        service.delete(id);
    }

    @Mutation("update")
    @Description("update")
    public void update(@Name("document") CertifyDocument document) {
        service.update(document);
    }

    @Query("findById")
    @Description("findById")
    public CertifyDocument findById(@Name("id") UUID id) {
        return service.findById(id).orElse(null);
    }

    @Query("findAll")
    @Description("findAll")
    public List<CertifyDocument> findAll(@Name("sort") String sort, @Name("offset") Integer offset,
            @Name("offset") Integer limit) {
        return service.findAll(sort, offset, limit);
    }

    @Query("findAllBySkillId")
    @Description("findAllBySkillId")
    public List<CertifyDocument> findAllBySkillId(@Name("skill_id") UUID skillId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllBySkillId(skillId, sort, offset, limit);
    }

    @Query("findAllByUserId")
    @Description("findAllByUserId")
    public List<CertifyDocument> findAllByUserId(@Name("user_id") UUID userId, @Name("sort") String sort,
            @Name("offset") Integer offset, @Name("offset") Integer limit) {
        return service.findAllByUserId(userId, sort, offset, limit);
    }
}
