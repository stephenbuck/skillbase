--liquibase formatted sql
--changeset skillbase_catalog:initial

DROP TABLE IF EXISTS catalog_category;
DROP TABLE IF EXISTS catalog_skill;

CREATE TABLE IF NOT EXISTS catalog_category (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  parent_id            UUID            NULL DEFAULT NULL,
  peer_id              VARCHAR         NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT catalog_category_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS catalog_category_by_title ON catalog_category (title);

INSERT INTO catalog_category
    ('title')
VALUES
    ('');

CREATE TABLE IF NOT EXISTS catalog_skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  category_id          UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  valid_for            INT             NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT catalog_skill_pk PRIMARY KEY (id),
  CONSTRAINT catalog_skill_fk_catalog_category FOREIGN KEY (category_id) REFERENCES catalog_category(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS catalog_skill_by_title ON catalog_skill (title);
