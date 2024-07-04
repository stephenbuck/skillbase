--liquibase formatted sql
--changeset skillbase_certify:initial

DROP TABLE IF EXISTS certify_model;
DROP TABLE IF EXISTS certify_cert;
DROP TABLE IF EXISTS certify_process;
DROP TABLE IF EXISTS certify_task;
DROP TABLE IF EXISTS certify_document;
DROP TABLE IF EXISTS certify_outbox;

CREATE TABLE IF NOT EXISTS certify_model (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NOT NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  bpmn                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_model_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS certify_cert (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  model_id             UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  granted_at           TIMESTAMP       NULL DEFAULT NULL,
  revoked_at           TIMESTAMP       NULL DEFAULT NULL,
  expires_at           TIMESTAMP       NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_cert_pk PRIMARY KEY (id),
  CONSTRAINT certify_cert_fk_model FOREIGN KEY (model_id) REFERENCES certify_model (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS certify_process (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  cert_id              UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_process_pk PRIMARY KEY (id),
  CONSTRAINT certify_process_fk_cert FOREIGN KEY (cert_id) REFERENCES certify_cert (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS certify_process_by_cert_id ON certify_cert (id);

CREATE TABLE IF NOT EXISTS certify_task (
  id                   UUID        NOT NULL UNIQUE,
  peer_id              VARCHAR         NULL DEFAULT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_task_pk PRIMARY KEY (id),
  CONSTRAINT certify_task_fk_process FOREIGN KEY (process_id) REFERENCES certify_process (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS certify_task_by_process_id ON certify_process (id);

CREATE TABLE IF NOT EXISTS certify_document (
  id                   UUID        NOT NULL UNIQUE,
  peer_id              VARCHAR         NULL DEFAULT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_document_pk PRIMARY KEY (id),
  CONSTRAINT certify_document_fk_process FOREIGN KEY (process_id) REFERENCES certify_process (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS certify_document_by_process_id ON certify_process (id);

CREATE TABLE IF NOT EXISTS certify_outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT certify_outbox_pk PRIMARY KEY (id)
);

