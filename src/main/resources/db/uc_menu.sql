/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : ospucenter

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-16 08:48:21
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
