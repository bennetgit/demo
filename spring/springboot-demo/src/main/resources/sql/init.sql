DROP TABLE IF EXISTS _user;
CREATE TABLE IF NOT EXISTS _user (
  id         BIGINT       NOT NULL,
  mobile     VARCHAR(11),
  sex        INTEGER,
  username   VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  created_on TIMESTAMP WITHOUT TIME ZONE,
  updated_on TIMESTAMP WITHOUT TIME ZONE,
  is_admin   BOOLEAN      NOT NULL DEFAULT FALSE,
  status     INTEGER               DEFAULT 0,
  CONSTRAINT user_pk PRIMARY KEY (id)
) WITH (
OIDS = FALSE
);

DROP SEQUENCE IF EXISTS seq_user;
CREATE SEQUENCE seq_user
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;


DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
) WITH (
OIDS = FALSE
);

DROP TABLE IF EXISTS _role;
CREATE TABLE IF NOT EXISTS _role (
  id          BIGINT       NOT NULL,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_on  TIMESTAMP WITHOUT TIME ZONE,
  updated_on  TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT role_pk PRIMARY KEY (id)
) WITH (
OIDS = FALSE
);

DROP SEQUENCE IF EXISTS seq_role;
CREATE SEQUENCE seq_role
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;


DROP TABLE IF EXISTS role_menu;
CREATE TABLE IF NOT EXISTS role_menu (
  role_id BIGINT NOT NULL,
  menu_id BIGINT NOT NULL
) WITH (
OIDS = FALSE
);

DROP TABLE IF EXISTS _menu;
CREATE TABLE IF NOT EXISTS _menu (
  id         BIGINT       NOT NULL,
  name       VARCHAR(255) NOT NULL,
  parent_id  BIGINT,
  url        VARCHAR(255) NOT NULL,
  sequence   INTEGER DEFAULT 0,
  created_on TIMESTAMP WITHOUT TIME ZONE,
  updated_on TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT menu_pk PRIMARY KEY (id)
) WITH (
OIDS = FALSE
);

DROP SEQUENCE IF EXISTS seq_menu;
CREATE SEQUENCE seq_menu
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;


DROP TABLE IF EXISTS _privilege;
CREATE TABLE _privilege (
  id             BIGINT       NOT NULL,
  name           VARCHAR(128),
  url            VARCHAR(128) NOT NULL,
  module         INTEGER      NOT NULL,
  request_method INTEGER      NOT NULL,
  created_by     BIGINT       NOT NULL,
  updated_by     BIGINT,
  created_on     TIMESTAMP WITHOUT TIME ZONE,
  updated_on     TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS seq_privilege;
CREATE SEQUENCE seq_privilege
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
;

DROP TABLE IF EXISTS role_privilege;
CREATE TABLE role_privilege (
  role_id      BIGINT NOT NULL,
  privilege_id BIGINT NOT NULL
);

----username: admin    password: a
INSERT INTO _user
VALUES (nextval('seq_user'), 10, NULL, 'admin', '0cc175b9c0f1b6a831c399e269772661', now(), now(), TRUE, 1);

INSERT INTO _role VALUES (nextval('seq_role'), '管理员', now(), now());
INSERT INTO user_role VALUES (1, 1);

INSERT INTO _menu VALUES (nextval('seq_menu'), '系统管理', NULL, 'sys', 0, now(), now());
INSERT INTO _menu VALUES (nextval('seq_menu'), '角色管理', 1, 'role.list', 0, now(), now());

INSERT INTO _menu VALUES (nextval('seq_menu'), '用户管理', 1, 'user.list', 1, now(), now());
INSERT INTO _menu VALUES (nextval('seq_menu'), '菜单管理', 1, 'menu.list', 2, now(), now());
INSERT INTO _menu VALUES (nextval('seq_menu'), '权限管理', 1, 'privilege.list', 3, now(), now());

INSERT INTO role_menu VALUES (1, 6), (1, 2), (1, 3), (1, 4), (1, 5), (1, 1);

DROP TABLE IF EXISTS login_audit;
CREATE TABLE login_audit (
  id         BIGINT NOT NULL,
  user_id    BIGINT,
  user_agent VARCHAR(512),
  ip_address VARCHAR(128),
  login_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP SEQUENCE IF EXISTS seq_login_audit;
CREATE SEQUENCE seq_login_audit
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;
;
