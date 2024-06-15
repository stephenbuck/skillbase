--liquibase formatted sql
--changeset skillbase_skill:initial

CREATE TABLE IF NOT EXISTS category (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NULL,
  category_id          UUID        NOT NULL,
  workflow_id          UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  valid_for            INT         NULL,
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT skill_pk PRIMARY KEY (id),
  CONSTRAINT skill_fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
--  CONSTRAINT skill_fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflow(id) ON DELETE CASCADE
);
