package com.headspin.skillbase.workflow.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class WorkflowDefinitionId implements Serializable {
    public UUID uuid;
}
