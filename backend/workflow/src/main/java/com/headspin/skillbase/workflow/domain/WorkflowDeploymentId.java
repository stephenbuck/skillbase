package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class WorkflowDeploymentId implements Serializable {
    public UUID uuid;
}
