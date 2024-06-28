package com.headspin.skillbase.identity.domain;

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
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "identity_user")
public record IdentityUser(

        @JsonbProperty("id") @Column(name = "id") @NotNull @EmbeddedId @Id @GeneratedValue(strategy = GenerationType.IDENTITY) UUID id,

        @JsonbProperty("state") @Column(name = "state") @NotNull @NotBlank String state,

        @JsonbProperty("user_name") @Column(name = "user_name") @NotNull @NotBlank String userName,

        @JsonbProperty("first_name") @Column(name = "first_name") @NotNull @NotBlank String firstName,

        @JsonbProperty("last_name") @Column(name = "last_name") @NotNull @NotBlank String lastName,

        @JsonbProperty("email") @Column(name = "email") @NotNull @Email String email,

        @JsonbProperty("phone") @Column(name = "phone") @NotNull String phone,

        @JsonbProperty("note") @Column(name = "note") @NotNull String note,

        @JsonbProperty("icon") @Column(name = "icon") @Null @Lob byte[] icon,

        @JsonbProperty("inserted_at") @Column(name = "inserted_at") @NotNull @Temporal(TemporalType.TIMESTAMP) Date insertedAt,

        @JsonbProperty("updated_at") @Column(name = "updated_at") @Null @Temporal(TemporalType.TIMESTAMP) Date updatedAt

) implements Serializable {
}
