package com.headspin.groupbase.workflow.domain;

import java.io.Serializable;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class WorkflowDocumentId implements Serializable {
    public UUID uuid;
}
