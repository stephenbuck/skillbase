package com.headspin.skillbase.workflow.infrastructure.messaging;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkflowCatalogEventConsumer {
    
    private static final Duration poll_timeout = Duration.ofMillis(100);

    @Inject
    private WorkflowDeploymentsService deps;

    @Inject
    private WorkflowDefinitionsService defs;

    @Inject
    private WorkflowInstancesService inst;
    

    public WorkflowCatalogEventConsumer() {
        ConsumerRunnable cr = new ConsumerRunnable();
        Thread thread = new Thread(cr);
        thread.start();
    }

    private class ConsumerRunnable implements Runnable {

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
                        onCatalogEvent((CatalogEvent)null);
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
}
