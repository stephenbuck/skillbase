package com.headspin.skillbase.certificate.domain;

import org.jmolecules.ddd.types.Repository;

import java.util.List;
import java.util.Optional;

public interface CertificateRepo extends Repository<Certificate, CertificateId> {

    void insert(Certificate certificate);

    void delete(Certificate certificate);

    void deleteById(CertificateId id);

    Certificate update(Certificate certificate);

    Optional<Certificate> findById(CertificateId id);

    List<Certificate> findAll();
}