package com.headspin.skillbase.workflow.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowRepo;

@RequestScoped
public class WorkflowRepoJPA implements WorkflowRepo {

    private static final Logger logger = Logger.getLogger(WorkflowRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-workflow")
    private EntityManager em;

    public Optional<Workflow> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Workflow.class, id));
    }

    public List<Workflow> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT w FROM Workflow w", Workflow.class).getResultList();
    }
}