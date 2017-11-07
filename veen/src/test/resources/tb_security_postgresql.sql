----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS login;
CREATE TABLE login (
  id bigint NOT NULL,
  user_id bigint DEFAULT '0',
  user_agent varchar(128) DEFAULT '',
  ip_address varchar(128) DEFAULT '',
  type integer DEFAULT '1',
  login_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status integer DEFAULT '1',
  is_delete integer DEFAULT '0',
  PRIMARY KEY (id)
);

comment on column login.user_agent is '浏览器标识';
comment on column login.ip_address is 'IP地址';
comment on column login.type is '1：网页登录\r\n2：OpenId登录';
comment on column login.login_time is '登录时间';
comment on column login.status is '1：正常';
comment on column login.is_delete is '是否删除';

-- ----------------------------
-- Records of login
-- ----------------------------

-- ----------------------------
-- Table structure for logout
-- ----------------------------
DROP TABLE IF EXISTS logout;
CREATE TABLE logout (
  id bigint NOT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of logout
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS persistent_logins;
CREATE TABLE persistent_logins (
  username varchar(64) NOT NULL,
  series varchar(64) NOT NULL,
  token varchar(64) NOT NULL,
  last_used TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (series)
);

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS sys_resources;
CREATE TABLE sys_resources (
  id bigint NOT NULL,
  icon varchar(255) DEFAULT '',
  name varchar(24) DEFAULT '',
  authority varchar(64) DEFAULT '',
  url varchar(255) DEFAULT '',
  type int DEFAULT '0',
  parent_id bigint DEFAULT '0',
  sort int DEFAULT '0',
  created_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status int DEFAULT '1',
  is_delete int DEFAULT '0',
  PRIMARY KEY (id)
);

comment on column sys_resources.icon is '资源图标';
comment on column sys_resources.name is '资源名字';
comment on column sys_resources.authority is '权限';
comment on column sys_resources.url is '资源路径';
comment on column sys_resources.type is '资源类型（1:一级菜单，2:二级菜单，3:链接）';
comment on column sys_resources.parent_id is '父资源ID';
comment on column sys_resources.sort is '资源顺序';
comment on column sys_resources.created_time is '创建时间';
comment on column sys_resources.modified_time is '修改时间';
comment on column sys_resources.status is '1：正常';
comment on column sys_resources.is_delete is '是否删除';
-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO sys_resources VALUES ('1', '', 'Spring Security', 'index', '/index.html', '0', '0', '1', '2016-12-02 19:57:40', '2016-12-02 19:57:41', '1', '0');
INSERT INTO sys_resources VALUES ('812200892752023552', '&#xe616;', '系统管理', 'system_manager', '#', '1', '1', '1', '2016-12-23 15:39:17', '2016-12-23 15:39:17', '1', '0');
INSERT INTO sys_resources VALUES ('812201186059702272', '', '管理员列表', 'account_list', '/account/list.html', '2', '812200892752023552', '1', '2016-12-23 15:40:27', '2016-12-23 15:40:27', '1', '0');
INSERT INTO sys_resources VALUES ('812202086140563456', '', '角色列表', 'role_list', '/role/list.html', '2', '812200892752023552', '2', '2016-12-23 15:44:02', '2016-12-23 15:44:02', '1', '0');
INSERT INTO sys_resources VALUES ('812202453876166656', '', '资源管理', 'resource_list', '/resource/list.html', '2', '812200892752023552', '3', '2016-12-23 15:45:30', '2016-12-23 15:45:30', '1', '0');
INSERT INTO sys_resources VALUES ('813952217315639296', '', '添加用户', 'account_add', '/account/add', '3', '812201186059702272', '1', '2016-12-28 11:38:26', '2016-12-28 11:38:26', '1', '0');
INSERT INTO sys_resources VALUES ('813952387331751936', '', '修改用户', 'account_edit', '/account/edit', '3', '812201186059702272', '2', '2016-12-28 11:39:06', '2016-12-28 11:39:06', '1', '0');
INSERT INTO sys_resources VALUES ('813952734192304128', '', '启用', 'account_start', '/account/start.json', '3', '812201186059702272', '3', '2016-12-28 11:40:29', '2016-12-28 11:40:29', '1', '0');
INSERT INTO sys_resources VALUES ('813952861153886208', '', '禁用', 'account_stop', '/account/stop.json', '3', '812201186059702272', '4', '2016-12-28 11:40:59', '2016-12-28 14:55:58', '1', '0');
INSERT INTO sys_resources VALUES ('813952962828009472', '', '删除用户', 'account_remove', '/account/remove.json', '3', '812201186059702272', '5', '2016-12-28 11:41:24', '2016-12-28 11:41:24', '1', '0');
INSERT INTO sys_resources VALUES ('813953048727355392', '', '批量删除用户', 'account_removes', '/account/removes.json', '3', '812201186059702272', '6', '2016-12-28 11:41:44', '2016-12-28 11:41:44', '1', '0');
INSERT INTO sys_resources VALUES ('814011057037996032', '', '添加角色', 'role_add', '/role/add', '3', '812202086140563456', '1', '2016-12-28 15:32:14', '2016-12-28 15:32:14', '1', '0');
INSERT INTO sys_resources VALUES ('814011174847606784', '', '修改角色', 'role_edit', '/role/edit', '3', '812202086140563456', '2', '2016-12-28 15:32:42', '2016-12-28 15:32:42', '1', '0');
INSERT INTO sys_resources VALUES ('814011439399137280', '', '删除角色', 'role_remove', '/role/remove.json', '3', '812202086140563456', '3', '2016-12-28 15:33:45', '2016-12-28 15:33:45', '1', '0');
INSERT INTO sys_resources VALUES ('814011554264346624', '', '批量删除角色', 'role_removes', '/role/removes.json', '3', '812202086140563456', '4', '2016-12-28 15:34:13', '2016-12-28 15:34:13', '1', '0');
INSERT INTO sys_resources VALUES ('814011701585080320', '', '角色资源管理', 'role_resources', '/role/resources', '3', '812202086140563456', '5', '2016-12-28 15:34:48', '2016-12-28 15:34:48', '1', '0');
INSERT INTO sys_resources VALUES ('814014649241268224', '', '查看', 'resource_view', '/resource/view.html', '3', '812202453876166656', '1', '2016-12-28 15:46:31', '2016-12-28 15:46:31', '1', '0');
INSERT INTO sys_resources VALUES ('814016381337825280', '', '添加资源', 'resource_add', '/resource/add', '3', '812202453876166656', '2', '2016-12-28 15:53:24', '2016-12-28 15:53:24', '1', '0');
INSERT INTO sys_resources VALUES ('814017063524622336', '', '修改资源', 'resource_edit', '/resource/edit', '3', '812202453876166656', '3', '2016-12-28 15:56:06', '2016-12-28 15:56:06', '1', '0');
INSERT INTO sys_resources VALUES ('814017351400677376', '', '删除资源', 'resource_remove', '/resource/remove.json', '3', '812202453876166656', '4', '2016-12-28 15:57:15', '2016-12-28 15:57:15', '1', '0');
INSERT INTO sys_resources VALUES ('814018011839979520', '', '管理链接', 'resource_manager_link', '/resource/manager.html', '3', '812202453876166656', '5', '2016-12-28 15:59:52', '2016-12-28 15:59:52', '1', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id bigint NOT NULL,
  name varchar(24) DEFAULT '',
  name_remark varchar(24) DEFAULT '',
  created_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status int DEFAULT '1',
  is_delete int DEFAULT '0',
  PRIMARY KEY (id)
);

comment on column sys_role.name is '角色名字(英文）';
comment on column sys_role.name_remark is '角色描述';
comment on column sys_role.created_time is '创建时间';
comment on column sys_role.modified_time is '修改时间';
comment on column sys_role.status is '1：正常';
comment on column sys_role.is_delete is '是否删除';
-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO sys_role VALUES ('1', 'super_admin', '超级管理员', '2016-12-02 12:10:07', '2016-12-02 12:10:08', '1', '0');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS role_resources;
CREATE TABLE role_resources (
  id bigint NOT NULL,
  role_id bigint DEFAULT '0',
  resource_id bigint DEFAULT '0',
  created_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status int DEFAULT '1',
  is_delete int DEFAULT '0',
  PRIMARY KEY (id)
);

comment on column role_resources.role_id is '角色ID';
comment on column role_resources.resource_id is '资源ID';
comment on column role_resources.created_time is '创建时间';
comment on column role_resources.modified_time is '修改时间';
comment on column role_resources.status is '1：正常';
comment on column role_resources.is_delete is '是否删除';
-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO role_resources VALUES ('814018121185484800', '1', '812200892752023552', '2016-12-28 16:00:18', '2016-12-28 16:00:18', '1', '0');
INSERT INTO role_resources VALUES ('814018121407782912', '1', '812201186059702272', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018121680412672', '1', '813952217315639296', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018122024345600', '1', '813952387331751936', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018122301169664', '1', '813952734192304128', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018122565410816', '1', '813952861153886208', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018122838040576', '1', '813952962828009472', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018123098087424', '1', '813953048727355392', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018123370717184', '1', '812202086140563456', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018123639152640', '1', '814011057037996032', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018123911782400', '1', '814011174847606784', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018124184412160', '1', '814011439399137280', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018124448653312', '1', '814011554264346624', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018124721283072', '1', '814011701585080320', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018124989718528', '1', '812202453876166656', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018125253959680', '1', '814014649241268224', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO role_resources VALUES ('814018125518200832', '1', '814016381337825280', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO role_resources VALUES ('814018125803413504', '1', '814017063524622336', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO role_resources VALUES ('814018126071848960', '1', '814017351400677376', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO role_resources VALUES ('814018126336090112', '1', '814018011839979520', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
  id bigint NOT NULL,
  nick varchar(32) DEFAULT '',
  user_name varchar(24) DEFAULT '',
  user_pwd varchar(32) DEFAULT '',
  admin int DEFAULT '1',
  register_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status int DEFAULT '1',
  is_delete int DEFAULT '0',
  PRIMARY KEY (id)
);


comment on column sys_user.nick is '名字';
comment on column sys_user.user_name is '用户名';
comment on column sys_user.user_pwd is '密码';
comment on column sys_user.admin is '1：普通\r\n2：管理员';
comment on column sys_user.register_time is '注册时间';
comment on column sys_user.status is '1：正常\r\n2：停用';
comment on column sys_user.is_delete is '是否删除';
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO sys_user VALUES ('1', '许许', 'Admin', 'be05977add575832dc52655d4ad5c42e', '2', '2016-12-23 16:18:38', '2016-12-28 16:02:55', '1', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  id bigint NOT NULL,
  user_id bigint DEFAULT '0',
  role_id bigint DEFAULT '0',
  created_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  modified_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
  status int DEFAULT '1' ,
  is_delete int DEFAULT '0',
  PRIMARY KEY (id)
);

comment on column user_role.user_id is '用户ID';
comment on column user_role.role_id is '角色ID';
comment on column user_role.created_time is '创建时间';
comment on column user_role.modified_time is '修改时间';
comment on column user_role.status is '1：正常\r\n2：停用';
comment on column user_role.is_delete is '是否删除';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO user_role VALUES ('1', '1', '1', '2016-12-02 12:10:19', '2016-12-25 17:11:15', '1', '0');
