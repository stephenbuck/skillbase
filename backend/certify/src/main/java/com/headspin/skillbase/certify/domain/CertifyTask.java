package com.headspin.skillbase.certify.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "certify_task")
public record CertifyTask (

    @JsonbProperty("id")
    @Column(name = "id")
    @NotNull @EmbeddedId @Id @GeneratedValue(strategy = GenerationType.IDENTITY) UUID id,

    @JsonbProperty("peer_id")
    @Column(name = "peer_id")
    @Null String peerId,

    @JsonbProperty("process_id")
    @Column(name = "process_id")
    @NotNull @ManyToOne UUID processId,

    @JsonbProperty("title")
    @Column(name = "title")
    @NotNull @NotBlank String title,

    @JsonbProperty("note")
    @Column(name = "note")
    @NotNull String note,

    @JsonbProperty("inserted_at")
    @Column(name = "inserted_at")
    @NotNull @Temporal(TemporalType.TIMESTAMP) Date insertedAt,

    @JsonbProperty("updated_at")
    @Column(name = "updated_at")
    @Null @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {}
