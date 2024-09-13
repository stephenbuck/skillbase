package com.headspin.skillbase.workflow.domain;

import java.time.LocalDateTime;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representation of a workflow definition entity.
 * 
 * Note that the 'peer_id' field contains a Flowable 'process_definition_id'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Cacheable
@Table(schema = "workflow", name = "definition")
public class WorkflowDefinition extends DomainEntity {

    @JsonbProperty("definition_id")
    @Column(name = "definition_id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID definition_id;

    @JsonbProperty("peer_id")
    @Column(name = "peer_id") public String peer_id;

    @JsonbProperty("deployment_id")
    @Column(name = "deployment_id") @NotNull public UUID deployment_id;

    @JsonbProperty("credential_id")
    @Column(name = "credential_id") @NotNull public UUID credential_id;

    @JsonbProperty("title")
    @Column(name = "title") @NotNull @NotBlank public String title;

    @JsonbProperty("note")
    @Column(name = "note") @NotNull public String note;

    @JsonbProperty("image_id")
    @Column(name = "image_id") public String image_id;

    @JsonbProperty("valid_for")
    @Column(name = "valid_for") @NotNull public Integer valid_for;

    @JsonbProperty("created_at")
    @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime created_at;

    @JsonbProperty("updated_at")
    @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public LocalDateTime updated_at;

    @JsonbProperty("version")
    @Column(name = "version") @NotNull @Version public Integer version;

    @Override
    public String toString() {
        return new StringBuilder()
            .append("WorkflowDefinition {\n")
            .append("    definition_id  = " + definition_id + "\n")
            .append("    peer_id        = " + peer_id + "\n")
            .append("    deployment_id  = " + deployment_id + "\n")
            .append("    credential_id  = " + credential_id + "\n")
            .append("    title          = " + title + "\n")
            .append("    note           = " + note + "\n")
            .append("    image_id       = " + image_id + "\n")
            .append("    valid_for      = " + valid_for + "\n")
            .append("    created_at     = " + created_at + "\n")
            .append("    updated_at     = " + updated_at + "\n")
            .append("    version        = " + version + "\n")
            .append("}\n")
            .toString();
    }
        
    public String toETag() {
        return String.valueOf(hashCode());
    }

    public static WorkflowDefinition fromJson(final String json) throws Exception {
        return JsonbBuilder.create().fromJson(json, WorkflowDefinition.class);
    }
    
    public static String toJson(final WorkflowDefinition definition) throws Exception {
        return JsonbBuilder.create().toJson(definition);
    }
}
