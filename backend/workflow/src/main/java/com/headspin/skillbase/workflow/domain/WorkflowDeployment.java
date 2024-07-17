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
 * Representation of a workflow deployment entity.
 * 
 * Note that the 'peer_id' field contains a Flowable 'deployment_id'.
 *
 * @author Stephen Buck
 * @since 1.0
 */

@Entity
@Table(schema = "workflow", name = "deployment")
public class WorkflowDeployment extends DomainEntity {

        @JsonbProperty("id")
        @Column(name = "id") @NotNull @Id @GeneratedValue(strategy = GenerationType.UUID) public UUID id;

        @JsonbProperty("peer_id")
        @Column(name = "peer_id") public String peer_id;

        @JsonbProperty("skill_id")
        @Column(name = "skill_id") public UUID skill_id;
        
        @JsonbProperty("state")
        @Column(name = "state") @NotNull public String state;

        @JsonbProperty("title")
        @Column(name = "title") @NotNull @NotBlank public String title;

        @JsonbProperty("note")
        @Column(name = "note") @NotNull public String note;

        @JsonbProperty("created_at")
        @Column(name = "created_at") @NotNull @Temporal(TemporalType.TIMESTAMP) public Date createdAt;

        @JsonbProperty("updated_at")
        @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) public Date updatedAt;

        @Override
        public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("WorkflowDeployment {\n}");
                stringBuilder.append("    id            = " + id + "\n");
                stringBuilder.append("    peer_id       = " + peer_id + "\n");
                stringBuilder.append("    skill_id      = " + skill_id + "\n");
                stringBuilder.append("    state         = " + state + "\n");
                stringBuilder.append("    title         = " + title + "\n");
                stringBuilder.append("    note          = " + note + "\n");
                stringBuilder.append("    createdAt     = " + createdAt + "\n");
                stringBuilder.append("    updatedAt     = " + updatedAt + "\n");
                stringBuilder.append("}\n");
                return stringBuilder.toString();
        }
}
