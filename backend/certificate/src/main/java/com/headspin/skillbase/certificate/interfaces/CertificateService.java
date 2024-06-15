package com.headspin.skillbase.certificate.interfaces;

import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

import com.headspin.skillbase.certificate.domain.Certificate;
import com.headspin.skillbase.certificate.domain.CertificateEvent;
import com.headspin.skillbase.certificate.domain.CertificateId;
import com.headspin.skillbase.certificate.domain.CertificateRepo;

public class CertificateService {

    @Inject
    private CertificateRepo repo;

    void insert(Certificate certificate) {
        repo.insert(certificate);
        CertificateEvent.build("com.headspin.skillbase.certificate.inserted");
    }

    void delete(Certificate certificate) {
        repo.delete(certificate);
        CertificateEvent.build("com.headspin.skillbase.certificate.deleted");
    }

    void deleteById(CertificateId id) {
        repo.deleteById(id);
        CertificateEvent.build("com.headspin.skillbase.certificate.deleted");
    }

    Certificate update(Certificate certificate) {
        Certificate updated = repo.update(certificate);
        CertificateEvent.build("com.headspin.skillbase.certificate.updated");
        return updated;
    }

    public Optional<Certificate> findById(CertificateId id) {
        return repo.findById(id);
    }

    public List<Certificate> findAll() {
        return repo.findAll();
    }
}
