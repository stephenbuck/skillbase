package com.headspin.skillbase.skill.domain;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public record Category(

    @Column(name = "id") @EmbeddedId @Id @NotNull CategoryId id,

    @Column(name = "title") @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements org.jmolecules.ddd.types.Entity<Skill, CategoryId>, Serializable {
    public CategoryId getId() { return id; }
}

