/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : ospucenter

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-09 18:41:32
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
  PRIMARY KEY (`menu_id`),
  KEY `systemcode` (`systemcode`),
  CONSTRAINT `systemcode3` FOREIGN KEY (`systemcode`) REFERENCES `uc_system` (`SystemCode`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_menu
-- ----------------------------

-- ----------------------------
-- Table structure for uc_permission
-- ----------------------------
DROP TABLE IF EXISTS `uc_permission`;
CREATE TABLE `uc_permission` (
  `Permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `Permission_Type` varchar(32) NOT NULL,
  `Menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Permission_id`),
  KEY `Menu_id` (`Menu_id`),
  CONSTRAINT `Menu_id` FOREIGN KEY (`Menu_id`) REFERENCES `uc_menu` (`menu_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_permission
-- ----------------------------

-- ----------------------------
-- Table structure for uc_permission_action
-- ----------------------------
DROP TABLE IF EXISTS `uc_permission_action`;
CREATE TABLE `uc_permission_action` (
  `Permission_id` int(11) NOT NULL,
  `Action_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_permission_action
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_role
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_user
-- ----------------------------

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



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uc_rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `uc_rolepermission`;
CREATE TABLE `uc_rolepermission` (
  `roleid` int(11) NOT NULL,
  `permission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_rolepermission
-- ----------------------------

