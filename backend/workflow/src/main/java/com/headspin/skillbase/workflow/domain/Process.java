package com.headspin.skillbase.workflow.domain;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "process")
public record Process (

    @Column(name = "id") @EmbeddedId @Id @NotNull ProcessId id,

    @Column(name = "peer_id") String peerId,

    @Column(name = "workflow_id") @ManyToOne @NotNull WorkflowId workflowId,

    @Column(name = "title") @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements org.jmolecules.ddd.types.Entity<Workflow, ProcessId>, Serializable {
    public ProcessId getId() { return id; }
}