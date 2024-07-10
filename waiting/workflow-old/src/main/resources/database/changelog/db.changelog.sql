--liquibase formatted sql
--changeset groupbase_workflow:initial

CREATE SCHEMA workflow;

CREATE TABLE IF NOT EXISTS workflow.model (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NOT NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  bpmn                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_model_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS workflow.cert (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  model_id             UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  granted_at           TIMESTAMP       NULL DEFAULT NULL,
  revoked_at           TIMESTAMP       NULL DEFAULT NULL,
  expires_at           TIMESTAMP       NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_cert_pk PRIMARY KEY (id),
--  CONSTRAINT workflow_cert_fk_model FOREIGN KEY (model_id) REFERENCES workflow.model (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS workflow.process (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  cert_id              UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_process_pk PRIMARY KEY (id),
--  CONSTRAINT workflow_process_fk_cert FOREIGN KEY (cert_id) REFERENCES workflow.cert (id) ON DELETE CASCADE
);
-- CREATE INDEX IF NOT EXISTS workflow_process_by_cert_id ON workflow.cert (id);

CREATE TABLE IF NOT EXISTS workflow.task (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_task_pk PRIMARY KEY (id),
--  CONSTRAINT workflow_task_fk_process FOREIGN KEY (process_id) REFERENCES workflow.process (id) ON DELETE CASCADE
);
-- CREATE INDEX IF NOT EXISTS workflow_task_by_process_id ON workflow.process (id);

CREATE TABLE IF NOT EXISTS workflow.document (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_document_pk PRIMARY KEY (id),
--  CONSTRAINT workflow_document_fk_process FOREIGN KEY (process_id) REFERENCES workflow.process (id) ON DELETE CASCADE
);
-- CREATE INDEX IF NOT EXISTS workflow_document_by_process_id ON workflow.process (id);

CREATE TABLE IF NOT EXISTS workflow.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT workflow_outbox_pk PRIMARY KEY (id)
);

