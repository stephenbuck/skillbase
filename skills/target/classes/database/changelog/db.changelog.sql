--liquibase formatted sql
--changeset skillbase_skills:initial

CREATE TABLE IF NOT EXISTS category (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS skill (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NULL,
  category_id          INT         NOT NULL,
  workflow_id          INT         NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT skill_pk PRIMARY KEY (id),
  CONSTRAINT skill_fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
--  CONSTRAINT skill_fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflow(id) ON DELETE CASCADE
);
