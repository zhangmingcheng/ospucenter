/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : uc_ucenter

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-24 18:33:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uc_action
-- ----------------------------
DROP TABLE IF EXISTS `uc_action`;
CREATE TABLE `uc_action` (
  `Action_Id` int(11) NOT NULL AUTO_INCREMENT,
  `action_Name` varchar(32) NOT NULL,
  `action_Code` varchar(32) NOT NULL,
  `action_parent` int(11) DEFAULT NULL,
  `action_preventUrl` varchar(64) NOT NULL,
  `systemcode` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Action_Id`),
  KEY `systemcode2` (`systemcode`),
  CONSTRAINT `systemcode2` FOREIGN KEY (`systemcode`) REFERENCES `uc_system` (`SystemCode`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_action
-- ----------------------------

-- ----------------------------
-- Table structure for uc_menu
-- ----------------------------
DROP TABLE IF EXISTS `uc_menu`;
CREATE TABLE `uc_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) NOT NULL,
  `menu_url` varchar(64) NOT NULL,
  `menu_parent` int(11) DEFAULT NULL,
  `systemcode` varchar(32) DEFAULT NULL,
  `menu_icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `systemcode` (`systemcode`),
  CONSTRAINT `systemcode3` FOREIGN KEY (`systemcode`) REFERENCES `uc_system` (`SystemCode`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_menu
-- ----------------------------
INSERT INTO `uc_menu` VALUES ('10', '权限管理', '/permiss/test', null, null, null);
INSERT INTO `uc_menu` VALUES ('11', '测试', '/ssss', null, null, null);
INSERT INTO `uc_menu` VALUES ('12', '测试2', '/sssss', null, null, null);
INSERT INTO `uc_menu` VALUES ('13', '测试3', '/ddd', null, null, null);

-- ----------------------------
-- Table structure for uc_permission
-- ----------------------------
DROP TABLE IF EXISTS `uc_permission`;
CREATE TABLE `uc_permission` (
  `Permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `Permission_Type` varchar(32) NOT NULL,
  `Menu_id` int(11) DEFAULT NULL,
  `Action_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Permission_id`),
  KEY `Menu_id` (`Menu_id`),
  KEY `Action_id` (`Action_id`),
  CONSTRAINT `Action_id` FOREIGN KEY (`Action_id`) REFERENCES `uc_action` (`Action_Id`) ON DELETE CASCADE ON UPDATE SET NULL,
  CONSTRAINT `Menu_id` FOREIGN KEY (`Menu_id`) REFERENCES `uc_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_permission
-- ----------------------------
INSERT INTO `uc_permission` VALUES ('2', 'MENU', '10', null);
INSERT INTO `uc_permission` VALUES ('3', 'MENU', '11', null);
INSERT INTO `uc_permission` VALUES ('4', 'MENU', '12', null);
INSERT INTO `uc_permission` VALUES ('5', 'MENU', null, null);

-- ----------------------------
-- Table structure for uc_role
-- ----------------------------
DROP TABLE IF EXISTS `uc_role`;
CREATE TABLE `uc_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_Name` varchar(64) NOT NULL,
  `SystemCode` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `SystemCode4` (`SystemCode`),
  CONSTRAINT `SystemCode4` FOREIGN KEY (`SystemCode`) REFERENCES `uc_system` (`SystemCode`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_role
-- ----------------------------
INSERT INTO `uc_role` VALUES ('1', '超级管理员', null);
INSERT INTO `uc_role` VALUES ('2', '普通用户', null);
INSERT INTO `uc_role` VALUES ('3', '权限中心', null);
INSERT INTO `uc_role` VALUES ('4', '管理员', null);

-- ----------------------------
-- Table structure for uc_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `uc_role_permission`;
CREATE TABLE `uc_role_permission` (
  `roleid` int(11) NOT NULL,
  `permission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_role_permission
-- ----------------------------
INSERT INTO `uc_role_permission` VALUES ('1', '2');
INSERT INTO `uc_role_permission` VALUES ('1', '3');
INSERT INTO `uc_role_permission` VALUES ('1', '4');
INSERT INTO `uc_role_permission` VALUES ('1', '5');

-- ----------------------------
-- Table structure for uc_system
-- ----------------------------
DROP TABLE IF EXISTS `uc_system`;
CREATE TABLE `uc_system` (
  `SystemCode` varchar(32) NOT NULL DEFAULT '',
  `SystemName` varchar(64) NOT NULL,
  PRIMARY KEY (`SystemCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_system
-- ----------------------------

-- ----------------------------
-- Table structure for uc_user
-- ----------------------------
DROP TABLE IF EXISTS `uc_user`;
CREATE TABLE `uc_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL,
  `user_pwd` varchar(64) NOT NULL,
  `systemcode` varchar(32) DEFAULT NULL,
  `create_time` varchar(64) DEFAULT NULL,
  `last_login_time` varchar(64) DEFAULT NULL,
  `user_email` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `systemcode` (`systemcode`),
  CONSTRAINT `systemcode` FOREIGN KEY (`systemcode`) REFERENCES `uc_system` (`SystemCode`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_user
-- ----------------------------
INSERT INTO `uc_user` VALUES ('1', 'zmcheng', '123456', null, null, null, null);
INSERT INTO `uc_user` VALUES ('2', 'lisi', '123456', null, null, null, null);
INSERT INTO `uc_user` VALUES ('3', 'wangwu', '123456', null, null, null, null);
INSERT INTO `uc_user` VALUES ('4', 'chen', '123456', null, null, null, null);

-- ----------------------------
-- Table structure for uc_user_role
-- ----------------------------
DROP TABLE IF EXISTS `uc_user_role`;
CREATE TABLE `uc_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_user_role
-- ----------------------------
INSERT INTO `uc_user_role` VALUES ('1', '1');
INSERT INTO `uc_user_role` VALUES ('1', '2');
