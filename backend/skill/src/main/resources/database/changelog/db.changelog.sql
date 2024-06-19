--liquibase formatted sql
--changeset skillbase_skill:initial

DROP TABLE IF EXISTS skillbase_category;
DROP TABLE IF EXISTS skillbase_skill;

CREATE TABLE IF NOT EXISTS skillbase_category (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT        NULL,
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_category_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS skillbase_category_by_title ON skillbase_category (title);

CREATE TABLE IF NOT EXISTS skillbase_skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR     NULL,
  category_id          UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT        NULL,
  valid_for            INT         NULL,
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_skill_pk PRIMARY KEY (id),
  CONSTRAINT skillbase_skill_fk_category FOREIGN KEY (category_id) REFERENCES skillbase_category(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS skillbase_skill_by_title ON skillbase_skill (title);
