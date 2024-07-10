--liquibase formatted sql
--changeset skillbase_workflow:initial

DROP SCHEMA IF EXISTS workflow CASCADE;
CREATE SCHEMA workflow;

CREATE TABLE IF NOT EXISTS workflow.model (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

INSERT INTO workflow.model(title, note) values('Model-1', 'Note-1');
INSERT INTO workflow.model(title, note) values('Model-2', 'Note-2');
INSERT INTO workflow.model(title, note) values('Model-3', 'Note-3');
INSERT INTO workflow.model(title, note) values('Model-4', 'Note-4');
INSERT INTO workflow.model(title, note) values('Model-5', 'Note-5');

CREATE TABLE IF NOT EXISTS workflow.process (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

INSERT INTO workflow.process(title, note) values('Process-1', 'Note-1');
INSERT INTO workflow.process(title, note) values('Process-2', 'Note-2');
INSERT INTO workflow.process(title, note) values('Process-3', 'Note-3');
INSERT INTO workflow.process(title, note) values('Process-4', 'Note-4');
INSERT INTO workflow.process(title, note) values('Process-5', 'Note-5');

CREATE TABLE IF NOT EXISTS workflow.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
