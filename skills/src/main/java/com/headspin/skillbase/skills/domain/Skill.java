package com.headspin.skillbase.skills.domain;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "skill")
public record Skill(

    @Column(name = "id") @Id @NotNull int id,

    @Column(name = "status", nullable = false) @NotBlank String status,

    @Column(name = "name") @NotBlank String name,

    @Column(name = "desc") @NotNull String desc,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date created_at,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updated_at

) implements Serializable {
}
