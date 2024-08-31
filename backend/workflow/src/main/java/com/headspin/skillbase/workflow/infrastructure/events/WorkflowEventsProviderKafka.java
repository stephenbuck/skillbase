package com.headspin.skillbase.workflow.infrastructure.events;

import java.net.InetAddress;
import java.net.URI;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.headspin.skillbase.common.events.EventListener;
import com.headspin.skillbase.common.events.WorkflowEvent;
import com.headspin.skillbase.common.providers.CommonEventsProvider;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.message.Encoding;
import io.cloudevents.jackson.JsonCloudEventData;
import io.cloudevents.jackson.JsonFormat;
import io.cloudevents.kafka.CloudEventSerializer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka implementation of the workflow events provider interface.
 * 
 * @author Stephen Buck
 * @since 1.0
 */

@Slf4j
@ApplicationScoped
public class WorkflowEventsProviderKafka implements CommonEventsProvider {

    private static final Duration poll_timeout = Duration.ofMillis(500);
    private static final String acks_config = "all";
    private static final String consumer_group = "skillbase";
    private static final String bootstrap_servers = "kafka:9092";
    private static final String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String val_serializer = "io.cloudevents.kafka.CloudEventSerializer";
    private static final String key_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String val_deserializer = "io.cloudevents.kafka.CloudEventDeserializer";
    private static final String auto_offset_reset = "latest";
    private static final String enable_auto_commit = "true";

    private final String client_id;
    private final String group_id;

    private final Properties admnConfig;
    private final Properties prodConfig;
    private final Properties consConfig;

    private Thread thread;

    public WorkflowEventsProviderKafka() {

        // Configure the IDs
        this.client_id = String.valueOf(InetAddress.getLoopbackAddress()); // .getLocalHost().getHostName();
        this.group_id = consumer_group;

        // Configure the admin
        this.admnConfig = new Properties();
        this.admnConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);

        // Configure the producer
        this.prodConfig = new Properties();
        this.prodConfig.put(ProducerConfig.ACKS_CONFIG, acks_config);
        this.prodConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        this.prodConfig.put(ProducerConfig.CLIENT_ID_CONFIG, client_id);
        this.prodConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key_serializer);
        this.prodConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, val_serializer);

        // Configure the CloudEvents
        this.prodConfig.put(CloudEventSerializer.ENCODING_CONFIG, Encoding.STRUCTURED);
        this.prodConfig.put(CloudEventSerializer.EVENT_FORMAT_CONFIG, JsonFormat.CONTENT_TYPE);

        // Configure the consumer
        this.consConfig = new Properties();
        this.consConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        this.consConfig.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
        this.consConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key_deserializer);
        this.consConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, val_deserializer);
        this.consConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset);
        this.consConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enable_auto_commit);
    }

    /**
     * Produces an event with the specified topic, type, and JSON data by
     * posting it to the configured Kafka broker.
     * 
     * @param topic
     * @param type
     * @param json
     */

    @Override
    @Transactional
    public void produce(@NotNull final String topic, @NotNull final String type, @NotNull final JsonObject json) {

        // Wrap the json data as CloudEvent data
        final JsonCloudEventData data = JsonCloudEventData
                .wrap(new ObjectMapper().valueToTree(json));

        // Create a CloudEvent object
        final CloudEvent event = CloudEventBuilder.v1()
                .withSource(URI.create(WorkflowEvent.SKILLBASE_EVENT_SOURCE))
                .withType(type)
                .withId(String.valueOf(UUID.randomUUID()))
                .withTime(ZonedDateTime.now().toOffsetDateTime())
                .withData(MediaType.APPLICATION_JSON, data)
                .build();

        // Create the KafkaProducer and send the event
        try (KafkaProducer<String, CloudEvent> producer = new KafkaProducer<>(prodConfig)) {
            producer.send(new ProducerRecord<>(topic, event));
        }
    }

    /**
     * Starts consuming events with any of the the specified topics from
     * the configured Kafka broker and sends them to the specified listener.
     * 
     * @param topics
     * @param listener
     */
    
    @Override
    public void consume(@NotNull final Collection<String> topics, @NotNull final EventListener listener) {

        this.thread = new Thread(new Runnable() {

            public void run() {

                // Create the KafkaConsumer
                try (KafkaConsumer<String, CloudEvent> consumer = new KafkaConsumer<>(consConfig)) {

                    // Subscribe to the topics we're interested in
                    consumer.subscribe(topics);

                    // Consume events and send them to the listener
                    while (true) {
                        final ConsumerRecords<String, CloudEvent> records = consumer.poll(poll_timeout);
                        for (final ConsumerRecord<String, CloudEvent> record : records) {
                            listener.onCloudEvent(record.topic(), record.value());
                        }
                    }
                } catch (final Exception e) {
                    log.info(String.valueOf(e));
                }
            }

        });
        this.thread.start();
    }

    @Override
    public void test() {
        log.info("test:");
        produce(
                WorkflowEvent.WORKFLOW_EVENT_TOPIC,
                WorkflowEvent.WORKFLOW_DEPLOYMENT_DELETED,
                Json.createObjectBuilder()
                        .add("deployment_id", String.valueOf(UUID.randomUUID()))
                        .build());
    }

    /*
     * private static final int num_partitions = 1;
     * private static final short replication_factor = 1;
     * private void check() {
     * try (Admin admin = Admin.create(admnConfig)) {
     * ListTopicsResult listres = admin.listTopics();
     * if (!listres.names().get().contains(WorkflowEvent.WORKFLOW_EVENT_TOPIC)) {
     * NewTopic topic = new NewTopic(WorkflowEvent.WORKFLOW_EVENT_TOPIC,
     * num_partitions,
     * replication_factor);
     * Set<NewTopic> topics = Collections.singleton(topic);
     * CreateTopicsResult result = admin.createTopics(topics);
     * KafkaFuture<Void> future =
     * result.values().get(WorkflowEvent.WORKFLOW_EVENT_TOPIC);
     * future.get();
     * }
     * }
     * }
     */
}
