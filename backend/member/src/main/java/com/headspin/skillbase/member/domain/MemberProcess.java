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
import lombok.Builder;

@Entity
@Builder
@Cacheable
@Table(schema = "member", name = "process")
public class MemberProcess extends DomainEntity {

    @JsonbProperty("process_id")
    @Column(name = "process_id")
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID process_id;

    @JsonbProperty("peer_id")
    @Column(name = "peer_id")
    @NotNull
    public String peer_id;

    @JsonbProperty("user_id")
    @Column(name = "user_id")
    @NotNull
    public UUID user_id;

    @JsonbProperty("state")
    @Column(name = "state")
    public String state;

    @JsonbProperty("title")
    @Column(name = "title")
    @NotNull
    @NotBlank
    public String title;

    @JsonbProperty("note")
    @Column(name = "note")
    @NotNull
    public String note;

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
                .append("MemberProcess {\n")
                .append("    process_id = " + process_id + "\n")
                .append("    peer_id    = " + peer_id + "\n")
                .append("    user_id    = " + user_id + "\n")
                .append("    state      = " + state + "\n")
                .append("    title      = " + title + "\n")
                .append("    note       = " + note + "\n")
                .append("    created_at = " + created_at + "\n")
                .append("    updated_at = " + updated_at + "\n")
                .append("    version    = " + version + "\n")
                .append("}\n")
                .toString();
    }

    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static MemberProcess fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, MemberProcess.class);
    }

    public static String toJson(final MemberProcess process) throws Exception {
        return JsonbBuilder.create().toJson(process);
    }
}
