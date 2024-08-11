package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.domain.WorkflowDeploymentRepo;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEventsProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeaturesProvider;

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

    /**
     * Inserts a new workflow deployment.
     *
     * @param deployment The new deployment.
     * @return The id of the new deployment.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final WorkflowDeployment deployment) {
        final UUID deployment_id = repo.insert(deployment);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_CREATED,
            Json.createObjectBuilder()
                .add("deployment_id", String.valueOf(deployment.deployment_id))
                .add("skill_id", String.valueOf(deployment.skill_id))
                .add("state", deployment.state)
                .add("title", deployment.title)
                .add("note", deployment.note)
                .add("created_at", String.valueOf(deployment.created_at))
                .add("updated_at", String.valueOf(deployment.updated_at))
                .build());
        return deployment_id;
    }

    /**
     * Deletes a workflow deployment given an id.
     *
     * @param deployment_id The requested deployment id.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID deployment_id) {
        repo.delete(deployment_id);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED,
            Json.createObjectBuilder()
                .add("deployment_id", String.valueOf(deployment_id))
                .build());
    }

    /**
     * Updates an existing workflow deployment.
     *
     * @param deployment The updated deployment.
     * @return The updated deployment.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDeployment update(@NotNull @Valid final WorkflowDeployment deployment) {
        final WorkflowDeployment updated = repo.update(deployment);
        evnt.produce(
            WorkflowEvent.WORKFLOW_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED,
            Json.createObjectBuilder()
                .add("deployment_id", String.valueOf(updated.deployment_id))
                .add("skill_id", String.valueOf(updated.skill_id))
                .add("state", updated.state)
                .add("title", updated.title)
                .add("note", updated.note)
                .add("created_at", String.valueOf(updated.created_at))
                .add("updated_at", String.valueOf(updated.updated_at))
                .build());
        return updated;
    }

    /**
     * Returns a workflow deployment given an id.
     *
     * @param deployment_id Requested deployment id.
     * @return An optional workflow deployment.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findById(@NotNull final UUID deployment_id) {
        return repo.findById(deployment_id);
    }

    /**
     * Returns a workflow deployment given a skill id.
     *
     * @param skill_id The requested skill id.
     * @return An optional workflow deployment.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowDeployment> findBySkillId(@NotNull final UUID skill_id) {
        return repo.findBySkillId(skill_id);
    }

    /**
     * Returns a list of all workflow deployments.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of workflow deployments.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<WorkflowDeployment> findAll(final String sort, final Integer offset, final Integer limit) {
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
