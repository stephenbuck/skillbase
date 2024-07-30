package com.headspin.skillbase.workflow.infrastructure.messaging;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.headspin.skillbase.common.events.CatalogEvent;
import com.headspin.skillbase.workflow.domain.WorkflowDefinition;
import com.headspin.skillbase.workflow.domain.WorkflowDeployment;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDefinitionsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowDeploymentsService;
import com.headspin.skillbase.workflow.interfaces.service.WorkflowInstancesService;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka implementation of the workflow catalog event consumer.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowCatalogEventConsumer {
    
    private static final Duration poll_timeout = Duration.ofMillis(100);

    private final String group_id = "skillbase";
    private final String bootstrap_servers = "kafka:9092";
    private final String key_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private final String val_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";

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
            config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
            config.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
            config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key_deserializer);
            config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, val_deserializer);
            config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(config);
            consumer.subscribe(Collections.singleton(CatalogEvent.CATALOG_EVENT_TOPIC));
            try {
                while (true) {
                    final ConsumerRecords<String, CloudEvent> consumerRecords = consumer.poll(poll_timeout);
                    for (final ConsumerRecord<String, CloudEvent> consumerRecord : consumerRecords) {
                        CloudEvent cloudEvent = consumerRecord.value();
                        CloudEventData ced = cloudEvent.getData();
                        JsonParser jp = Json.createParser(new ByteArrayInputStream(ced.toBytes()));
                        JsonObject jo = jp.getObject();
                        CatalogEvent catalogEvent = new CatalogEvent(
                            UUID.fromString(cloudEvent.getId()),
                            cloudEvent.getType(),
                            jo);
                        onCatalogEvent(catalogEvent);
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
            log.info("onCredentialCreated:");
            JsonObject job = event.data();
            UUID credential_id = UUID.fromString(job.getString("credential_id"));

            WorkflowDefinition definition = new WorkflowDefinition();
            definition.credential_id = credential_id;
            definition.deployment_id = UUID.fromString(job.getString("deployment_id"));
            definition.title = job.getString("title");
            definition.note = job.getString("note");
            defs.insert(definition);
        }

        private void onCredentialDeleted(CatalogEvent event) {
            log.info("onCredentialDeleted:");
            JsonObject job = event.data();
            UUID credential_id = UUID.fromString(job.getString("credential_id"));

            Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);
            WorkflowDefinition definition = result.get();
            defs.delete(definition.id);
        }

        private void onCredentialUpdated(CatalogEvent event) {
            log.info("onCredentialUpdated:");
            JsonObject job = event.data();
            UUID credential_id = UUID.fromString(job.getString("credential_id"));

            Optional<WorkflowDefinition> result = defs.findByCredentialId(credential_id);
            WorkflowDefinition definition = result.get();
            definition.credential_id = credential_id;
            definition.deployment_id = UUID.fromString(job.getString("deployment_id"));
            definition.title = job.getString("title");
            definition.note = job.getString("note");
            defs.update(definition);
        }


        /**
         * When a CatalogEvent.SkillCreated event arrives, the workflow
         * service creates a corresponding workflow deployment entity.
         * 
         * @param event
         */
        private void onSkillCreated(CatalogEvent event) {
            log.info("onSkillCreated:");
            JsonObject job = event.data();
            UUID skill_id = UUID.fromString(job.getString("id"));

            WorkflowDeployment deployment = new WorkflowDeployment();
            deployment.skill_id = skill_id;
            deployment.state = job.getString("state");
            deployment.title = job.getString("title");
            deployment.note = job.getString("note");
            deps.insert(deployment);
        }

        private void onSkillDeleted(CatalogEvent event) {
            log.info("onSkillDelete:");
            JsonObject job = event.data();
            UUID skill_id = UUID.fromString(job.getString("id"));

            Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);
            WorkflowDeployment deployment = result.get();
            deps.delete(deployment.id);
        }

        private void onSkillUpdated(CatalogEvent event) {
            log.info("onSkillUpdated:");
            JsonObject job = event.data();
            UUID skill_id = UUID.fromString(job.getString("id"));

            Optional<WorkflowDeployment> result = deps.findBySkillId(skill_id);
            WorkflowDeployment deployment = result.get();
            deployment.state = job.getString("state");
            deployment.title = job.getString("title");
            deployment.note = job.getString("note");
            deps.update(deployment);
        }
    }
}
