package com.headspin.skillbase.workflow.infrastructure.messaging;

import java.util.Optional;
import java.util.UUID;

import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.inject.Inject;
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

    @Inject
    private WorkflowDeploymentsService deps;

    @Inject
    private WorkflowDefinitionsService defs;

    @Inject
    private WorkflowInstancesService inst;
    

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

    /**
     * When a CatalogEvent.CredentialCreated event arrives, the workflow
     * service creates a corresponding workflow definition entity.
     * 
     * @param event
     */
    private void onCredentialCreated(CatalogEvent event) {
        WorkflowDefinition definition = new WorkflowDefinition();
        definition.deployment_id = UUID.randomUUID();
        definition.credential_id = UUID.randomUUID();
        definition.title = "";
        definition.note = "";
        defs.insert(definition);
    }

    private void onCredentialDeleted(CatalogEvent event) {
        Optional<WorkflowDefinition> result = defs.findByCredentialId(UUID.randomUUID());
        WorkflowDefinition definition = result.get();
        defs.delete(definition.id);
    }

    private void onCredentialUpdated(CatalogEvent event) {
        Optional<WorkflowDefinition> result = defs.findByCredentialId(UUID.randomUUID());
        WorkflowDefinition definition = result.get();
        definition.title = "";
        definition.note = "";
        defs.update(definition);
    }


    /**
     * When a CatalogEvent.SkillCreated event arrives, the workflow
     * service creates a corresponding workflow deployment entity.
     * 
     * @param event
     */
    private void onSkillCreated(CatalogEvent event) {
        WorkflowDeployment deployment = new WorkflowDeployment();
        deployment.skill_id = UUID.randomUUID();
        deployment.title = "";
        deployment.note = "";
        deps.insert(deployment);
    }

    private void onSkillDeleted(CatalogEvent event) {
        Optional<WorkflowDeployment> result = deps.findBySkillId(UUID.randomUUID());
        WorkflowDeployment deployment = result.get();
        deps.delete(deployment.id);
    }

    private void onSkillUpdated(CatalogEvent event) {
        Optional<WorkflowDeployment> result = deps.findBySkillId(UUID.randomUUID());
        WorkflowDeployment deployment = result.get();
        deployment.title = "";
        deployment.note = "";
        deps.update(deployment);
    }
}
