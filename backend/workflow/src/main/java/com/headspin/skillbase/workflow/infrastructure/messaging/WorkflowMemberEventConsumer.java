package com.headspin.skillbase.workflow.infrastructure.messaging;

import com.headspin.skillbase.common.events.MemberEvent;

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
    name = "WorkflowMemberEventConsumer",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = MemberEvent.MEMBER_EVENT_TOPIC),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue="jakarta.jms.Topic")
    }
)
public class WorkflowMemberEventConsumer implements MessageListener {
    
    @Resource
    private MessageDrivenContext context;
    
    public void onMessage(Message message) {  
        log.info("onMessage({})", message);
        try {
            onMemberEvent((MemberEvent) message.getBody(MemberEvent.class));
        }
        catch (JMSException e) {
            log.info(String.valueOf(e));
        }
    }

    private void onMemberEvent(MemberEvent event) {
        switch (event.type()) {

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
        }       
    }

    private void onUserCreated(MemberEvent event) {
    /*
        WorkflowDefinition definition = new WorkflowDefinition();
        definition.credential_id = event?.credential_id;
        definition.title = event?.title;
        definition.note = event?.note;
        insertDefinition(definition);
    */
    }

    private void onUserDeleted(MemberEvent event) {
        /*
        WorkflowDefinition definition = findByUserId(event?.credential_id);
        deleteDefinition(definition.id);
        */
    }

    private void onUserUpdated(MemberEvent event) {
    /*
        WorkflowDefinition definition = findByUserId(event?.credential_id);
        definition.title = event?.title;
        definition.note = event?.note;
        updateDefinition(definition);
    */
    }
}
