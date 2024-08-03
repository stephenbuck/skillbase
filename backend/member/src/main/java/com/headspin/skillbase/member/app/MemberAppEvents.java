package com.headspin.skillbase.member.app;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.headspin.skillbase.common.app.AppEvents;
import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.member.infrastructure.events.MemberEventsProviderKafka;
import com.headspin.skillbase.member.providers.MemberEventsProvider;

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

    @Inject
    private MemberEventsProvider evnt; //  = new MemberConsumerProviderKafka();

    private List<String> topics = Arrays.asList(
        CatalogEvent.CATALOG_EVENT_TOPIC,
        WorkflowEvent.WORKFLOW_EVENT_TOPIC
    );

    public MemberAppEvents() {
        try {
            this.evnt = new MemberEventsProviderKafka();
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.info("evnt = {}", evnt);
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
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
                case WorkflowEvent.WORKFLOW_EVENT_TOPIC:
                    onWorkflowEvent(new WorkflowEvent(eventId, eventType, eventJson));
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
    private void onWorkflowEvent(WorkflowEvent event) {

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
                onInstanceCreated(event);
                break;
            case WorkflowEvent.WORKFLOW_INSTANCE_DELETED:
                onInstanceDeleted(event);
                break;
            case WorkflowEvent.WORKFLOW_INSTANCE_UPDATED:
                onInstanceUpdated(event);
                break;

            default:
                break;
        }       
    }

    private void onInstanceCreated(WorkflowEvent event) {
    /*
        MemberProcess process = new MemberProcess();
        process.state = event?.state;
        process.title = event?.title;
        process.note = event?.note;
        insertProcess(process);
    */
    }

    private void onInstanceDeleted(WorkflowEvent event) {
    /*
        MemberProcess process = findByInstanceId(event?.instance_id);
        deleteProcess(process.id);
    */
    }

    private void onInstanceUpdated(WorkflowEvent event) {
    /*
        MemberProcess process = findByInstanceId(event?.instance_id);
        process.state = event?.state;
        updateProcess(process);
    */
    }

    /*
    private void onInstancePassed(WorkflowEvent event) {
        
        MemberProcess process = findByInstanceId(event?.instance_id);
        process.state = event?.state;
        updateProcess(process);

        MemberAchievement achievement = new MemberAchievement();
        achievement.skill_id = event?.skill_id;
        achievement.credential_id = event?.credential_id;
        achievement.title = event?.title;
        achievement.note = event?.note;
        insertAchievement(achievement);
    }
    */

    /*
    private void onInstanceFailed(WorkflowEvent event) {
        // TBD
    }
    */    
}
