package com.headspin.skillbase.certificate.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import com.headspin.skillbase.certificate.domain.CertificateId;
import com.headspin.skillbase.certificate.domain.Certificate;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class CertificateGraphQL {

    @Inject
    private CertificateService service;
    
    @Mutation("insert")
    @Description("insert")
    public void insert(@Name("certificate") @Valid Certificate certificate) {
        service.insert(certificate);
    }

    @Mutation("deleteById")
    @Description("deleteById")
    public void deleteById(@Name("id") CertificateId id) {
        service.deleteById(id);
    }
    
    @Mutation("update")
    @Description("update")
    public void update(@Name("certificate") @Valid Certificate certificate) {
        service.update(certificate);
    }

    @Query("findById")
    @Description("findById")
    public Certificate findById(@Name("id") CertificateId id) {
        return service.findById(id).orElse(null);
    }
    
    @Query("findAll")
    @Description("findAll")
    public List<Certificate> findAll() {
        return service.findAll();
    }
}
