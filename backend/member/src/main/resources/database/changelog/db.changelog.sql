--liquibase formatted sql
--changeset skillbase_member:initial

DROP SCHEMA IF EXISTS member CASCADE;
CREATE SCHEMA member;

CREATE TABLE IF NOT EXISTS member.user (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  user_name            VARCHAR     NOT NULL,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-1', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-1');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-2', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-2');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-3', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-3');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-4', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-4');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-5', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-5');

CREATE TABLE IF NOT EXISTS member.group (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image                TEXT            NULL DEFAULT NULL,
  valid_for            INT             NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);

insert into member.group(title, note) values('Group-1', 'Note-1');
insert into member.group(title, note) values('Group-2', 'Note-2');

CREATE TABLE IF NOT EXISTS member.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL
);
