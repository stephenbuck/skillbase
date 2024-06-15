package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class WorkflowId implements org.jmolecules.ddd.types.Identifier, Serializable {
    public UUID uuid;
}