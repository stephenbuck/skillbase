package com.headspin.skillbase.workflow.domain;

import java.util.Date;
import java.util.UUID;

import com.headspin.skillbase.common.domain.DomainEntity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date created_at;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date updated_at;

        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("WorkflowDefinition {\n");
                stringBuilder.append("    definition_id  = " + definition_id + "\n");
                stringBuilder.append("    peer_id        = " + peer_id + "\n");
                stringBuilder.append("    deployment_id  = " + deployment_id + "\n");
                stringBuilder.append("    credential_id  = " + credential_id + "\n");
                stringBuilder.append("    title          = " + title + "\n");
                stringBuilder.append("    note           = " + note + "\n");
                stringBuilder.append("    created_at     = " + created_at + "\n");
                stringBuilder.append("    updated_at     = " + updated_at + "\n");
                stringBuilder.append("}\n");
                return stringBuilder.toString();
        }
}
