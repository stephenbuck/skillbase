package com.headspin.skillbase.workflow.domain;

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

/**
 * Representation of a workflow instance entity.
 * 
 * Note that the 'peer_id' field contains a Flowable 'process_instance_id'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Cacheable
@Table(schema = "workflow", name = "instance")
public class WorkflowInstance extends DomainEntity {

    @JsonbProperty("instance_id")
    @Column(name = "instance_id")
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID instance_id;

    @JsonbProperty("peer_id")
    @Column(name = "peer_id")
    public String peer_id;

    @JsonbProperty("definition_id")
    @Column(name = "definition_id")
    @NotNull
    public UUID definition_id;

    @JsonbProperty("user_id")
    @Column(name = "user_id")
    @NotNull
    public UUID user_id;

    @JsonbProperty("is_test")
    @Column(name = "is_test")
    @NotNull
    public boolean is_test;

    @JsonbProperty("state")
    @Column(name = "state")
    @NotNull
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
                .append("WorkflowInstance {\n")
                .append("    instance_id   = " + instance_id + "\n")
                .append("    peer_id       = " + peer_id + "\n")
                .append("    definition_id = " + definition_id + "\n")
                .append("    user_id       = " + user_id + "\n")
                .append("    is_test       = " + is_test + "\n")
                .append("    state         = " + state + "\n")
                .append("    title         = " + title + "\n")
                .append("    note          = " + note + "\n")
                .append("    created_at    = " + created_at + "\n")
                .append("    updated_at    = " + updated_at + "\n")
                .append("    version       = " + version + "\n")
                .append("}\n")
                .toString();
    }

    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static WorkflowInstance fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, WorkflowInstance.class);
    }

    public static String toJson(final WorkflowInstance instance) throws Exception {
        return JsonbBuilder.create().toJson(instance);
    }
}
