package com.headspin.skillbase.workflow.interfaces.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDefinitionRepo;
import com.headspin.skillbase.workflow.providers.WorkflowEngineProvider;
import com.headspin.skillbase.common.providers.CommonConfigProvider;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.common.providers.CommonFeaturesProvider;
import com.headspin.skillbase.common.providers.CommonStorageProvider;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Workflow definitions service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@Stateless
@PermitAll
// @DeclareRoles({ "Admin", "Publisher", "Creator", "Member" })
// @DeclareRoles(SecurityRole.list())
public class WorkflowDefinitionsService {

    @Resource
    private SessionContext ctx;

    @Inject
    private WorkflowDefinitionRepo repo;

    @Inject
    private WorkflowEngineProvider work;

    @Inject
    private CommonConfigProvider conf;

    @Inject
    private CommonEventsProvider evnt;

    @Inject
    private CommonFeaturesProvider feat;

    @Inject
    private CommonStorageProvider stor;

    /**
     * Insert a workflow definition.
     *
     * @param definition The new definition.
     * @return The id of the new definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public UUID insert(@NotNull @Valid final WorkflowDefinition definition) throws Exception {
        final UUID definition_id = repo.insert(definition);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_DEFINITION_CREATED,
                WorkflowDefinition.toJson(definition));
        return definition_id;
    }

    /**
     * Delete a workflow definition.
     *
     * @param definition_id The requested definition id.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public void delete(@NotNull final UUID definition_id) throws Exception {
        repo.delete(definition_id);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_DEFINITION_DELETED,
                "{}");
    }

    /**
     * Update a workflow definition.
     *
     * @param instance The updated definition.
     * @return The updated definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    @Transactional
    public WorkflowDefinition update(@NotNull @Valid final WorkflowDefinition definition) throws Exception {
        final WorkflowDefinition updated = repo.update(definition);
        evnt.produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_DEFINITION_UPDATED,
                WorkflowDefinition.toJson(updated));
        return updated;
    }

    /**
     * Find a workflow definition by id.
     *
     * @param definition_id The requested definition id.
     * @return An optional workflow definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findById(@NotNull final UUID definition_id) {
        return repo.findById(definition_id);
    }

    /**
     * Find a workflow definition by credential id.
     *
     * @param credential_id The requested credential id.
     * @return An optional workflow definition.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Optional<WorkflowDefinition> findByCredentialId(@NotNull final UUID credential_id) {
        return repo.findByCredentialId(credential_id);
    }

    /**
     * Find all workflow definitions.
     *
     * @param sort   Sort field.
     * @param offset Offset of first result.
     * @param limit  Limit of results returned.
     * @return A list of workflow definitiones.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public List<WorkflowDefinition> findAll(final String sort, final Integer offset, final Integer limit) {
        return repo.findAll(sort, offset, limit);
    }

    /**
     * Return a count of workflow definitions.
     *
     * @return The count.
     * @since 1.0
     */
    // @RolesAllowed({ "Admin" })
    public Long count() {
        return repo.count();
    }

    // @RolesAllowed({ "Admin" })
    public Integer test() {
        log.info("test:");
        conf.test();
        evnt.test();
        feat.test();
        stor.test();
        work.test();
        return 0;
    }
}
