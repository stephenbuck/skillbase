package com.headspin.skillbase.certificate.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.headspin.skillbase.certificate.domain.Certificate;
import com.headspin.skillbase.certificate.domain.CertificateId;
import com.headspin.skillbase.certificate.domain.CertificateRepo;

@RequestScoped
public class CertificateRepoJPA implements CertificateRepo {

    private static final Logger logger = Logger.getLogger(CertificateRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-certificates")
    private EntityManager em;
    
    @Override
    @Transactional
    public void insert(Certificate certificate) {
        logger.info("insert()");
        em.persist(certificate);
    }

    @Override
    @Transactional
    public void delete(Certificate certificate) {
        logger.info("delete()");
        em.remove(certificate);
    }

    @Override
    @Transactional
    public void deleteById(CertificateId id) {
        logger.info("deleteById(" + id + ")");
        findById(id).ifPresent(em::remove);
    }

    @Override
    @Transactional
    public Certificate update(Certificate certificate) {
        logger.info("update()");
        return em.merge(certificate);
    }

    @Override
    public Optional<Certificate> findById(CertificateId id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Certificate.class, id));
    }

    @Override
    public List<Certificate> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT c FROM certificate c", Certificate.class).getResultList();
    }
}
