drop table if EXISTS _user;
create table IF NOT EXISTS _user(
   	id bigint not null,
   	age integer,
   	sex integer,
    username varchar(255) not null,
    password varchar(255) not null,
    created_on TIMESTAMP WITHOUT TIME ZONE,
    updated_on TIMESTAMP WITHOUT TIME ZONE,
	constraint user_pk primary key(id)
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


drop table if EXISTS _menu;
create table IF NOT EXISTS _menu(
  id bigint not null,
  name varchar(255) not null,
  parent_id bigint,
  url varchar(255) not null,
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