--liquibase formatted sql
--changeset skillbase_user:initial

DROP TABLE IF EXISTS skillbase_user;
DROP TABLE IF EXISTS skillbase_group;
DROP TABLE IF EXISTS skillbase_user_group;
DROP TABLE IF EXISTS skillbase_role;
DROP TABLE IF EXISTS skillbase_group_role;
DROP TABLE IF EXISTS skillbase_user_role;

CREATE TABLE IF NOT EXISTS skillbase_user (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  status_code          VARCHAR     NOT NULL,
  user_name            VARCHAR     NOT NULL UNIQUE,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT        NULL,
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_user_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS skillbase_user_by_last_name ON skillbase_user (last_name);
CREATE INDEX IF NOT EXISTS skillbase_user_by_user_name ON skillbase_user (user_name);

CREATE TABLE IF NOT EXISTS skillbase_group (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT        NULL,
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NULL,

  CONSTRAINT skillbase_group_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS skillbase_group_by_title ON skillbase_group (title);

CREATE TABLE IF NOT EXISTS skillbase_user_group (
  user_id UUID         NOT NULL,
  group_id UUID        NOT NULL,

  CONSTRAINT skillbase_user_group_pk PRIMARY KEY (user_id, group_id),
  CONSTRAINT skillbase_user_group_fk_user FOREIGN KEY (user_id) REFERENCES skillbase_user(id) ON DELETE CASCADE,
  CONSTRAINT skillbase_user_group_fk_group FOREIGN KEY (group_id) REFERENCES skillbase_group(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS skillbase_role (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  inserted_at          TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT skillbase_role_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS skillbase_role_by_title ON skillbase_role (title);

CREATE TABLE IF NOT EXISTS skillbase_user_role (
  user_id UUID         NOT NULL,
  role_id UUID         NOT NULL,

  CONSTRAINT skillbase_user_role_pk PRIMARY KEY (user_id, role_id)
--  CONSTRAINT skillbase_user_role_fk_user FOREIGN KEY (user_id) REFERENCES skillbase_user(id) ON DELETE CASCADE,
--  CONSTRAINT skillbase_user_role_fk_role FOREIGN KEY (role_id) REFERENCES skillbase_role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS skillbase_group_role (
  group_id UUID        NOT NULL,
  role_id UUID         NOT NULL,

  CONSTRAINT skillbase_group_role_pk PRIMARY KEY (group_id, role_id)
--  CONSTRAINT skillbase_group_role_fk_group FOREIGN KEY (group_id) REFERENCES skillbase_group_role(id) ON DELETE CASCADE,
--  CONSTRAINT skillbase_group_role_fk_role FOREIGN KEY (role_id) REFERENCES skillbase_role(id) ON DELETE CASCADE
);

