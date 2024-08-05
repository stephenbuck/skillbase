package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeaturesProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEventsProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Workflow deployments service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowDeploymentsService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowDeploymentRepo repo;

    @Inject
    private WorkflowConfigProvider conf;

    @Inject
    private WorkflowFeaturesProvider feat;

    @Inject
    private WorkflowEventsProvider evnt;

    @Inject
    private WorkflowEngineProvider work;

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowDeployment deployment) {
        UUID id = repo.insert(deployment);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_CREATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(deployment.id))
                .add("title", deployment.title)
                .add("skill_id", String.valueOf(deployment.skill_id))
                .add("state", deployment.state)
                .add("title", deployment.title)
                .add("note", deployment.note)
                .add("created_at", String.valueOf(deployment.createdAt))
                .build());
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build());
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDeployment update(@NotNull @Valid WorkflowDeployment deployment) {
        WorkflowDeployment updated = repo.update(deployment);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED,
            Json.createObjectBuilder()
                .add("id", String.valueOf(updated.id))
                .add("title", updated.title)
                .add("skill_id", String.valueOf(updated.skill_id))
                .add("state", updated.state)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.createdAt))
                .add("updated_at", String.valueOf(updated.updatedAt))
                .build());
        return updated;
    }

    /**
     * Returns a workflow deployment given an id.
     *
     * @param id Requested deployment id.
     * @return An optional workflow process.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    /**
     * Returns a workflow deployment given an id.
     *
     * @param id Requested deployment id.
     * @return An optional workflow process.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findBySkillId(@NotNull UUID skill_id) {
        return repo.findBySkillId(skill_id);
    }

    /**
     * Returns a list of all workflow deployments.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of workflow processes.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<WorkflowDeployment> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Returns a count of workflow deployments.
     *
     * @return The count.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

//    @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        feat.test();
        evnt.test();
        work.test();
        return 0;
    }
}
