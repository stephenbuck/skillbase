package com.headspin.skillbase.workflow.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class WorkflowProcessId implements Serializable {
    public UUID uuid;
}
