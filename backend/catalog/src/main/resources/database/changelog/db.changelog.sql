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
CREATE INDEX catalog.category_parent ON catalog.category(parent_id);
CREATE INDEX catalog.category_title ON catalog.category(title);

INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-1', 'Note-1');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-2', 'Note-2');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-3', 'Note-3');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-4', 'Note-4');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-5', 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  category_id          UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
CREATE INDEX catalog.skill_category ON catalog.skill(category_id);
CREATE INDEX catalog.skill_title ON catalog.skill(title);

insert into catalog.skill (title, category_id, note) values('Skill-1', (select id from catalog.category where title like '%-1' limit 1), 'Note-1');
insert into catalog.skill (title, category_id, note) values('Skill-2', (select id from catalog.category where title like '%-1' limit 1), 'Note-2');
insert into catalog.skill (title, category_id, note) values('Skill-3', (select id from catalog.category where title like '%-1' limit 1), 'Note-3');
insert into catalog.skill (title, category_id, note) values('Skill-4', (select id from catalog.category where title like '%-1' limit 1), 'Note-4');
insert into catalog.skill (title, category_id, note) values('Skill-5', (select id from catalog.category where title like '%-1' limit 1), 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.credential (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  skill_id             UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  bpmn                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
CREATE INDEX catalog.credential_skill ON catalog.credential(skill_id);
CREATE INDEX catalog.credential_title ON catalog.credential(title);


insert into catalog.credential (title, skill_id, note) values('Credential-1', (select id from catalog.skill where title like '%-1' limit 1), 'Note-1');
insert into catalog.credential (title, skill_id, note) values('Credential-2', (select id from catalog.skill where title like '%-1' limit 1), 'Note-2');
insert into catalog.credential (title, skill_id, note) values('Credential-3', (select id from catalog.skill where title like '%-1' limit 1), 'Note-3');
insert into catalog.credential (title, skill_id, note) values('Credential-4', (select id from catalog.skill where title like '%-1' limit 1), 'Note-4');
insert into catalog.credential (title, skill_id, note) values('Credential-5', (select id from catalog.skill where title like '%-1' limit 1), 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
