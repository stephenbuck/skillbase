--liquibase formatted sql
--changeset skillbase_cert:initial

DROP TABLE IF EXISTS skillbase_model;
DROP TABLE IF EXISTS skillbase_cert;
DROP TABLE IF EXISTS skillbase_process;
DROP TABLE IF EXISTS skillbase_task;
DROP TABLE IF EXISTS skillbase_document;

CREATE TABLE IF NOT EXISTS skillbase_model (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  bpmn                 TEXT        NULL,
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_model_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS skillbase_cert (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NULL,
  model_id             UUID        NOT NULL,
  state                VARCHAR     NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  granted_at           TIMESTAMP   NULL,
  revoked_at           TIMESTAMP   NULL,
  expires_at           TIMESTAMP   NULL,
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_cert_pk PRIMARY KEY (id),
  CONSTRAINT skillbase_cert_fk_model FOREIGN KEY (model_id) REFERENCES skillbase_model (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS skillbase_process (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NOT NULL,
  cert_id              UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_process_pk PRIMARY KEY (id),
  CONSTRAINT skillbase_process_fk_cert FOREIGN KEY (cert_id) REFERENCES skillbase_cert (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS skillbase_process_by_cert_id ON skillbase_cert (id);

CREATE TABLE IF NOT EXISTS skillbase_task (
  id                   UUID        NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_task_pk PRIMARY KEY (id),
  CONSTRAINT skillbase_task_fk_process FOREIGN KEY (process_id) REFERENCES skillbase_process (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS skillbase_task_by_process_id ON skillbase_process (id);

CREATE TABLE IF NOT EXISTS skillbase_document (
  id                   UUID        NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  process_id           UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_document_pk PRIMARY KEY (id),
  CONSTRAINT skillbase_document_fk_process FOREIGN KEY (process_id) REFERENCES skillbase_process (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS skillbase_document_by_process_id ON skillbase_process (id);
