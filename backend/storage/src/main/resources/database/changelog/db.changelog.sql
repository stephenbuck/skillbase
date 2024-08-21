--liquibase formatted sql
--changeset skillbase_storage:initial

DROP SCHEMA IF EXISTS storage CASCADE;
CREATE SCHEMA storage;

CREATE TABLE IF NOT EXISTS storage.category (
  category_id          UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  parent_id            UUID            NULL DEFAULT NULL,
  is_enabled           BOOLEAN     NOT NULL DEFAULT FALSE,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  FOREIGN KEY (parent_id) REFERENCES storage.category(category_id)
);
CREATE INDEX category_parent ON storage.category(parent_id);
CREATE INDEX category_title ON storage.category(title);

INSERT INTO storage.category (parent_id, title, note) values(NULL, 'Category-1', 'Note-1');
INSERT INTO storage.category (parent_id, title, note) values(NULL, 'Category-2', 'Note-2');
INSERT INTO storage.category (parent_id, title, note) values(NULL, 'Category-3', 'Note-3');
INSERT INTO storage.category (parent_id, title, note) values(NULL, 'Category-4', 'Note-4');
INSERT INTO storage.category (parent_id, title, note) values(NULL, 'Category-5', 'Note-5');

CREATE TABLE IF NOT EXISTS storage.outbox (
  outbox_id            UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now()
);
