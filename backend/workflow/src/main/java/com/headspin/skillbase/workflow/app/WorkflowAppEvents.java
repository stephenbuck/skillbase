package com.headspin.skillbase.workflow.app;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.headspin.skillbase.common.app.AppEvents;
import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.workflow.infrastructure.events.WorkflowEventsProviderKafka;
import com.headspin.skillbase.workflow.providers.WorkflowEventsProvider;

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
    private WorkflowEventsProvider evnt;

    private List<String> topics = Arrays.asList(
        CatalogEvent.CATALOG_EVENT_TOPIC,
        MemberEvent.MEMBER_EVENT_TOPIC
    );

    public WorkflowAppEvents() {
        try {
            this.evnt = new WorkflowEventsProviderKafka();
            evnt.consume(topics, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When a CloudEvent event arrives, this method converts it to
     * a skillbase event and dispatches it to to the correct handler
     * based on the event topic.
     * 
     * @param topic
     * @param event
     */
    public void onCloudEvent(String topic, CloudEvent event) {

        log.info("cloud event = {}", event);

        CloudEventData data = event.getData();

        ByteArrayInputStream stream = new ByteArrayInputStream(data.toBytes());

        JsonParser parser = Json.createParser(stream);

        if (parser.hasNext()) {

            parser.next();

            UUID eventId = UUID.fromString(event.getId());
            String eventType = event.getType();
            JsonObject eventJson = parser.getObject();

            switch (topic) {
                case CatalogEvent.CATALOG_EVENT_TOPIC:
                    onCatalogEvent(new CatalogEvent(eventId, eventType, eventJson));
                    break;
                case MemberEvent.MEMBER_EVENT_TOPIC:
                    onMemberEvent(new MemberEvent(eventId, eventType, eventJson));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * When a CatalogEvent event arrives, this method dispatches it
     * to the correct handler based on the event type.
     * 
     * @param event
     */
    private void onCatalogEvent(CatalogEvent event) {

        log.info("catalog event = {}", event);

        switch (event.type()) {

            case CatalogEvent.CATALOG_CREDENTIAL_CREATED:
                onCredentialCreated(event);
                break;
            case CatalogEvent.CATALOG_CREDENTIAL_DELETED:
                onCredentialDeleted(event);
                break;
            case CatalogEvent.CATALOG_CREDENTIAL_UPDATED:
                onCredentialUpdated(event);
                break;

            case CatalogEvent.CATALOG_SKILL_CREATED:
                onSkillCreated(event);
                break;
            case CatalogEvent.CATALOG_SKILL_DELETED:
                onSkillDeleted(event);
                break;
            case CatalogEvent.CATALOG_SKILL_UPDATED:
                onSkillUpdated(event);
                break;

            default:
                break;
        }
    }

    /**
     * When a CatalogEvent.CredentialCreated event arrives, the workflow
     * service creates a corresponding workflow definition entity.
     * 
     * @param event
     */
    private void onCredentialCreated(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID credential_id = UUID.fromString(job.getString("credential_id"));
         * 
         * WorkflowDefinition definition = new WorkflowDefinition();
         * definition.credential_id = credential_id;
         * definition.deployment_id = UUID.fromString(job.getString("deployment_id"));
         * definition.title = job.getString("title");
         * definition.note = job.getString("note");
         * log.info("definition = {}", definition);
         * 
         * defs.insert(definition);
         */
    }

    /**
     * When a CatalogEvent.CredentialDeleted event arrives, the workflow
     * service deletes the corresponding workflow definition entity.
     * 
     * @param event
     */
    private void onCredentialDeleted(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID credential_id = UUID.fromString(job.getString("credential_id"));
         * 
         * Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);
         * WorkflowDefinition definition = result.get();
         * 
         * defs.delete(definition.id);
         */
    }

    /**
     * When a CatalogEvent.CredentialUpdated event arrives, the workflow
     * service updates the corresponding workflow definition entity.
     * 
     * @param event
     */
    private void onCredentialUpdated(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID credential_id = UUID.fromString(job.getString("credential_id"));
         * 
         * Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);
         * WorkflowDefinition definition = result.get();
         * definition.credential_id = credential_id;
         * definition.deployment_id = UUID.fromString(job.getString("deployment_id"));
         * definition.title = job.getString("title");
         * definition.note = job.getString("note");
         * 
         * defs.update(definition);
         */
    }

    /**
     * When a CatalogEvent.SkillCreated event arrives, the workflow
     * service creates a corresponding workflow deployment entity.
     * 
     * @param event
     */
    private void onSkillCreated(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID skill_id = UUID.fromString(job.getString("id"));
         * 
         * WorkflowDeployment deployment = new WorkflowDeployment();
         * deployment.skill_id = skill_id;
         * deployment.state = job.getString("state");
         * deployment.title = job.getString("title");
         * deployment.note = job.getString("note");
         * 
         * deps.insert(deployment);
         */
    }

    /**
     * When a CatalogEvent.SkillDeleted event arrives, the workflow
     * service deletes the corresponding workflow deployment entity.
     * 
     * @param event
     */
    private void onSkillDeleted(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID skill_id = UUID.fromString(job.getString("id"));
         * 
         * Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);
         * WorkflowDeployment deployment = result.get();
         * 
         * deps.delete(deployment.id);
         */
    }

    /**
     * When a CatalogEvent.SkillUpdated event arrives, the workflow
     * service updates the corresponding workflow deployment entity.
     * 
     * @param event
     */
    private void onSkillUpdated(CatalogEvent event) {
        /*
         * JsonObject job = event.data();
         * UUID skill_id = UUID.fromString(job.getString("id"));
         * 
         * Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);
         * WorkflowDeployment deployment = result.get();
         * deployment.state = job.getString("state");
         * deployment.title = job.getString("title");
         * deployment.note = job.getString("note");
         * 
         * deps.update(deployment);
         */
    }

    /**
     * When a MemberEvent event arrives, this method dispatches it
     * to the correct handler based on the event type.
     * 
     * @param event
     */
    private void onMemberEvent(MemberEvent event) {

        log.info("member event = {}", event);

        switch (event.type()) {

            /*
            case MemberEvent.MEMBER_ACHIEVEMENT_CREATED:
                onAchievementCreated(event);
                break;Achievement
            case MemberEvent.MEMBER_ACHIEVEMENT_DELETED:
                onAchievementDeleted(event);
                break;
            case MemberEvent.MEMBER_ACHIEVEMENT_UPDATED:
                onAchievementUpdated(event);
                break;

            case MemberEvent.MEMBER_USER_CREATED:
                onUserCreated(event);
                break;
            case MemberEvent.MEMBER_USER_DELETED:
                onUserDeleted(event);
                break;
            case MemberEvent.MEMBER_USER_UPDATED:
                onUserUpdated(event);
                break;

            default:
                break;
            */
        }
    }
}
