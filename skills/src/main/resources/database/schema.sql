CREATE TABLE IF NOT EXISTS skill (
  id                   INT         NOT NULL UNIQUE,
  status_code          VARCHAR     NOT NULL,
  skill_name           VARCHAR     NOT NULL,
  skill_desc           VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,
  workflow_id          INT         NOT NULL,

  CONSTRAINT skill_pk PRIMARY KEY (id),
  CONSTRAINT skill_fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflow(id)
);
