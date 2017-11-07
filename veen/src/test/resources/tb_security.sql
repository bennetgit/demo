/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tb_security

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-28 16:30:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT '0',
  `user_agent` varchar(128) DEFAULT '' COMMENT '浏览器标识',
  `ip_address` varchar(128) DEFAULT '' COMMENT 'IP地址',
  `type` int(11) DEFAULT '1' COMMENT '1：网页登录\r\n2：OpenId登录',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `modified_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1：正常',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of login
-- ----------------------------

-- ----------------------------
-- Table structure for logout
-- ----------------------------
DROP TABLE IF EXISTS `logout`;
CREATE TABLE `logout` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of logout
-- ----------------------------

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` bigint(20) NOT NULL,
  `icon` varchar(255) DEFAULT '' COMMENT '资源图标',
  `name` varchar(24) DEFAULT '' COMMENT '资源名字',
  `authority` varchar(64) DEFAULT '' COMMENT '权限',
  `url` varchar(255) DEFAULT '' COMMENT '资源路径',
  `type` int(11) DEFAULT '0' COMMENT '资源类型（1:一级菜单，2:二级菜单，3:链接）',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父资源ID',
  `sort` int(11) DEFAULT '0' COMMENT '资源顺序',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '1' COMMENT '1：正常',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '', 'Spring Security', 'index', '/index.html', '0', '0', '1', '2016-12-02 19:57:40', '2016-12-02 19:57:41', '1', '0');
