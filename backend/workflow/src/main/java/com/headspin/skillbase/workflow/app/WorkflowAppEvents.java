package com.headspin.skillbase.workflow.app;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.app.AppEvents;
import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.common.providers.CommonEventsProvider;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * Application events for the workflow service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowAppEvents extends AppEvents {

    @Inject
    private WorkflowDeploymentsService deps;

    @Inject
    private WorkflowDefinitionsService defs;

    private final List<String> topics = Arrays.asList(
        CatalogEvent.CATALOG_EVENT_TOPIC,
        MemberEvent.MEMBER_EVENT_TOPIC
    );

    @Inject
    public WorkflowAppEvents(
        final CommonEventsProvider evnt
    )
    {
        evnt.consume(topics, this);
    }

    /**
     * When a CloudEvent event arrives, this method converts it to
     * a skillbase event and dispatches it to to the correct handler
     * based on the event topic.
     * 
     * @param topic
     * @param event
     */
    public void onCloudEvent(final String topic, final CloudEvent event)  {

        log.info("cloud event = {}", event);

        /*
        try {

            final CloudEventData data = event.getData();

            final ByteArrayInputStream stream = new ByteArrayInputStream(data.toBytes());

            final JsonParser parser = Json.createParser(stream);

            if (parser.hasNext()) {

                parser.next();

                final UUID eventId = UUID.fromString(event.getId());
                final String eventType = event.getType();
                final JsonObject eventJson = parser.getObject();

                switch (eventType) {
                    case CatalogEvent.CATALOG_CREDENTIAL_CREATED:
                        onCredentialCreated(event, eventJson);
                        break;
                    case CatalogEvent.CATALOG_CREDENTIAL_DELETED:
                        onCredentialDeleted(event, eventJson);
                        break;
                    case CatalogEvent.CATALOG_CREDENTIAL_UPDATED:
                        onCredentialUpdated(event, eventJson);
                        break;
                    case CatalogEvent.CATALOG_SKILL_CREATED:
                        onSkillCreated(event, eventJson);
                        break;
                    case CatalogEvent.CATALOG_SKILL_DELETED:
                        onSkillDeleted(event, eventJson);
                        break;
                    case CatalogEvent.CATALOG_SKILL_UPDATED:
                        onSkillUpdated(event, eventJson);
                        break;
                    default:
                        break;
                }
        }
        catch (Exception e) {
            log.error("TBD", e);
        }
        */
    }

    /**
     * When a CatalogEvent.CredentialCreated event arrives, the workflow
     * service creates a corresponding workflow definition entity.
     * 
     * @param event
     * @param json
     */
    private void onCredentialCreated(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onCredentialCreated()");

        final WorkflowDefinition definition = new WorkflowDefinition();
        definition.peer_id = null;
        definition.credential_id = UUID.fromString(json.getString("credential_id"));
//        definition.deployment_id = json.getString("deployment_id");
        definition.title = json.getString("title");
        definition.note = json.getString("note");
        
        final UUID id = defs.insert(definition);
    }

    /**
     * When a CatalogEvent.CredentialDeleted event arrives, the workflow
     * service deletes the corresponding workflow definition entity.
     * 
     * @param event
     * @param json
     */
    private void onCredentialDeleted(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onCredentialDeleted()");

        final UUID credential_id = UUID.fromString(json.getString("credential_id"));
        
        log.info("credential_id = {}", credential_id);

        final Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);

        final WorkflowDefinition definition = result.get();
        
        defs.delete(definition.definition_id);        
    }

    /**
     * When a CatalogEvent.CredentialUpdated event arrives, the workflow
     * service updates the corresponding workflow definition entity.
     * 
     * @param event
     * @param json
     */
    private void onCredentialUpdated(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onCredentialUpdated()");

        final UUID credential_id = UUID.fromString(json.getString("credential_id"));
        
        final Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);

        final WorkflowDefinition definition = result.get();
        definition.credential_id = credential_id;
        definition.deployment_id = UUID.fromString(json.getString("deployment_id"));
        definition.title = json.getString("title");
        definition.note = json.getString("note");
        
        defs.update(definition);
    }

    /**
     * When a CatalogEvent.SkillCreated event arrives, the workflow
     * service creates a corresponding workflow deployment entity.
     * 
     * @param event
     * @param json
     */
    private void onSkillCreated(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onSkillCreated()");

        final WorkflowDeployment deployment = new WorkflowDeployment();
        deployment.peer_id = null;
        deployment.skill_id = UUID.fromString(json.getString("skill_id"));
        deployment.state = json.getString("state");
        deployment.title = json.getString("title");
        deployment.note = json.getString("note");
        
        final UUID id = deps.insert(deployment);
    }

    /**
     * When a CatalogEvent.SkillDeleted event arrives, the workflow
     * service deletes the corresponding workflow deployment entity.
     * 
     * @param event
     * @param json
     */
    private void onSkillDeleted(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onSkillDelete():");

        final UUID skill_id = UUID.fromString(json.getString("skill_id"));
     
        log.info("skill_id = {}", skill_id);

        final Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);

        final WorkflowDeployment deployment = result.get();
        
        deps.delete(deployment.deployment_id);
    }

    /**
     * When a CatalogEvent.SkillUpdated event arrives, the workflow
     * service updates the corresponding workflow deployment entity.
     * 
     * @param event
     * @param json
     */
    private void onSkillUpdated(final CatalogEvent event, final JsonObject json) throws Exception {

        log.info("onSkillUpdate():");

        final UUID skill_id = UUID.fromString(json.getString("skill_id"));
     
        log.info("skill_id = {}", skill_id);

        final Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);

        final WorkflowDeployment deployment = result.get();
        deployment.state = json.getString("state");
        deployment.title = json.getString("title");
        deployment.note = json.getString("note");
        
        deps.update(deployment);
    }

    /**
     * When a MemberEvent event arrives, this method dispatches it
     * to the correct handler based on the event type.
     * 
     * @param event
     * @param json
     */
    /*
    private void onMemberEvent(final MemberEvent event, final JsonObject json) throws Exception {

        log.info("member event = {}", event);

        switch (event.type()) {
            case MemberEvent.MEMBER_ACHIEVEMENT_CREATED:
                onAchievementCreated(event, json);
                break;
            case MemberEvent.MEMBER_ACHIEVEMENT_DELETED:
                onAchievementDeleted(event, json);
                break;
            case MemberEvent.MEMBER_ACHIEVEMENT_UPDATED:
                onAchievementUpdated(event, json);
                break;
            case MemberEvent.MEMBER_USER_CREATED:
                onUserCreated(event, json);
                break;
            case MemberEvent.MEMBER_USER_DELETED:
                onUserDeleted(event, json);
                break;
            case MemberEvent.MEMBER_USER_UPDATED:
                onUserUpdated(event, json);
                break;
            default:
                break;
        }
    }
    */
}
