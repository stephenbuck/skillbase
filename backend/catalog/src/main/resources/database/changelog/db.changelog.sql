--liquibase formatted sql
--changeset skillbase_catalog:initial

DROP TABLE IF EXISTS catalog_category;
DROP TABLE IF EXISTS catalog_skill;
DROP TABLE IF EXISTS catalog_outbox;

CREATE TABLE IF NOT EXISTS catalog_category (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  parent_id            UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
--  icon                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT catalog_category_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS catalog_category_by_title ON catalog_category (title);

INSERT INTO catalog_category(title, note) values('Category-1', '');
INSERT INTO catalog_category(title, note) values('Category-2', '');
INSERT INTO catalog_category(title, note) values('Category-3', '');
INSERT INTO catalog_category(title, note) values('Category-4', '');
INSERT INTO catalog_category(title, note) values('Category-5', '');

CREATE TABLE IF NOT EXISTS catalog_skill (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  category_id          UUID        NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
--  icon                 TEXT            NULL DEFAULT NULL,
  valid_for            INT             NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT catalog_skill_pk PRIMARY KEY (id)
--  CONSTRAINT catalog_skill_fk_catalog_category FOREIGN KEY (category_id) REFERENCES catalog_category(id) ON DELETE CASCADE
);
CREATE INDEX IF NOT EXISTS catalog_skill_by_title ON catalog_skill (title);

insert into catalog_skill(title, category_id) values('Skill-1', (select id from catalog_category where title like '%-1' limit 1));
insert into catalog_skill(title, category_id) values('Skill-2', (select id from catalog_category where title like '%-2' limit 1));

CREATE TABLE IF NOT EXISTS catalog_outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT catalog_outbox_pk PRIMARY KEY (id)
);
