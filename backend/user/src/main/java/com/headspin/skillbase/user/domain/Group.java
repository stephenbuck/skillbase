package com.headspin.skillbase.user.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "group")
public record Group (

    @Column(name = "id") @NotNull @EmbeddedId @Id UUID id,

    @Column(name = "title") @NotNull @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "icon") @Null @Lob byte[] icon,

    @Column(name = "inserted_at") @NotNull @Temporal(TemporalType.TIMESTAMP) Date insertedAt,

    @Column(name = "updated_at") @Null @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {}
