package com.headspin.skillbase.workflow.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.headspin.skillbase.workflow.domain.Process;
import com.headspin.skillbase.workflow.domain.Workflow;
import com.headspin.skillbase.workflow.domain.WorkflowRepo;

@RequestScoped
public class WorkflowRepoJPA implements WorkflowRepo {

    private static final Logger logger = Logger.getLogger(WorkflowRepoJPA.class.getName());

    @PersistenceContext(name = "skillbase-workflow")
    private EntityManager em;

    public void insert(Workflow workflow) {
        logger.info("insert()");
        em.persist(workflow);
    }

    public void delete(Workflow workflow) {
        logger.info("delete()");
        em.remove(workflow);
    }

    public void deleteById(int id) {
        logger.info("deleteById(" + id + ")");
        em.remove(em.find(Workflow.class, id));
    }

    public Workflow update(Workflow workflow) {
        logger.info("update()");
        return em.merge(workflow);
    }

    public Optional<Workflow> findById(int id) {
        logger.info("findById(" + id + ")");
        return Optional.ofNullable(em.find(Workflow.class, id));
    }

    public List<Workflow> findAll() {
        logger.info("findAll()");
        return em.createQuery("SELECT w FROM Workflow w", Workflow.class).getResultList();
    }
}