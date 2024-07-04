package com.headspin.skillbase.certify.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "certify_cert")
public class CertifyCert implements Serializable {

        @JsonbProperty("id")
        @Column(name = "id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID id;

        @JsonbProperty("peer_id")
        @Column(name = "peer_id") @Null public String peerId;

        @JsonbProperty("model_id")
        @Column(name = "model_id") @NotNull @OneToOne public UUID modelId;

        @JsonbProperty("state")
        @Column(name = "state") @NotNull @NotBlank public String state;

        @JsonbProperty("title")
        @Column(name = "title") @NotNull @NotBlank public String title;

        @JsonbProperty("none")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("granted_at")
        @Column(name = "granted_at") @Null @Temporal(TemporalType.TIMESTAMP) public Date grantedAt;

        @JsonbProperty("revoked_at")
        @Column(name = "revoked_at") @Null @Temporal(TemporalType.TIMESTAMP) public Date revokedAt;

        @JsonbProperty("expires_at")
        @Column(name = "expires_at") @Null @Temporal(TemporalType.TIMESTAMP) public Date expiresAt;

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date createdAt;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @Null @Temporal(TemporalType.TIMESTAMP) public Date updatedAt;

}
