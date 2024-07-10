--liquibase formatted sql
--changeset skillbase_catalog:initial

DROP SCHEMA IF EXISTS catalog CASCADE;
CREATE SCHEMA catalog;

CREATE TABLE IF NOT EXISTS catalog.category (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  parent_id            UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

INSERT INTO catalog.category (title, note) values('Category-1', 'Note-1');
INSERT INTO catalog.category (title, note) values('Category-2', 'Note-2');
INSERT INTO catalog.category (title, note) values('Category-3', 'Note-3');
INSERT INTO catalog.category (title, note) values('Category-4', 'Note-4');
INSERT INTO catalog.category (title, note) values('Category-5', 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  category_id          UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

insert into catalog.skill (title, category_id, note) values('Skill-1', (select id from catalog.category where title like '%-1' limit 1), 'Note-1');
insert into catalog.skill (title, category_id, note) values('Skill-2', (select id from catalog.category where title like '%-2' limit 1), 'Note-2');

CREATE TABLE IF NOT EXISTS catalog.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
