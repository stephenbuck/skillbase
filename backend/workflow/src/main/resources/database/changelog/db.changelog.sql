--liquibase formatted sql
--changeset skillbase_workflow:initial

DROP SCHEMA IF EXISTS workflow CASCADE;
CREATE SCHEMA workflow;

CREATE TABLE IF NOT EXISTS workflow.deployment (
  deployment_id            UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id                  VARCHAR         NULL DEFAULT NULL,
  skill_id                 UUID            NULL DEFAULT NULL,
  state                    VARCHAR         NULL DEFAULT NULL,
  title                    VARCHAR     NOT NULL,
  note                     VARCHAR     NOT NULL DEFAULT '',
  created_at               TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at               TIMESTAMP   NOT NULL DEFAULT now(),
  version                  INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (deployment_id)
);
ALTER TABLE workflow.deployment REPLICA IDENTITY DEFAULT;
CREATE INDEX deployment_title ON workflow.deployment(title);

INSERT INTO workflow.deployment(title, note) values('Deployment-1', 'Note-1');
INSERT INTO workflow.deployment(title, note) values('Deployment-2', 'Note-2');

CREATE TABLE IF NOT EXISTS workflow.definition (
  definition_id            UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id                  VARCHAR         NULL DEFAULT NULL,
  deployment_id            UUID        NOT NULL,
  credential_id            UUID            NULL DEFAULT NULL,
  title                    VARCHAR     NOT NULL,
  note                     VARCHAR     NOT NULL DEFAULT '',
  image_id                 VARCHAR         NULL DEFAULT NULL,
  valid_for                INTEGER     NOT NULL DEFAULT 0,
  created_at               TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at               TIMESTAMP   NOT NULL DEFAULT now(),
  version                  INTEGER     NOT NULL DEFAULT 0,
  PRIMARY_KEY (definition_id),
  FOREIGN KEY (deployment_id) REFERENCES workflow.deployment(deployment_id)
);
ALTER TABLE workflow.definition REPLICA IDENTITY DEFAULT;
CREATE INDEX definition_title ON workflow.definition(title);
CREATE INDEX definition_deployment ON workflow.definition(deployment_id);

INSERT INTO workflow.definition(deployment_id, title, note) values((SELECT deployment_id FROM workflow.deployment WHERE title LIKE '%-1' LIMIT 1), 'Model-1', 'Note-1');
INSERT INTO workflow.definition(deployment_id, title, note) values((SELECT deployment_id FROM workflow.deployment WHERE title LIKE '%-1' LIMIT 1), 'Model-2', 'Note-2');
INSERT INTO workflow.definition(deployment_id, title, note) values((SELECT deployment_id FROM workflow.deployment WHERE title LIKE '%-1' LIMIT 1), 'Model-3', 'Note-3');
INSERT INTO workflow.definition(deployment_id, title, note) values((SELECT deployment_id FROM workflow.deployment WHERE title LIKE '%-1' LIMIT 1), 'Model-4', 'Note-4');
INSERT INTO workflow.definition(deployment_id, title, note) values((SELECT deployment_id FROM workflow.deployment WHERE title LIKE '%-1' LIMIT 1), 'Model-5', 'Note-5');

CREATE TABLE IF NOT EXISTS workflow.instance (
  instance_id              UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id                  VARCHAR         NULL DEFAULT NULL,
  definition_id            UUID        NOT NULL,
  user_id                  UUID            NULL DEFAULT NULL,
  is_test                  BOOLEAN     NOT NULL DEFAULT FALSE,
  state                    VARCHAR         NULL DEFAULT NULL,
  title                    VARCHAR     NOT NULL,
  note                     VARCHAR     NOT NULL DEFAULT '',
  created_at               TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at               TIMESTAMP   NOT NULL DEFAULT now(),
  version                  INTEGER     NOT NULL DEFAULT 0,
  PRIMARY_KEY (instance_id),
  FOREIGN KEY (definition_id) REFERENCES workflow.definition(definition_id)
);
ALTER TABLE workflow.instance REPLICA IDENTITY DEFAULT;
CREATE INDEX instance_title ON workflow.instance(title);

INSERT INTO workflow.instance(definition_id, title, note) values((SELECT definition_id FROM workflow.definition WHERE title LIKE '%-1' LIMIT 1), 'Process-1', 'Note-1');
INSERT INTO workflow.instance(definition_id, title, note) values((SELECT definition_id FROM workflow.definition WHERE title LIKE '%-1' LIMIT 1), 'Process-2', 'Note-2');
INSERT INTO workflow.instance(definition_id, title, note) values((SELECT definition_id FROM workflow.definition WHERE title LIKE '%-1' LIMIT 1), 'Process-3', 'Note-3');
INSERT INTO workflow.instance(definition_id, title, note) values((SELECT definition_id FROM workflow.definition WHERE title LIKE '%-1' LIMIT 1), 'Process-4', 'Note-4');
INSERT INTO workflow.instance(definition_id, title, note) values((SELECT definition_id FROM workflow.definition WHERE title LIKE '%-1' LIMIT 1), 'Process-5', 'Note-5');

CREATE TABLE IF NOT EXISTS workflow.task (
  task_id                  UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id                  VARCHAR         NULL DEFAULT NULL,
  instance_id              UUID        NOT NULL,
  title                    VARCHAR     NOT NULL,
  note                     VARCHAR     NOT NULL DEFAULT '',
  created_at               TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at               TIMESTAMP   NOT NULL DEFAULT now(),
  version                  INTEGER     NOT NULL DEFAULT 0,
  PRIMARY_KEY (task_id),
  FOREIGN KEY (instance_id) REFERENCES workflow.instance(instance_id)
);
ALTER TABLE workflow.task REPLICA IDENTITY DEFAULT;
CREATE INDEX task_title ON workflow.task(title);
CREATE INDEX task_instance ON workflow.task(instance_id);

CREATE TABLE IF NOT EXISTS workflow.outbox (
  outbox_id                UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                    VARCHAR     NOT NULL,
  created_at               TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at               TIMESTAMP   NOT NULL DEFAULT now(),
  PRIMARY_KEY (outbox_id)
);
ALTER TABLE workflow.outbox REPLICA IDENTITY DEFAULT;
