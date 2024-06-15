package com.headspin.skillbase.certificate.domain;

import org.jmolecules.ddd.types.AggregateRoot;

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
@Table(name = "certificate")
public record Certificate (

    @Column(name = "id") @EmbeddedId @Id @NotNull CertificateId id,

    @Column(name = "status_code", nullable = false) @NotBlank String statusCode,

    @Column(name = "title") @NotBlank String title,

    @Column(name = "note") @NotNull String note,

    @Column(name = "granted_at") @Temporal(TemporalType.TIMESTAMP) Date grantedAt,

    @Column(name = "revoked_at") @Temporal(TemporalType.TIMESTAMP) Date revokedAt,

    @Column(name = "expires_at") @Temporal(TemporalType.TIMESTAMP) Date expiresAt,

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) @NotNull Date createdAt,

    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements AggregateRoot<Certificate, CertificateId>, Serializable {
    public CertificateId getId() { return id; }
}
