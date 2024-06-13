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
@Table(name = "category")
public record Category(

    @Column(name = "id") @Id @NotNull int id,

    @Column(name = "title") @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {
}
