package com.headspin.skillbase.skill.domain;

import org.jmolecules.ddd.types.AggregateRoot;

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
@Table(name = "skill")
public record Skill (

    @Column(name = "id") @EmbeddedId @Id @NotNull SkillId id,

    @Column(name = "category_id") @ManyToOne @NotNull CategoryId categoryId,

    @Column(name = "workflow_id") @NotNull String workflowId,

    @Column(name = "title") @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "valid_for") int validFor,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements AggregateRoot<Skill, SkillId>, Serializable {
    public SkillId getId() { return id; }
}
