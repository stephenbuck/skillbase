--liquibase formatted sql
--changeset skillbase_member:initial

CREATE TABLE IF NOT EXISTS member (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  status_code          VARCHAR     NOT NULL,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT member_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS member_by_last_name ON member (last_name);
