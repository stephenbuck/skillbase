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

CREATE TABLE IF NOT EXISTS process (
  id                   INT         NOT NULL UNIQUE,
  workflow_id          INT         NOT NULL,
  peer_id              VARCHAR     NOT NULL,
  process_name         VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT process_pk PRIMARY KEY (id),
  CONSTRAINT process_fk_workflow FOREIGN KEY (workflow_id) REFERENCES workflow (id),
)
CREATE INDEX IF NOT EXISTS process_by_workflow_id ON workflow (id);

CREATE TABLE IF NOT EXISTS workflow (
  id                   INT         NOT NULL UNIQUE,
  peer_id              VARCHAR     NOT NULL,
  worflow_name         VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL,
  updated_at           TIMESTAMP   NOT NULL,

  CONSTRAINT workflow_pk PRIMARY KEY (id)
)
