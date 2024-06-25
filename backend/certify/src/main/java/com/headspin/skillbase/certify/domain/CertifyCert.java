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
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "certify_cert")
public record CertifyCert(

        @JsonbProperty("id")
        @Column(name = "id")
        @NotNull @EmbeddedId @Id @GeneratedValue(strategy = GenerationType.IDENTITY) UUID id,

        @JsonbProperty("peer_id")
        @Column(name = "peer_id")
        @Null String peerId,

        @JsonbProperty("model_id")
        @Column(name = "model_id")
        @NotNull @OneToOne UUID modelId,

        @JsonbProperty("state")
        @Column(name = "state")
        @NotNull @NotBlank String state,

        @JsonbProperty("title")
        @Column(name = "title")
        @NotNull @NotBlank String title,

        @JsonbProperty("none")
        @Column(name = "note")
        @NotNull String note,

        @JsonbProperty("granted_at")
        @Column(name = "granted_at")
        @Null @Temporal(TemporalType.TIMESTAMP) Date grantedAt,

        @JsonbProperty("revoked_at")
        @Column(name = "revoked_at")
        @Null @Temporal(TemporalType.TIMESTAMP) Date revokedAt,

        @JsonbProperty("expires_at")
        @Column(name = "expires_at") 
        @Null @Temporal(TemporalType.TIMESTAMP) Date expiresAt,

        @JsonbProperty("inserted_at")
        @Column(name = "inserted_at")
        @NotNull @Temporal(TemporalType.TIMESTAMP) Date insertedAt,

        @JsonbProperty("updated_at")
        @Column(name = "updated_at")
        @Null @Temporal(TemporalType.TIMESTAMP) Date updatedAt,

        @JsonbProperty("version")
        @Column(name = "version", columnDefinition = "integer DEFAULT 0")
        @NotNull @Version Long version

) implements Serializable {
}
