--liquibase formatted sql
--changeset skillbase_identity:initial

DROP TABLE IF EXISTS identity_user;
DROP TABLE IF EXISTS identity_group;
DROP TABLE IF EXISTS identity_user_group;
DROP TABLE IF EXISTS identity_role;
DROP TABLE IF EXISTS identity_group_role;
DROP TABLE IF EXISTS identity_user_role;

CREATE TABLE IF NOT EXISTS identity_user (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  status_code          VARCHAR     NOT NULL,
  user_name            VARCHAR     NOT NULL UNIQUE,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  inserted_at          TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT identity_user_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS identity_user_by_last_name ON identity_user (last_name);
CREATE INDEX IF NOT EXISTS identity_user_by_user_name ON identity_user (user_name);

CREATE TABLE IF NOT EXISTS identity_group (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  icon                 TEXT            NULL DEFAULT NULL,
  inserted_at          TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT identity_group_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS identity_group_by_title ON identity_group (title);

CREATE TABLE IF NOT EXISTS identity_user_group (
  user_id UUID         UUID        NOT NULL,
  group_id UUID        UUID        NOT NULL,

  CONSTRAINT identity_user_group_pk PRIMARY KEY (user_id, group_id),
  CONSTRAINT identity_user_group_fk_user FOREIGN KEY (user_id) REFERENCES identity_user(id) ON DELETE CASCADE,
  CONSTRAINT identity_user_group_fk_group FOREIGN KEY (group_id) REFERENCES identity_group(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS identity_role (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  inserted_at          TIMESTAMP   NOT NULL DEFAULT now,
  updated_at           TIMESTAMP       NULL DEFAULT NULL,

  CONSTRAINT identity_role_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS identity_role_by_title ON identity_role (title);

CREATE TABLE IF NOT EXISTS identity_user_role (
  user_id              UUID        NOT NULL,
  role_id              UUID        NOT NULL,

  CONSTRAINT identity_user_role_pk PRIMARY KEY (user_id, role_id)
--  CONSTRAINT identity_user_role_fk_user FOREIGN KEY (user_id) REFERENCES identity_user(id) ON DELETE CASCADE,
--  CONSTRAINT identity_user_role_fk_role FOREIGN KEY (role_id) REFERENCES identity_role(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS identity_group_role (
  group_id             UUID        NOT NULL,
  role_id              UUID        NOT NULL,

  CONSTRAINT identity_group_role_pk PRIMARY KEY (group_id, role_id)
--  CONSTRAINT identity_group_role_fk_group FOREIGN KEY (group_id) REFERENCES identity_group_role(id) ON DELETE CASCADE,
--  CONSTRAINT identity_group_role_fk_role FOREIGN KEY (role_id) REFERENCES identity_role(id) ON DELETE CASCADE
);

