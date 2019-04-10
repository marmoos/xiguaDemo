/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : xigua

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-10 15:38:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `longUrl` varchar(255) DEFAULT NULL,
  `shortUrl` varchar(255) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('8', 'www.baidu.com', '1ycyx9', '3');
INSERT INTO `test` VALUES ('9', 'www.qq.com', 'z84qz0', '2');
INSERT INTO `test` VALUES ('10', 'www.sina.com', 'daff46', '3');
