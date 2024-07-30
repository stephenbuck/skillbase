package com.headspin.skillbase.workflow.infrastructure.messaging;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.common.events.MemberEvent;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka implementation of the workflow member event consumer.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowMemberEventConsumer {
    
    private static final Duration poll_timeout = Duration.ofMillis(100);

    @Inject
    private WorkflowDeploymentsService deps;

    @Inject
    private WorkflowDefinitionsService defs;

    @Inject
    private WorkflowInstancesService inst;
    

    public WorkflowMemberEventConsumer() {
        ConsumerRunnable cr = new ConsumerRunnable();
        Thread thread = new Thread(cr);
        thread.start();
    }

    private static class ConsumerRunnable implements Runnable {

        @Override
        public void run() {
            Properties config = new Properties();
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
            consumer.subscribe(Collections.singleton(CatalogEvent.CATALOG_EVENT_TOPIC));
            try {
                while (true) {
                    final ConsumerRecords<String, String> consumerRecords = consumer.poll(poll_timeout);
                    for (final ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                        log.info("Getting consumer record key: '" + consumerRecord.key() + "', value: '" + consumerRecord.value() + "', partition: " + consumerRecord.partition() + " and offset: " + consumerRecord.offset() + " at " + new Date(consumerRecord.timestamp()));
                        onMemberEvent((MemberEvent)null);
                    }
                }
            }
            catch (Exception e) {
                log.info(String.valueOf(e));
            }
            finally {
                consumer.close();
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

        /**
         * When a MemberEvent.UserCreated event arrives, the workflow
         * service creates a corresponding workflow user entity.
         * 
         * @param event
         */
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
}
