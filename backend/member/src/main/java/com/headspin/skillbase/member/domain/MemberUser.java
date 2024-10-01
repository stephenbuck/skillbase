package com.headspin.skillbase.member.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEntity;

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Cacheable
@Table(schema = "member", name = "user")
public class MemberUser extends DomainEntity {

    @JsonbProperty("user_id")
    @Column(name = "user_id")
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID user_id;

    @JsonbProperty("peer_id")
    @Column(name = "peer_id")
    public String peer_id;

    @JsonbProperty("is_enabled")
    @Column(name = "is_enabled")
    @NotNull
    public boolean is_enabled;

    @JsonbProperty("user_name")
    @Column(name = "user_name")
    @NotNull
    @NotBlank
    public String user_name;

    @JsonbProperty("first_name")
    @Column(name = "first_name")
    @NotNull
    @NotBlank
    public String first_name;

    @JsonbProperty("last_name")
    @Column(name = "last_name")
    @NotNull
    @NotBlank
    public String last_name;

    @JsonbProperty("email")
    @Column(name = "email")
    @NotNull
    @NotBlank
    public String email;

    @JsonbProperty("phone")
    @Column(name = "phone")
    @NotNull
    @NotBlank
    public String phone;

    @JsonbProperty("note")
    @Column(name = "note")
    @NotNull
    public String note;

    @JsonbProperty("image_id")
    @Column(name = "image_id")
    public String image_id;

    @JsonbProperty("created_at")
    @Column(name = "created_at")
    @NotNull
    public Date created_at;

    @JsonbProperty("updated_at")
    @Column(name = "updated_at")
    @NotNull
    public Date updated_at;

    @JsonbProperty("version")
    @Column(name = "version")
    @NotNull
    @Version
    public Integer version;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MemberUser {\n")
                .append("    user_id    = " + user_id + "\n")
                .append("    peer_id    = " + peer_id + "\n")
                .append("    is_enabled = " + is_enabled + "\n")
                .append("    user_name  = " + user_name + "\n")
                .append("    first_name = " + first_name + "\n")
                .append("    last_name  = " + last_name + "\n")
                .append("    email      = " + email + "\n")
                .append("    phone      = " + phone + "\n")
                .append("    note       = " + note + "\n")
                .append("    image_id   = " + image_id + "\n")
                .append("    created_at = " + created_at + "\n")
                .append("    updated_at = " + updated_at + "\n")
                .append("    version    = " + version + "\n")
                .append("}\n")
                .toString();
    }

    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static MemberUser fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, MemberUser.class);
    }

    public static String toJson(final MemberUser user) throws Exception {
        return JsonbBuilder.create().toJson(user);
    }
}
