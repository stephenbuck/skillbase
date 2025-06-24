--liquibase formatted sql
--changeset skillbase_catalog:initial

DROP SCHEMA IF EXISTS catalog CASCADE;
CREATE SCHEMA catalog;

CREATE TABLE IF NOT EXISTS catalog.category (
  category_id          UUID            NULL UNIQUE DEFAULT uuid_v7(),
  parent_id            UUID            NULL DEFAULT NULL,
  is_enabled           BOOLEAN         NULL DEFAULT FALSE,
  title                VARCHAR         NULL,
  note                 VARCHAR         NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  created_at           TIMESTAMP       NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT now(),
  version              INTEGER         NULL DEFAULT 0,
  PRIMARY KEY (category_id),
  FOREIGN KEY (parent_id) REFERENCES catalog.category(category_id)
);
ALTER TABLE catalog.category REPLICA IDENTITY DEFAULT;
CREATE INDEX category_parent ON catalog.category(parent_id);
CREATE INDEX category_title ON catalog.category(title);

INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-1', 'Note-1');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-2', 'Note-2');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-3', 'Note-3');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-4', 'Note-4');
INSERT INTO catalog.category (parent_id, title, note) values(NULL, 'Category-5', 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.skill (
  skill_id             UUID        NOT NULL UNIQUE DEFAULT uuid_v7(),
  deployment_id        VARCHAR         NULL DEFAULT NULL,
  category_id          UUID            NULL DEFAULT NULL,
  is_enabled           BOOLEAN     NOT NULL DEFAULT FALSE,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (skill_id),
  FOREIGN KEY (category_id) REFERENCES catalog.category(category_id)
);
ALTER TABLE catalog.skill REPLICA IDENTITY DEFAULT;
CREATE INDEX skill_category ON catalog.skill(category_id);
CREATE INDEX skill_title ON catalog.skill(title);

insert into catalog.skill (title, category_id, note) values('Skill-1', (select category_id from catalog.category where title like '%-1' limit 1), 'Note-1');
insert into catalog.skill (title, category_id, note) values('Skill-2', (select category_id from catalog.category where title like '%-1' limit 1), 'Note-2');
insert into catalog.skill (title, category_id, note) values('Skill-3', (select category_id from catalog.category where title like '%-1' limit 1), 'Note-3');
insert into catalog.skill (title, category_id, note) values('Skill-4', (select category_id from catalog.category where title like '%-1' limit 1), 'Note-4');
insert into catalog.skill (title, category_id, note) values('Skill-5', (select category_id from catalog.category where title like '%-1' limit 1), 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.credential (
  credential_id        UUID        NOT NULL UNIQUE DEFAULT uuid_v7(),
  skill_id             UUID        NOT NULL,
  is_enabled           BOOLEAN     NOT NULL DEFAULT FALSE,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  bpmn_id              VARCHAR         NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (credential_id),
  FOREIGN KEY (skill_id) REFERENCES catalog.skill(skill_id)
);
ALTER TABLE catalog.credential REPLICA IDENTITY DEFAULT;
CREATE INDEX credential_skill ON catalog.credential(skill_id);
CREATE INDEX credential_title ON catalog.credential(title);

insert into catalog.credential (title, skill_id, note) values('Credential-1', (select skill_id from catalog.skill where title like '%-1' limit 1), 'Note-1');
insert into catalog.credential (title, skill_id, note) values('Credential-2', (select skill_id from catalog.skill where title like '%-1' limit 1), 'Note-2');
insert into catalog.credential (title, skill_id, note) values('Credential-3', (select skill_id from catalog.skill where title like '%-1' limit 1), 'Note-3');
insert into catalog.credential (title, skill_id, note) values('Credential-4', (select skill_id from catalog.skill where title like '%-1' limit 1), 'Note-4');
insert into catalog.credential (title, skill_id, note) values('Credential-5', (select skill_id from catalog.skill where title like '%-1' limit 1), 'Note-5');

CREATE TABLE IF NOT EXISTS catalog.outbox (
  outbox_id            UUID        NOT NULL UNIQUE DEFAULT uuid_v7(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY(outbox_id)
);
ALTER TABLE catalog.outbox REPLICA IDENTITY DEFAULT;
