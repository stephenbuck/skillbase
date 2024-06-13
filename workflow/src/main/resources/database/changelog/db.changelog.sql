--liquibase formatted sql
--changeset skillbase_workflow:initial

CREATE TABLE IF NOT EXISTS model (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT model_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS workflow (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  model_id             INT         NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT workflow_pk PRIMARY KEY (id),
  CONSTRAINT workflow_fk_model FOREIGN KEY (model_id) REFERENCES model (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS process_by_workflow_id ON workflow (id);

CREATE TABLE IF NOT EXISTS process (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  workflow_id          INT         NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT process_pk PRIMARY KEY (id),
  CONSTRAINT process_fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflow (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS process_by_workflow_id ON workflow (id);

CREATE TABLE IF NOT EXISTS task (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  process_id           INT         NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT task_pk PRIMARY KEY (id),
  CONSTRAINT task_fk_process FOREIGN KEY (process_id) REFERENCES process (id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS task_by_process_id ON process (id);
