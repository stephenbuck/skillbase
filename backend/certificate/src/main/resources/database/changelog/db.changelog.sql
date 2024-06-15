--liquibase formatted sql
--changeset skillbase_certificate:initial

CREATE TABLE IF NOT EXISTS certificate (
  id                   UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  status_code          VARCHAR     NOT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  granted_at           TIMESTAMP   NULL,
  revoked_at           TIMESTAMP   NULL,
  expires_at           TIMESTAMP   NULL,
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT certificate_pk PRIMARY KEY (id)
);
