--liquibase formatted sql
--changeset skillbase_members:initial

CREATE TABLE IF NOT EXISTS member (
  id                   INT         NOT NULL UNIQUE,
  status_code          VARCHAR     NOT NULL,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT member_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS member_by_last_name ON member (last_name);

-- CREATE TABLE IF NOT EXISTS member_skill (
--   id                   INT         NOT NULL UNIQUE,
--   member_id            INT         NOT NULL,
--   skill_id             INT         NOT NULL,
--   process_id           INT         NULL,
--   status_code          VARCHAR     NOT NULL,
--   title                VARCHAR     NOT NULL,
--   note                 VARCHAR     NOT NULL DEFAULT '',
--   created_at           TIMESTAMP   NOT NULL,
--   updated_at           TIMESTAMP   NOT NULL,
-- 
--   CONSTRAINT member_skill_pk PRIMARY KEY (id),
--   CONSTRAINT member_skill_fk_member FOREIGN KEY (member_id) REFERENCES member (id),
--   CONSTRAINT member_skill_fk_skill FOREIGN KEY (skill_id) REFERENCES skill (id),
--   CONSTRAINT member_skill_fk_process FOREIGN KEY (process_id) REFERENCES process (id)
-- );
-- CREATE INDEX IF NOT EXISTS member_skill_by_member_id ON member_skill (member_id);
-- CREATE INDEX IF NOT EXISTS member_skill_by_skill_id ON member_skill (skill_id);
