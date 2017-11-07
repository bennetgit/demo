DROP TABLE IF exists _user;
CREATE TABLE IF NOT EXISTS _user(
    id bigint NOT NULL,
    age INTEGER,
    sex INTEGER,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_on TIMESTAMP WITHOUT TIME ZONE,
    updated_on TIMESTAMP WITHOUT TIME ZONE,
 CONSTRAINT user_pk PRIMARY KEY(id)
   )WITH (
     OIDS=FALSE
    );

   DO  $$
   BEGIN
      CREATE SEQUENCE seq_user
       INCREMENT 1
       START 1
       MINVALUE 1
       MAXVALUE 9223372036854775807
       CACHE 1;
     EXCEPTION
     WHEN duplicate_table THEN
       RAISE NOTICE 'sequence [seq_user] already exists' ;
   END ;
   $$;


DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role(
  user_id bigint NOT NULL,
  role_id bigint NOT NULL
)WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS _role;
CREATE TABLE IF NOT EXISTS _role(
  id bigint NOT NULL,
  name varchar(255) NOT NULL,
  created_on TIMESTAMP WITHOUT TIME ZONE,
  updated_on TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT role_pk primary key(id)
)WITH (
OIDS=FALSE
);

DO  $$
   BEGIN
      CREATE SEQUENCE seq_role
       INCREMENT 1
       START 1
       MINVALUE 1
       MAXVALUE 9223372036854775807
       CACHE 1;
     EXCEPTION
     WHEN duplicate_table THEN
       RAISE NOTICE 'sequence [seq_role] already exists' ;
   END ;
   $$;


DROP TABLE IF EXISTS role_menu;
CREATE TABLE IF NOT EXISTS role_menu(
  role_id bigint NOT NULL,
  menu_id bigint NOT NULL
)WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS _menu;
CREATE TABLE IF NOT EXISTS _menu(
  id bigint NOT NULL,
  name varchar(255) NOT NULL,
  parent_id bigint,
  url varchar(255) NOT NULL,
  sequence integer DEFAULT 0,
  created_on TIMESTAMP WITHOUT TIME ZONE,
  updated_on TIMESTAMP WITHOUT TIME ZONE,
  constraint menu_pk primary key(id)
)WITH (
OIDS=FALSE
);

DO  $$
   BEGIN
      CREATE SEQUENCE seq_menu
       INCREMENT 1
       START 1
       MINVALUE 1
       MAXVALUE 9223372036854775807
       CACHE 1;
     EXCEPTION
     WHEN duplicate_table THEN
       RAISE NOTICE 'sequence [seq_menu] already exists' ;
   END ;
   $$;