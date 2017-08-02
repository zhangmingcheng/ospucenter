/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-02 16:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL COMMENT '权限表',
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL COMMENT '资源代码',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'demo列表', 'demo:list', 'demo列表权限');
INSERT INTO `sys_permission` VALUES ('2', 'demo更新', 'demo:update', 'demo更新功能');
INSERT INTO `sys_permission` VALUES ('3', 'demo添加', 'demo:add', 'demo添加功能');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL COMMENT '角色表',
  `name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `code` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `type` int(11) DEFAULT '1' COMMENT '1系统角色；2其他的下发的角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', '超级管理员', '1');
INSERT INTO `sys_role` VALUES ('2', 'user', 'user', '成员用户', '0');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='角色授权';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL COMMENT '用户表',
  `login_name` varchar(64) CHARACTER SET utf8mb4 NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `salt` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT ' 加密参数',
  `addtime` timestamp NULL DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT '0' COMMENT '1禁用；0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin1', '4779e4a9903dfb08f9f877791c516a73', 'admin', '2017-07-27 13:40:52', '0');
INSERT INTO `sys_user` VALUES ('2', 'zivy', 'zivyiv', '90539cfde7843b59006e0db3bda19698', 'zivy', '2017-07-27 16:51:09', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `user_id` int(11) NOT NULL COMMENT '用户',
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for zz_demo
-- ----------------------------
DROP TABLE IF EXISTS `zz_demo`;
CREATE TABLE `zz_demo` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `addtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间',
  `disabled` tinyint(1) DEFAULT '0' COMMENT '0有效；1作废',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zz_demo
-- ----------------------------
INSERT INTO `zz_demo` VALUES ('1', 'a', '2017-07-10 11:48:28', '0');
INSERT INTO `zz_demo` VALUES ('2', 'b', '2017-07-10 11:48:30', '0');
INSERT INTO `zz_demo` VALUES ('3', 'c收到', '2017-07-13 09:52:31', '0');
INSERT INTO `zz_demo` VALUES ('4', 'aa', '2017-07-12 16:52:42', '0');
INSERT INTO `zz_demo` VALUES ('5', 'test', '2017-07-17 12:29:02', '0');
INSERT INTO `zz_demo` VALUES ('6', 'atest', '2017-07-17 12:30:31', '0');
INSERT INTO `zz_demo` VALUES ('7', 'tet', '2017-07-17 12:33:10', '0');
INSERT INTO `zz_demo` VALUES ('8', 'adfa', '2017-07-17 12:33:35', '0');
INSERT INTO `zz_demo` VALUES ('9', 'gfsdf', '2017-07-17 12:37:13', '0');
INSERT INTO `zz_demo` VALUES ('10', '我是谁。', '2017-07-18 10:52:01', '0');
INSERT INTO `zz_demo` VALUES ('11', '阿道夫', '2017-07-18 10:52:43', '0');
INSERT INTO `zz_demo` VALUES ('12', 'test', '2017-07-18 11:14:46', '0');
INSERT INTO `zz_demo` VALUES ('13', 't1', '2017-07-18 11:23:38', '0');
INSERT INTO `zz_demo` VALUES ('14', 't2', '2017-07-18 11:26:33', '0');
INSERT INTO `zz_demo` VALUES ('15', 't3', '2017-07-18 11:28:32', '0');
INSERT INTO `zz_demo` VALUES ('16', 't6', '2017-07-18 11:30:07', '0');
INSERT INTO `zz_demo` VALUES ('17', 't7', '2017-07-18 11:31:53', '0');
INSERT INTO `zz_demo` VALUES ('18', 'test8', '2017-07-18 11:39:52', '0');
INSERT INTO `zz_demo` VALUES ('19', 't9', '2017-07-18 11:39:59', '0');
INSERT INTO `zz_demo` VALUES ('22', 't91', '2017-07-19 11:09:01', '0');
INSERT INTO `zz_demo` VALUES ('23', 't9112z', '2017-07-19 11:16:08', '0');
