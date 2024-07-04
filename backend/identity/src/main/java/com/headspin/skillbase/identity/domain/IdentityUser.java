package com.headspin.skillbase.identity.domain;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "identity_user")
public class IdentityUser implements Serializable {

        @JsonbProperty("id")
        @Column(name = "id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID id;

        @JsonbProperty("peer_id")
        @Column(name = "peer_id") @Null public String peerId;

        @JsonbProperty("state")
        @Column(name = "state") @NotNull @NotBlank public String state;

        @JsonbProperty("user_name")
        @Column(name = "user_name") @NotNull @NotBlank public String userName;

        @JsonbProperty("first_name")
        @Column(name = "first_name") @NotNull @NotBlank public String firstName;

        @JsonbProperty("last_name")
        @Column(name = "last_name") @NotNull @NotBlank public String lastName;

        @JsonbProperty("email")
        @Column(name = "email") @NotNull @Email public String email;

        @JsonbProperty("phone")
        @Column(name = "phone") @NotNull public String phone;

        @JsonbProperty("note")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("icon")
        @Column(name = "icon") @Null public byte[] icon;

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date createdAt;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @Null @Temporal(TemporalType.TIMESTAMP) public Date updatedAt;

}
