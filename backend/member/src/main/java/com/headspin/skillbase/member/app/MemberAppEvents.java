package com.headspin.skillbase.member.app;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.headspin.skillbase.common.app.AppEvents;
import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.common.providers.CommonEventsProvider;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * Application events for the member service.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class MemberAppEvents extends AppEvents {

    private final List<String> topics = Arrays.asList(
            CatalogEvent.CATALOG_EVENT_TOPIC,
            WorkflowEvent.WORKFLOW_EVENT_TOPIC);

    @Inject
    public MemberAppEvents(
        final CommonEventsProvider evnt
    ) {
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
    public void onCloudEvent(final String topic, final CloudEvent event) {

        log.info("cloud event = {}", event);

        final CloudEventData data = event.getData();

        final ByteArrayInputStream stream = new ByteArrayInputStream(data.toBytes());

        final JsonParser parser = Json.createParser(stream);

        if (parser.hasNext()) {

            parser.next();

            final UUID eventId = UUID.fromString(event.getId());
            final String eventType = event.getType();
            final JsonObject eventJson = parser.getObject();

            switch (topic) {
                case CatalogEvent.CATALOG_EVENT_TOPIC:
                    onCatalogEvent(new CatalogEvent(eventId, eventType, eventJson), eventJson);
                    break;
                case WorkflowEvent.WORKFLOW_EVENT_TOPIC:
                    onWorkflowEvent(new WorkflowEvent(eventId, eventType, eventJson), eventJson);
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
    private void onCatalogEvent(final CatalogEvent event, final JsonObject json) {

        log.info("catalog event = {}", event);

        switch (event.type()) {
            case CatalogEvent.CATALOG_CREDENTIAL_CREATED:
                break;
            case CatalogEvent.CATALOG_CREDENTIAL_DELETED:
                break;
            case CatalogEvent.CATALOG_CREDENTIAL_UPDATED:
                break;
            case CatalogEvent.CATALOG_SKILL_CREATED:
                break;
            case CatalogEvent.CATALOG_SKILL_DELETED:
                break;
            case CatalogEvent.CATALOG_SKILL_UPDATED:
                break;
            default:
                break;
        }
    }

    /**
     * When a WorkflowEvent event arrives, this method dispatches it
     * to the correct handler based on the event type.
     * 
     * @param event
     */
    private void onWorkflowEvent(final WorkflowEvent event, final JsonObject json) {

        log.info("workflow event = {}", event);

        switch (event.type()) {
            case WorkflowEvent.WORKFLOW_DEPLOYMENT_CREATED:
                break;
            case WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED:
                break;
            case WorkflowEvent.WORKFLOW_DEPLOYMENT_UPDATED:
                break;
            case WorkflowEvent.WORKFLOW_DEFINITION_CREATED:
                break;
            case WorkflowEvent.WORKFLOW_DEFINITION_DELETED:
                break;
            case WorkflowEvent.WORKFLOW_DEFINITION_UPDATED:
                break;
            case WorkflowEvent.WORKFLOW_INSTANCE_CREATED:
                onInstanceCreated(event, json);
                break;
            case WorkflowEvent.WORKFLOW_INSTANCE_DELETED:
                onInstanceDeleted(event, json);
                break;
            case WorkflowEvent.WORKFLOW_INSTANCE_UPDATED:
                onInstanceUpdated(event, json);
                break;

            default:
                break;
        }
    }

    private void onInstanceCreated(final WorkflowEvent event, final JsonObject json) {
        /*
         * MemberProcess process = new MemberProcess();
         * process.state = event?.state;
         * process.title = event?.title;
         * process.note = event?.note;
         * insertProcess(process);
         */
    }

    private void onInstanceDeleted(final WorkflowEvent event, final JsonObject json) {
        /*
         * MemberProcess process = findByInstanceId(event?.instance_id);
         * deleteProcess(process.process_id);
         */
    }

    private void onInstanceUpdated(final WorkflowEvent event, final JsonObject json) {
        /*
         * MemberProcess process = findByInstanceId(event?.instance_id);
         * process.state = event?.state;
         * updateProcess(process);
         */
    }

    /*
     * private void onInstancePassed(WorkflowEvent event, JsonObject json) {
     * 
     * MemberProcess process = findByInstanceId(event?.instance_id);
     * process.state = event?.state;
     * updateProcess(process);
     * 
     * MemberAchievement achievement = new MemberAchievement();
     * achievement.skill_id = event?.skill_id;
     * achievement.credential_id = event?.credential_id;
     * achievement.title = event?.title;
     * achievement.note = event?.note;
     * insertAchievement(achievement);
     * }
     */

    /*
     * private void onInstanceFailed(WorkflowEvent event, JsonObject json) {
     * // TBD
     * }
     */
}
