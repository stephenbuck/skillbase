package com.headspin.skillbase.workflow.infrastructure.messaging;

import com.headspin.skillbase.common.events.CatalogEvent;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MessageDriven(
    name = "WorkflowCatalogEventConsumer",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = CatalogEvent.CATALOG_EVENT_TOPIC),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue="jakarta.jms.Topic")
    }
)
public class WorkflowCatalogEventConsumer implements MessageListener {
    
    @Resource
    private MessageDrivenContext context;

    public void onMessage(Message message) {  
        log.info("onMessage({})", message);
        try {
            onCatalogEvent((CatalogEvent) message.getBody(CatalogEvent.class));
        }
        catch (JMSException e) {
            log.info(String.valueOf(e));
        }
    }

    private void onCatalogEvent(CatalogEvent event) {
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

    private void onCredentialCreated(CatalogEvent event) {
    /*
        WorkflowDefinition definition = new WorkflowDefinition();
        definition.credential_id = event?.credential_id;
        definition.title = event?.title;
        definition.note = event?.note;
        insertDefinition(definition);
    */
    }

    private void onCredentialDeleted(CatalogEvent event) {
        /*
        WorkflowDefinition definition = findByCredentialId(event?.credential_id);
        deleteDefinition(definition.id);
        */
    }

    private void onCredentialUpdated(CatalogEvent event) {
    /*
        WorkflowDefinition definition = findByCredentialId(event?.credential_id);
        definition.title = event?.title;
        definition.note = event?.note;
        updateDefinition(definition);
    */
    }


    private void onSkillCreated(CatalogEvent event) {
    /*
        WorkflowDeployment deployment = new WorkflowDeployment();
        deployment.skill_id = event?.skill_id;
        deployment.title = event?.title;
        deployment.note = event?.note;
        insertDeployment(deployment);
    */
    }

    private void onSkillDeleted(CatalogEvent event) {
    /*
        WorkflowDeployment deployment = findBySkillId(event?.skill_id);
        deleteDeployment(deployment.id);
    */
    }

    private void onSkillUpdated(CatalogEvent event) {
    /*
        WorkflowDeployment deployment = findBySkillId(event?.skill_id);
        deployment.title = event?.title;
        deployment.note = event?.note;
        updateDeployment(deployment);
    */
    }

    /*
    Properties props = new Properties();

    // Other config props
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CloudEventDeserializer.class);

    try (KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(props)) {

        ConsumerRecords<String, CloudEvent> records = consumer.poll(Duration.ofSeconds(10));

        records.forEach(rec -> {
            log.info(rec.value().toString());
        });
    }
    */

}
