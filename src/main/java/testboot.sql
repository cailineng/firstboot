/*
MySQL Data Transfer
Source Host: localhost
Source Database: testboot
Target Host: localhost
Target Database: testboot
Date: 2018/1/5 23:54:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cat
-- ----------------------------
DROP TABLE IF EXISTS `cat`;
CREATE TABLE `cat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catAge` varchar(255) DEFAULT NULL,
  `catName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systempermission
-- ----------------------------
DROP TABLE IF EXISTS `systempermission`;
CREATE TABLE `systempermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systempermissionrole
-- ----------------------------
DROP TABLE IF EXISTS `systempermissionrole`;
CREATE TABLE `systempermissionrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `permissionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemrole
-- ----------------------------
DROP TABLE IF EXISTS `systemrole`;
CREATE TABLE `systemrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemroleuser
-- ----------------------------
DROP TABLE IF EXISTS `systemroleuser`;
CREATE TABLE `systemroleuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `systemUserId` int(11) DEFAULT NULL,
  `systemRoleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemuser
-- ----------------------------
DROP TABLE IF EXISTS `systemuser`;
CREATE TABLE `systemuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `psw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cat` VALUES ('1', '18', 'name');
INSERT INTO `cat` VALUES ('2', '18', 'chenshuwen');
INSERT INTO `cat` VALUES ('3', '16', 'chenshuwen');
INSERT INTO `cat` VALUES ('4', '18', 'chenshuwen2');
INSERT INTO `cat` VALUES ('5', '18', 'cai');
INSERT INTO `cat` VALUES ('6', '19', 'cai');
INSERT INTO `cat` VALUES ('16', '18', 'chen');
INSERT INTO `systempermission` VALUES ('1', 'ROLE_HELLO', 'ABel', '/hello', null);
INSERT INTO `systempermission` VALUES ('2', 'ROLE_HELLOINDEX', 'helloIndex', '/helloIndex', null);
INSERT INTO `systempermissionrole` VALUES ('1', '1', '1');
INSERT INTO `systempermissionrole` VALUES ('2', '2', '2');
INSERT INTO `systempermissionrole` VALUES ('3', '1', '2');
INSERT INTO `systemrole` VALUES ('1', 'ROLE_USER');
INSERT INTO `systemrole` VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `systemrole` VALUES ('3', ' ROLE_ADMINNENG');
INSERT INTO `systemroleuser` VALUES ('1', '1', '1');
INSERT INTO `systemroleuser` VALUES ('2', '2', '2');
INSERT INTO `systemuser` VALUES ('1', 'admin', '123456');
INSERT INTO `systemuser` VALUES ('2', 'cailineng', '123456');
