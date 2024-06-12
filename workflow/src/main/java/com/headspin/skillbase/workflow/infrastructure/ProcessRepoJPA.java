package com.headspin.skillbase.workflow.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.workflow.domain.Process;
import com.headspin.skillbase.workflow.domain.ProcessRepo;

@RequestScoped
public class ProcessRepoJPA implements ProcessRepo {

    private static final Logger logger = Logger.getLogger(ProcessRepoJPA.class.getName());
    
    @PersistenceContext(name = "skillbase-workflow")
    private EntityManager em;
    
    public Optional<Process> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Process.class, id));
    }
    
    public List<Process> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT p FROM Process p", Process.class).getResultList();
    }
  }