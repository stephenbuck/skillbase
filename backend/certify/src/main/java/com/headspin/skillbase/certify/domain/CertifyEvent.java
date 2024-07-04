package com.headspin.skillbase.certify.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

public class CertifyEvent implements Serializable {

    private static URI source = URI.create("http://skillbase.com");

    public static final String CERTIFY_EVENT_CERT_INSERTED = "com.headspin.skillbase.certify.cert.inserted";
    public static final String CERTIFY_EVENT_CERT_DELETED = "com.headspin.skillbase.certify.cert.deleted";
    public static final String CERTIFY_EVENT_CERT_UPDATED = "com.headspin.skillbase.certify.cert.updated";

    public static final String CERTIFY_EVENT_DOCUMENT_INSERTED = "com.headspin.skillbase.certify.document.inserted";
    public static final String CERTIFY_EVENT_DOCUMENT_DELETED = "com.headspin.skillbase.certify.document.deleted";
    public static final String CERTIFY_EVENT_DOCUMENT_UPDATED = "com.headspin.skillbase.certify.document.updated";

    public static final String CERTIFY_EVENT_MODEL_INSERTED = "com.headspin.skillbase.certify.model.inserted";
    public static final String CERTIFY_EVENT_MODEL_DELETED = "com.headspin.skillbase.certify.model.deleted";
    public static final String CERTIFY_EVENT_MODEL_UPDATED = "com.headspin.skillbase.certify.model.updated";

    public static final String CERTIFY_EVENT_PROCESS_INSERTED = "com.headspin.skillbase.certify.process.inserted";
    public static final String CERTIFY_EVENT_PROCESS_DELETED = "com.headspin.skillbase.certify.process.deleted";
    public static final String CERTIFY_EVENT_PROCESS_UPDATED = "com.headspin.skillbase.certify.process.updated";

    public static final String CERTIFY_EVENT_TASK_INSERTED = "com.headspin.skillbase.certify.task.inserted";
    public static final String CERTIFY_EVENT_TASK_DELETED = "com.headspin.skillbase.certify.task.deleted";
    public static final String CERTIFY_EVENT_TASK_UPDATED = "com.headspin.skillbase.certify.task.updated";

    private UUID id;
    private String type;

    public CertifyEvent(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public UUID id() {
        return this.id;
    }

    public String type() {
        return this.type;
    }

    public static CertifyEvent buildEvent(UUID id, String type) {
        return new CertifyEvent(id, type);
    }

    public static CloudEvent buildCloud(CertifyEvent event) {
        return CloudEventBuilder
                .v1()
                .withId(String.valueOf(event.id()))
                .withType(event.type())
                .withSource(source)
                .build();
    }
}