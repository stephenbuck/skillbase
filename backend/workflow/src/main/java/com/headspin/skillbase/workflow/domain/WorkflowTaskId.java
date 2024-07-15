package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class WorkflowTaskId implements Serializable {
    public UUID uuid;
}