INSERT INTO `resources` VALUES ('812200892752023552', '&#xe616;', '系统管理', 'system_manager', '#', '1', '1', '1', '2016-12-23 15:39:17', '2016-12-23 15:39:17', '1', '0');
INSERT INTO `resources` VALUES ('812201186059702272', '', '管理员列表', 'account_list', '/account/list.html', '2', '812200892752023552', '1', '2016-12-23 15:40:27', '2016-12-23 15:40:27', '1', '0');
INSERT INTO `resources` VALUES ('812202086140563456', '', '角色列表', 'role_list', '/role/list.html', '2', '812200892752023552', '2', '2016-12-23 15:44:02', '2016-12-23 15:44:02', '1', '0');
INSERT INTO `resources` VALUES ('812202453876166656', '', '资源管理', 'resource_list', '/resource/list.html', '2', '812200892752023552', '3', '2016-12-23 15:45:30', '2016-12-23 15:45:30', '1', '0');
INSERT INTO `resources` VALUES ('813952217315639296', '', '添加用户', 'account_add', '/account/add', '3', '812201186059702272', '1', '2016-12-28 11:38:26', '2016-12-28 11:38:26', '1', '0');
INSERT INTO `resources` VALUES ('813952387331751936', '', '修改用户', 'account_edit', '/account/edit', '3', '812201186059702272', '2', '2016-12-28 11:39:06', '2016-12-28 11:39:06', '1', '0');
INSERT INTO `resources` VALUES ('813952734192304128', '', '启用', 'account_start', '/account/start.json', '3', '812201186059702272', '3', '2016-12-28 11:40:29', '2016-12-28 11:40:29', '1', '0');
INSERT INTO `resources` VALUES ('813952861153886208', '', '禁用', 'account_stop', '/account/stop.json', '3', '812201186059702272', '4', '2016-12-28 11:40:59', '2016-12-28 14:55:58', '1', '0');
INSERT INTO `resources` VALUES ('813952962828009472', '', '删除用户', 'account_remove', '/account/remove.json', '3', '812201186059702272', '5', '2016-12-28 11:41:24', '2016-12-28 11:41:24', '1', '0');
INSERT INTO `resources` VALUES ('813953048727355392', '', '批量删除用户', 'account_removes', '/account/removes.json', '3', '812201186059702272', '6', '2016-12-28 11:41:44', '2016-12-28 11:41:44', '1', '0');
INSERT INTO `resources` VALUES ('814011057037996032', '', '添加角色', 'role_add', '/role/add', '3', '812202086140563456', '1', '2016-12-28 15:32:14', '2016-12-28 15:32:14', '1', '0');
INSERT INTO `resources` VALUES ('814011174847606784', '', '修改角色', 'role_edit', '/role/edit', '3', '812202086140563456', '2', '2016-12-28 15:32:42', '2016-12-28 15:32:42', '1', '0');
INSERT INTO `resources` VALUES ('814011439399137280', '', '删除角色', 'role_remove', '/role/remove.json', '3', '812202086140563456', '3', '2016-12-28 15:33:45', '2016-12-28 15:33:45', '1', '0');
INSERT INTO `resources` VALUES ('814011554264346624', '', '批量删除角色', 'role_removes', '/role/removes.json', '3', '812202086140563456', '4', '2016-12-28 15:34:13', '2016-12-28 15:34:13', '1', '0');
INSERT INTO `resources` VALUES ('814011701585080320', '', '角色资源管理', 'role_resources', '/role/resources', '3', '812202086140563456', '5', '2016-12-28 15:34:48', '2016-12-28 15:34:48', '1', '0');
INSERT INTO `resources` VALUES ('814014649241268224', '', '查看', 'resource_view', '/resource/view.html', '3', '812202453876166656', '1', '2016-12-28 15:46:31', '2016-12-28 15:46:31', '1', '0');
INSERT INTO `resources` VALUES ('814016381337825280', '', '添加资源', 'resource_add', '/resource/add', '3', '812202453876166656', '2', '2016-12-28 15:53:24', '2016-12-28 15:53:24', '1', '0');
INSERT INTO `resources` VALUES ('814017063524622336', '', '修改资源', 'resource_edit', '/resource/edit', '3', '812202453876166656', '3', '2016-12-28 15:56:06', '2016-12-28 15:56:06', '1', '0');
INSERT INTO `resources` VALUES ('814017351400677376', '', '删除资源', 'resource_remove', '/resource/remove.json', '3', '812202453876166656', '4', '2016-12-28 15:57:15', '2016-12-28 15:57:15', '1', '0');
INSERT INTO `resources` VALUES ('814018011839979520', '', '管理链接', 'resource_manager_link', '/resource/manager.html', '3', '812202453876166656', '5', '2016-12-28 15:59:52', '2016-12-28 15:59:52', '1', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(24) DEFAULT '' COMMENT '角色名字(英文）',
  `name_remark` varchar(24) DEFAULT '' COMMENT '角色描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '1' COMMENT '1：正常',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'super_admin', '超级管理员', '2016-12-02 12:10:07', '2016-12-02 12:10:08', '1', '0');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT '0' COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT '0' COMMENT '资源ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '1' COMMENT '1：正常',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('814018121185484800', '1', '812200892752023552', '2016-12-28 16:00:18', '2016-12-28 16:00:18', '1', '0');
INSERT INTO `role_resources` VALUES ('814018121407782912', '1', '812201186059702272', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018121680412672', '1', '813952217315639296', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018122024345600', '1', '813952387331751936', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018122301169664', '1', '813952734192304128', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018122565410816', '1', '813952861153886208', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018122838040576', '1', '813952962828009472', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018123098087424', '1', '813953048727355392', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018123370717184', '1', '812202086140563456', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018123639152640', '1', '814011057037996032', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018123911782400', '1', '814011174847606784', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018124184412160', '1', '814011439399137280', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018124448653312', '1', '814011554264346624', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018124721283072', '1', '814011701585080320', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018124989718528', '1', '812202453876166656', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018125253959680', '1', '814014649241268224', '2016-12-28 16:00:19', '2016-12-28 16:00:19', '1', '0');
INSERT INTO `role_resources` VALUES ('814018125518200832', '1', '814016381337825280', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO `role_resources` VALUES ('814018125803413504', '1', '814017063524622336', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO `role_resources` VALUES ('814018126071848960', '1', '814017351400677376', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');
INSERT INTO `role_resources` VALUES ('814018126336090112', '1', '814018011839979520', '2016-12-28 16:00:20', '2016-12-28 16:00:20', '1', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `nick` varchar(32) DEFAULT '' COMMENT '名字',
  `user_name` varchar(24) DEFAULT '' COMMENT '用户名',
  `user_pwd` varchar(32) DEFAULT '' COMMENT '密码',
  `admin` int(11) DEFAULT '1' COMMENT '1：普通\r\n2：管理员',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `modified_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '1：正常\r\n2：停用',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '许许', 'Admin', 'be05977add575832dc52655d4ad5c42e', '2', '2016-12-23 16:18:38', '2016-12-28 16:02:55', '1', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT '0' COMMENT '角色ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '1' COMMENT '1：正常',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2016-12-02 12:10:19', '2016-12-25 17:11:15', '1', '0');
