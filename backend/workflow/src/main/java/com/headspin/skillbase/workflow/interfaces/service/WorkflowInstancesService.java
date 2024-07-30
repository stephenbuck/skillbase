package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowInstance;
import com.headspin.skillbase.workflow.domain.WorkflowInstanceRepo;
import com.headspin.skillbase.workflow.infrastructure.config.WorkflowConfigProviderDefault;
import com.headspin.skillbase.workflow.infrastructure.engine.WorkflowEngineProviderFlowable;
import com.headspin.skillbase.workflow.infrastructure.feature.WorkflowFeatureProviderFlipt;
import com.headspin.skillbase.workflow.infrastructure.messaging.WorkflowEventProducerKafka;
import com.headspin.skillbase.workflow.providers.WorkflowConfigProvider;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.workflow.providers.WorkflowFeatureProvider;
import com.headspin.skillbase.workflow.providers.WorkflowProducerProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service interface for workflow instances.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowInstancesService {

    @Resource
    private SessionContext ctx;
    
    @Inject
    private WorkflowInstanceRepo repo;

    private WorkflowConfigProvider conf = new WorkflowConfigProviderDefault();
    private WorkflowFeatureProvider feat = new WorkflowFeatureProviderFlipt();
    private WorkflowProducerProvider prod = new WorkflowEventProducerKafka();
    private WorkflowEngineProvider work = new WorkflowEngineProviderFlowable();

    private void produceInstanceCreatedEvent(WorkflowInstance instance) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_INSTANCE_CREATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(instance.id))
                .add("title", instance.title)
                .build()));
    }

    private void produceInstanceDeletedEvent(UUID id) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_INSTANCE_DELETED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(id))
                .build()));
    }

    private void produceInstanceUpdatedEvent(WorkflowInstance instance) {
        prod.produce(new WorkflowEvent(
            WorkflowEvent.WORKFLOW_INSTANCE_UPDATED, 
            Json.createObjectBuilder()
                .add("id", String.valueOf(instance.id))
                .add("title", instance.title)
                .build()));
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid WorkflowInstance instance) {
        UUID id = repo.insert(instance);
        produceInstanceCreatedEvent(instance);
        return id;
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull UUID id) {
        repo.delete(id);
        produceInstanceDeletedEvent(id);
    }

//    @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowInstance update(@NotNull @Valid WorkflowInstance instance) {
        WorkflowInstance updated = repo.update(instance);
        produceInstanceUpdatedEvent(updated);
        return updated;
    }

    /**
     * Returns a workflow instance given an id.
     *
     * @param id Requested instance id.
     * @return An optional workflow instance.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public Optional<WorkflowInstance> findById(@NotNull UUID id) {
        return repo.findById(id);
    }

    /**
     * Returns a list of all workflow instances.
     *
     * @param sort Sort field.
     * @param offset Offset of first result.
     * @param limit Limit of results returned.
     * @return A list of workflow instances.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    public List<WorkflowInstance> findAll(String sort, Integer offset, Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Starts a workflow instance for a specified instance and user.
     *
     * @return The id of the workflow instance.
     * @since 1.0
     */
//    @RolesAllowed({ "Admin" })
    @Transactional
    public UUID start(UUID definition_id, UUID user_id) {
        WorkflowInstance instance = new WorkflowInstance();
        instance.peer_id = null;
        instance.definition_id = definition_id;
        instance.user_id = user_id;
        instance.title = "TBD";
        instance.note = "TBD";
        UUID instance_id = repo.insert(instance);
        // UUID peer_id = work.start(instance_id, user_id);
        return instance_id;
    }

    /**
     * Returns a count of workflow instances.
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
        conf.test();
        feat.test();
        prod.test();
        work.test();
        return 0;
    }
}
