--liquibase formatted sql
--changeset skillbase_member:initial

CREATE SCHEMA member;

CREATE TABLE IF NOT EXISTS member.user (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  status_code          VARCHAR     NOT NULL,
  user_name            VARCHAR     NOT NULL UNIQUE,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT member_user_pk PRIMARY KEY (id)
);
-- CREATE INDEX IF NOT EXISTS member_user_by_last_name ON member.user (last_name);
-- CREATE INDEX IF NOT EXISTS member_user_by_user_name ON member.user (user_name);

INSERT INTO member.user (status_code, user_name, first_name, last_name, email, phone) VALUES ('TBD', 'stephenbuck', 'Stephen', 'Buck', 'stephenbuck@mac.com', '303.378.0247');
INSERT INTO member.user (status_code, user_name, first_name, last_name, email, phone) VALUES ('TBD', 'catherinebuck', 'Catherine', 'Buck', 'catherinebuck@mac.com', '303.378.6202');

CREATE TABLE IF NOT EXISTS member.group (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  parent_id            UUID            NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT member_group_pk PRIMARY KEY (id)
);
-- CREATE INDEX IF NOT EXISTS member_group_by_title ON member.group (title);

CREATE TABLE IF NOT EXISTS member.user_group (
  user_id              UUID        NOT NULL,
  group_id             UUID        NOT NULL

--  CONSTRAINT member_user_group_pk PRIMARY KEY (user_id, group_id),
--  CONSTRAINT member_user_group_fk_user FOREIGN KEY (user_id) REFERENCES member.user(id) ON DELETE CASCADE,
--  CONSTRAINT member_user_group_fk_group FOREIGN KEY (group_id) REFERENCES member.group(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS member.role (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT member_role_pk PRIMARY KEY (id)
);
-- CREATE INDEX IF NOT EXISTS member_role_by_title ON member.role (title);

CREATE TABLE IF NOT EXISTS member.user_role (
  user_id              UUID        NOT NULL,
  role_id              UUID        NOT NULL

--  CONSTRAINT member_user_role_pk PRIMARY KEY (user_id, role_id)
--  CONSTRAINT member_user_role_fk_user FOREIGN KEY (user_id) REFERENCES member.user(id) ON DELETE CASCADE,
--  CONSTRAINT member_user_role_fk_role FOREIGN KEY (role_id) REFERENCES member.role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS member.group_role (
  group_id             UUID        NOT NULL,
  role_id              UUID        NOT NULL

--  CONSTRAINT member_group_role_pk PRIMARY KEY (group_id, role_id)
--  CONSTRAINT member_group_role_fk_group FOREIGN KEY (group_id) REFERENCES member.group_role(id) ON DELETE CASCADE,
--  CONSTRAINT member_group_role_fk_role FOREIGN KEY (role_id) REFERENCES member.role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS member.outbox (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP       NULL DEFAULT NULL

--  CONSTRAINT member_outbox_pk PRIMARY KEY (id)
);
