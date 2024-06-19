package com.headspin.skillbase.cert.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "cert")
public record Cert(

        @Column(name = "id") @NotNull @EmbeddedId @Id UUID id,

        @Column(name = "peer_id") @Null String peerId,

        @Column(name = "model_id") @NotNull @OneToOne UUID modelId,

        @Column(name = "state") @NotNull @NotBlank String state,

        @Column(name = "title") @NotNull @NotBlank String title,

        @Column(name = "note") @NotNull String note,

        @Column(name = "granted_at") @Null @Temporal(TemporalType.TIMESTAMP) Date grantedAt,

        @Column(name = "revoked_at") @Null @Temporal(TemporalType.TIMESTAMP) Date revokedAt,

        @Column(name = "expires_at") @Null @Temporal(TemporalType.TIMESTAMP) Date expiresAt,

        @Column(name = "inserted_at") @NotNull @Temporal(TemporalType.TIMESTAMP) Date insertedAt,

        @Column(name = "updated_at") @Null @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {
}
