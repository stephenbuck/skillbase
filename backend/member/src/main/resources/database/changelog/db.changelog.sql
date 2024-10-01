--liquibase formatted sql
--changeset skillbase_member:initial

DROP SCHEMA IF EXISTS member CASCADE;
CREATE SCHEMA member;

CREATE TABLE IF NOT EXISTS member.user (
  user_id              UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  is_enabled           BOOLEAN     NOT NULL DEFAULT FALSE,
  user_name            VARCHAR     NOT NULL,
  first_name           VARCHAR     NOT NULL,
  last_name            VARCHAR     NOT NULL,
  email                VARCHAR     NOT NULL,
  phone                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id)
);
ALTER TABLE member.user REPLICA IDENTITY DEFAULT;
CREATE INDEX user_name ON member.user(user_name);

INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-1', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-1');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-2', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-2');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-3', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-3');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-4', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-4');
INSERT INTO member.user(user_name, first_name, last_name, email, phone, note) values('User-5', 'First-1', 'Last-1', 'Email-1', 'Phone-1', 'Note-5');

CREATE TABLE IF NOT EXISTS member.achievement (
  achievement_id       UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  user_id              UUID        NOT NULL,
  state                VARCHAR         NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  valid_for            INTEGER     NOT NULL DEFAULT 0,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (achievement_id),
  FOREIGN KEY (user_id) REFERENCES member.user(user_id)
);
ALTER TABLE member.achievement REPLICA IDENTITY DEFAULT;
CREATE INDEX achievement_user ON member.achievement(user_id);
CREATE INDEX achievement_title ON member.achievement(title);

insert into member.achievement(title, user_id, note) values('Achievement-1', (select user_id from member.user where user_name like '%-1' limit 1), 'Note-1');
insert into member.achievement(title, user_id, note) values('Achievement-2', (select user_id from member.user where user_name like '%-1' limit 1), 'Note-2');
insert into member.achievement(title, user_id, note) values('Achievement-3', (select user_id from member.user where user_name like '%-1' limit 1), 'Note-2');
insert into member.achievement(title, user_id, note) values('Achievement-4', (select user_id from member.user where user_name like '%-1' limit 1), 'Note-2');
insert into member.achievement(title, user_id, note) values('Achievement-6', (select user_id from member.user where user_name like '%-1' limit 1), 'Note-2');

CREATE TABLE IF NOT EXISTS member.follow (
  follow_id            UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  source_id            UUID        NOT NULL,
  target_id            UUID        NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  FOREIGN KEY (source_id) REFERENCES member.user(user_id),
  FOREIGN KEY (target_id) REFERENCES member.user(user_id)
);
ALTER TABLE member.follow REPLICA IDENTITY DEFAULT;
CREATE INDEX follow_source ON member.follow(source_id);
CREATE INDEX follow_target ON member.follow(target_id);

CREATE TABLE IF NOT EXISTS member.group (
  group_id             UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  image_id             VARCHAR         NULL DEFAULT NULL,
  valid_for            INTEGER     NOT NULL DEFAULT 0,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (group_id)
);
ALTER TABLE member.group REPLICA IDENTITY DEFAULT;
CREATE INDEX group_title ON member.group(title);

insert into member.group(title, note) values('Group-1', 'Note-1');
insert into member.group(title, note) values('Group-2', 'Note-2');

CREATE TABLE IF NOT EXISTS member.process (
  process_id           UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  peer_id              VARCHAR         NULL DEFAULT NULL,
  user_id              UUID        NOT NULL,
  state                VARCHAR         NULL DEFAULT NULL,
  title                VARCHAR     NOT NULL,
  note                 VARCHAR     NOT NULL DEFAULT '',
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (process_id),
  FOREIGN KEY (user_id) REFERENCES member.user(user_id)
);
ALTER TABLE member.process REPLICA IDENTITY DEFAULT;
CREATE INDEX process_user ON member.process(user_id);

CREATE TABLE IF NOT EXISTS member.outbox (
  outbox_id            UUID        NOT NULL UNIQUE DEFAULT gen_random_uuid(),
  event                VARCHAR     NOT NULL,
  created_at           TIMESTAMP   NOT NULL DEFAULT now(),
  updated_at           TIMESTAMP   NOT NULL DEFAULT now(),
  version              INTEGER     NOT NULL DEFAULT 0,
  PRIMARY KEY (outbox_id)
);
ALTER TABLE member.outbox REPLICA IDENTITY DEFAULT;
