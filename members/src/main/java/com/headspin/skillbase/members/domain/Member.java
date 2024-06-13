package com.headspin.skillbase.members.domain;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "member")
public record Member(

    @Column(name = "id") @Id @NotNull int id,

    @Column(name = "status_code", nullable = false) @NotBlank String statusCode,

    @Column(name = "first_name") @NotBlank String firstName,

    @Column(name = "last_name") @NotBlank String lastName,

    @Column(name = "email") @Email @NotNull String email,

    @Column(name = "note") @NotNull String note,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {
}
