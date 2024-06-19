package com.headspin.skillbase.skill.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "skill")
public record Skill (

    @Column(name = "id") @NotNull @EmbeddedId @Id UUID id,

    @Column(name = "category_id") @NotNull @ManyToOne UUID categoryId,

    @Column(name = "title") @NotNull @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "icon") @Null @Lob byte[] icon,

    @Column(name = "valid_for") @Null int validFor,

    @Column(name = "inserted_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date insertedAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) @Null Date updatedAt

) implements Serializable {}
