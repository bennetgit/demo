drop table if exists _user;
create table if not exists _user(
  id bigint not null,
  mobile varchar(11),
  sex integer,
  username varchar(255) not null,
  password varchar(255) not null,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  is_admin boolean not null default false,
  constraint user_pk primary key(id)
)with (
oids=false
);

drop sequence if exists seq_user;
create sequence seq_user
increment 1
start 1
minvalue 1
maxvalue 9223372036854775807
cache 1;


drop table if exists user_role;
create table if not exists user_role(
  user_id bigint not null,
  role_id bigint not null
)with (
oids=false
);

drop table if exists _role;
create table if not exists _role(
  id bigint not null,
  name varchar(255) not null,
  description varchar(255) not null,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  constraint role_pk primary key(id)
)with (
oids=false
);

drop sequence if exists seq_role;
create sequence seq_role
increment 1
start 1
minvalue 1
maxvalue 9223372036854775807
cache 1;


drop table if exists role_menu;
create table if not exists role_menu(
  role_id bigint not null,
  menu_id bigint not null
)with (
oids=false
);

drop table if exists _menu;
create table if not exists _menu(
  id bigint not null,
  name varchar(255) not null,
  parent_id bigint,
  url varchar(255) not null,
  sequence integer default 0,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  constraint menu_pk primary key(id)
)with (
oids=false
);

drop sequence if exists seq_menu;
create sequence seq_menu
increment 1
start 1
minvalue 1
maxvalue 9223372036854775807
cache 1;


----username: admin    password: a
insert into _user values(nextval('seq_user'), 10, null, 'admin', '0cc175b9c0f1b6a831c399e269772661', now(),now(),true);

insert into _role values(nextval('seq_role'),'管理员',now(),now());
insert into user_role values(1,1);

insert into _menu values(nextval('seq_menu'), '系统管理',1,'sys',0,now(),now());
insert into _menu values(nextval('seq_menu'), '角色管理',1,'role.list',1,now(),now());

insert into _menu values(nextval('seq_menu'), '用户管理',1,'user.list',0,now(),now());
insert into _menu values(nextval('seq_menu'), '菜单管理',1,'menu.list',1,now(),now());

insert into role_menu values(1,6),(1,2),(1,3),(1,4),(1,5),(1,1);

