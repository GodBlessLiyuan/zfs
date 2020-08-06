/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.3
Source Server Version : 80020
Source Host           : 192.168.10.3:3306
Source Database       : smartzfs

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-08-06 09:09:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_register_user
-- ----------------------------
DROP TABLE IF EXISTS `m_register_user`;
CREATE TABLE `m_register_user` (
  `re_uid` bigint NOT NULL AUTO_INCREMENT,
  `phone` char(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `versionname` char(16) DEFAULT NULL,
  `buildrelease` char(16) DEFAULT NULL,
  `manufacturer` char(128) DEFAULT NULL,
  `androidmodel` char(64) DEFAULT NULL,
  PRIMARY KEY (`re_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_register_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `activity_id` int NOT NULL,
  `activityname` varchar(128) DEFAULT NULL,
  `position` tinyint DEFAULT NULL COMMENT '1 会员中心 ',
  `status` int DEFAULT NULL,
  `a_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  `com_type_id` int NOT NULL,
  `days` int DEFAULT NULL,
  `com_type_name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `source` int DEFAULT NULL COMMENT '1 活动赠送',
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `activity_id` (`activity_id`),
  KEY `a_id` (`a_id`),
  KEY `com_type_id` (`com_type_id`),
  CONSTRAINT `t_activity_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_activity_ibfk_2` FOREIGN KEY (`com_type_id`) REFERENCES `t_com_type` (`com_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES ('1', null, null, '1', '1', null, null, null, '1', '7', '周卡', '1');

-- ----------------------------
-- Table structure for t_ad_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_ad_channel`;
CREATE TABLE `t_ad_channel` (
  `ad_id` int NOT NULL,
  `soft_channel_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint DEFAULT '1' COMMENT '1 开始 2 关闭',
  `app_id` int NOT NULL,
  `dr` tinyint DEFAULT NULL COMMENT '1 未删除  2删除',
  KEY `ad_id` (`ad_id`),
  KEY `app_id` (`app_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  CONSTRAINT `t_ad_channel_ibfk_1` FOREIGN KEY (`ad_id`) REFERENCES `t_adconfig` (`ad_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_ad_channel_ibfk_2` FOREIGN KEY (`app_id`) REFERENCES `t_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_ad_channel_ibfk_3` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_ad_channel
-- ----------------------------

-- ----------------------------
-- Table structure for t_adconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_adconfig`;
CREATE TABLE `t_adconfig` (
  `ad_id` int NOT NULL AUTO_INCREMENT,
  `a_id` int NOT NULL,
  `ad_number` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `priority` tinyint DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `contacts` char(20) DEFAULT NULL,
  `total` tinyint DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1关闭 2开启 3 删除',
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`ad_id`),
  UNIQUE KEY `ad_id` (`ad_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_adconfig_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_adconfig
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_log`;
CREATE TABLE `t_admin_log` (
  `v_id` int NOT NULL AUTO_INCREMENT,
  `a_id` int NOT NULL,
  `action` varchar(64) DEFAULT NULL,
  `action_time` time DEFAULT NULL,
  PRIMARY KEY (`v_id`),
  UNIQUE KEY `v_id` (`v_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_admin_log_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `username` char(32) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `email` char(128) DEFAULT NULL,
  `is_lock` tinyint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  `role_id` int NOT NULL,
  `relation_a_id` int DEFAULT NULL,
  `name` char(64) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_admin_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', 'admin', 'b34232933f4816d8415b8d66379e28a4', null, null, null, null, '1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for t_ali_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_ali_feedback`;
CREATE TABLE `t_ali_feedback` (
  `notify_time` datetime DEFAULT NULL,
  `notify_type` char(64) DEFAULT NULL,
  `notify_id` char(128) DEFAULT NULL,
  `app_id` char(32) DEFAULT NULL,
  `version` char(3) DEFAULT NULL,
  `sign_type` char(10) DEFAULT NULL,
  `trade_no` char(64) DEFAULT NULL,
  `out_trade_no` char(64) DEFAULT NULL,
  `out_biz_no` char(64) DEFAULT NULL,
  `buyer_id` char(16) DEFAULT NULL,
  `buyer_logon_id` char(100) DEFAULT NULL,
  `seller_id` char(30) DEFAULT NULL,
  `seller_email` char(100) DEFAULT NULL,
  `trade_status` char(32) DEFAULT NULL,
  `total_amount` float(9,2) DEFAULT NULL,
  `receipt_amount` float(9,2) DEFAULT NULL,
  `invoice_amount` float(9,2) DEFAULT NULL,
  `buyer_pay_amount` float(9,2) DEFAULT NULL,
  `point_amount` float(9,2) DEFAULT NULL,
  `refund_fee` float(9,2) DEFAULT NULL,
  `subject` char(255) DEFAULT NULL,
  `body` char(255) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_payment` datetime DEFAULT NULL,
  `gmt_refund` datetime DEFAULT NULL,
  `gmt_close` datetime DEFAULT NULL,
  `fund_bill_list` char(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_ali_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `app_id` int NOT NULL AUTO_INCREMENT,
  `versionname` char(64) DEFAULT NULL,
  `versioncode` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '1 未发布 2 发布',
  `update_type` tinyint DEFAULT '1' COMMENT '1 普通更新 2 强制更新',
  `size` int DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  `md5` char(32) DEFAULT NULL,
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app
-- ----------------------------

-- ----------------------------
-- Table structure for t_app_ava_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_ava_ch`;
CREATE TABLE `t_app_ava_ch` (
  `aac_id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` int NOT NULL,
  `soft_channel_id` int NOT NULL,
  `avatar_id` bigint NOT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 未发布 2 发布',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`aac_id`),
  UNIQUE KEY `aac_id` (`aac_id`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `t_app_ava_ch_ibfk_1` FOREIGN KEY (`avatar_id`) REFERENCES `t_avatar` (`avatar_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_ava_ch
-- ----------------------------

-- ----------------------------
-- Table structure for t_app_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_ch`;
CREATE TABLE `t_app_ch` (
  `ac_id` int NOT NULL AUTO_INCREMENT,
  `status` tinyint DEFAULT NULL COMMENT '1 未发布  2 发布',
  `app_id` int NOT NULL,
  `soft_channel_id` int NOT NULL,
  PRIMARY KEY (`ac_id`),
  UNIQUE KEY `ac_id` (`ac_id`),
  KEY `app_id` (`app_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  CONSTRAINT `t_app_ch_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `t_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_app_ch_ibfk_2` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_ch
-- ----------------------------

-- ----------------------------
-- Table structure for t_app_plu_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_plu_ch`;
CREATE TABLE `t_app_plu_ch` (
  `apc_id` int NOT NULL AUTO_INCREMENT,
  `app_id` int NOT NULL,
  `soft_channel_id` int NOT NULL,
  `plugin_id` int NOT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 未发布 2 发布',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`apc_id`),
  UNIQUE KEY `apc_id` (`apc_id`),
  KEY `app_id` (`app_id`),
  KEY `plugin_id` (`plugin_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  CONSTRAINT `t_app_plu_ch_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `t_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_app_plu_ch_ibfk_2` FOREIGN KEY (`plugin_id`) REFERENCES `t_plugin` (`plugin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_app_plu_ch_ibfk_3` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_app_plu_ch
-- ----------------------------

-- ----------------------------
-- Table structure for t_avatar
-- ----------------------------
DROP TABLE IF EXISTS `t_avatar`;
CREATE TABLE `t_avatar` (
  `avatar_id` bigint NOT NULL AUTO_INCREMENT,
  `version_name` char(64) DEFAULT NULL,
  `version_code` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 未发布  2 发布',
  `update_type` tinyint DEFAULT '1' COMMENT '1 不同更新 2 强制更新',
  `size` int DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT NULL COMMENT '1 未删除  2删除',
  `md5` char(32) DEFAULT NULL,
  `a_id` int NOT NULL,
  `os_version` int DEFAULT NULL,
  PRIMARY KEY (`avatar_id`),
  UNIQUE KEY `avatar_id` (`avatar_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_avatar_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_avatar
-- ----------------------------

-- ----------------------------
-- Table structure for t_bannerconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_bannerconfig`;
CREATE TABLE `t_bannerconfig` (
  `banner_id` int NOT NULL AUTO_INCREMENT,
  `a_id` int NOT NULL,
  `name` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1关闭 2开启 3 删除',
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  `start_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `pic_path` char(255) DEFAULT NULL,
  PRIMARY KEY (`banner_id`),
  UNIQUE KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_bannerconfig
-- ----------------------------

-- ----------------------------
-- Table structure for t_batch_info
-- ----------------------------
DROP TABLE IF EXISTS `t_batch_info`;
CREATE TABLE `t_batch_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vipkey` char(16) DEFAULT NULL,
  `batch_id` int NOT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 未激活 2 激活  3 冻结  4 失效',
  `days` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `t_batch_info_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `t_ch_batch` (`batch_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_batch_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_black_app
-- ----------------------------
DROP TABLE IF EXISTS `t_black_app`;
CREATE TABLE `t_black_app` (
  `black_id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `package_name` char(32) DEFAULT NULL,
  `app_name` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int NOT NULL,
  PRIMARY KEY (`black_id`),
  UNIQUE KEY `black_id` (`black_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_black_app_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_black_app
-- ----------------------------

-- ----------------------------
-- Table structure for t_blank_app
-- ----------------------------
DROP TABLE IF EXISTS `t_blank_app`;
CREATE TABLE `t_blank_app` (
  `blank_id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `package_name` char(32) DEFAULT NULL,
  `app_name` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int NOT NULL,
  PRIMARY KEY (`blank_id`),
  UNIQUE KEY `blank_id` (`blank_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_blank_app_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_blank_app
-- ----------------------------

-- ----------------------------
-- Table structure for t_ch_batch
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_batch`;
CREATE TABLE `t_ch_batch` (
  `batch_id` int NOT NULL AUTO_INCREMENT,
  `num` int DEFAULT NULL,
  `a_id` int NOT NULL,
  `com_type_id` int NOT NULL,
  `chan_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 正常 2 未激活  3 冻结 4 失效 5 结束',
  `com_type_name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `dr` tinyint DEFAULT NULL COMMENT '1 未删除  2删除',
  `extra` char(255) DEFAULT NULL,
  `update_a_id` int DEFAULT NULL,
  `days` int DEFAULT NULL,
  `active_sync` tinyint DEFAULT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_id` (`batch_id`),
  KEY `chan_id` (`chan_id`),
  CONSTRAINT `t_ch_batch_ibfk_1` FOREIGN KEY (`chan_id`) REFERENCES `t_channel` (`chan_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_ch_batch
-- ----------------------------

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `chan_id` int NOT NULL AUTO_INCREMENT,
  `chan_nickname` char(20) DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `pro_id` int NOT NULL,
  `a_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`chan_id`),
  UNIQUE KEY `chan_id` (`chan_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `t_channel_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `t_promoter` (`pro_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_channel
-- ----------------------------

-- ----------------------------
-- Table structure for t_com_type
-- ----------------------------
DROP TABLE IF EXISTS `t_com_type`;
CREATE TABLE `t_com_type` (
  `com_type_id` int NOT NULL AUTO_INCREMENT,
  `name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `days` int DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`com_type_id`),
  UNIQUE KEY `com_type_id` (`com_type_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_com_type_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_com_type
-- ----------------------------
INSERT INTO `t_com_type` VALUES ('1', '周卡', '7', null, '1', '2019-11-06 12:30:02', '2019-11-06 12:30:04');

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `device_id` bigint NOT NULL AUTO_INCREMENT,
  `utdid` char(32) DEFAULT NULL,
  `androidid` char(32) DEFAULT NULL,
  `buildversion` tinyint DEFAULT NULL COMMENT 'android系统的版本号',
  `soft_channel_id` int DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `versioncode` int DEFAULT NULL,
  `manufacturer` char(128) DEFAULT NULL,
  `androidmodel` char(64) DEFAULT NULL,
  `uuid` char(32) DEFAULT NULL,
  `buildrelease` char(16) DEFAULT NULL,
  `versionname` char(16) DEFAULT NULL,
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `device_id` (`device_id`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=347 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_device
-- ----------------------------

-- ----------------------------
-- Table structure for t_device_imei
-- ----------------------------
DROP TABLE IF EXISTS `t_device_imei`;
CREATE TABLE `t_device_imei` (
  `device_id` bigint NOT NULL,
  `imei` char(32) NOT NULL,
  PRIMARY KEY (`imei`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `t_device_imei_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_device_imei
-- ----------------------------

-- ----------------------------
-- Table structure for t_device_statistics
-- ----------------------------
DROP TABLE IF EXISTS `t_device_statistics`;
CREATE TABLE `t_device_statistics` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `device_id` bigint NOT NULL,
  `visit_time` datetime DEFAULT NULL,
  `ip` char(128) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sid` (`sid`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `t_device_statistics_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_device_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for t_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_exception`;
CREATE TABLE `t_exception` (
  `exceptionid` int NOT NULL AUTO_INCREMENT,
  `device_id` bigint NOT NULL,
  `error` text,
  `buildversion` tinyint DEFAULT NULL COMMENT 'android系统的版本号',
  `versioncode` int DEFAULT NULL,
  `androidmodel` char(64) DEFAULT NULL,
  `pkg` char(32) DEFAULT NULL,
  `buildrelease` char(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exceptionid`),
  UNIQUE KEY `exceptionid` (`exceptionid`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `t_exception_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_exception
-- ----------------------------

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `user_device_id` int DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `contact` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `manufacturer` char(128) DEFAULT NULL,
  `androidmodel` char(64) DEFAULT NULL,
  `buildversion` tinyint DEFAULT NULL COMMENT 'android系统的版本号',
  `versioncode` int DEFAULT NULL,
  `buildrelease` char(16) DEFAULT NULL,
  `url1` char(255) DEFAULT NULL,
  `url2` char(255) DEFAULT NULL,
  `url3` char(255) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `feedback_id` (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for t_functionvideo
-- ----------------------------
DROP TABLE IF EXISTS `t_functionvideo`;
CREATE TABLE `t_functionvideo` (
  `function_id` int NOT NULL AUTO_INCREMENT,
  `fun_name` char(32) DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`),
  UNIQUE KEY `function_id` (`function_id`),
  UNIQUE KEY `fun_name` (`fun_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_functionvideo
-- ----------------------------

-- ----------------------------
-- Table structure for t_godinsec_user
-- ----------------------------
DROP TABLE IF EXISTS `t_godinsec_user`;
CREATE TABLE `t_godinsec_user` (
  `phone` char(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 未赠送  2 赠送',
  `name` char(64) NOT NULL COMMENT '产品名称',
  `end_time` datetime DEFAULT NULL COMMENT 'vip到期时间',
  `days` int DEFAULT '0' COMMENT '赠送天数',
  PRIMARY KEY (`phone`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_godinsec_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_invite_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_invite_detail`;
CREATE TABLE `t_invite_detail` (
  `inde_id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `com_type_id` int DEFAULT NULL,
  `com_type_name` char(128) DEFAULT NULL,
  `pay` bigint DEFAULT NULL,
  `earnings` bigint DEFAULT NULL,
  `proportion` tinyint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `invitee_id` bigint NOT NULL,
  `viptype_id` int DEFAULT '0',
  `pay_time` datetime DEFAULT NULL,
  `invite_id` int NOT NULL,
  PRIMARY KEY (`inde_id`),
  UNIQUE KEY `inde_id` (`inde_id`),
  KEY `invite_id` (`invite_id`),
  KEY `ix_invite_detail_userid` (`user_id` DESC) USING BTREE,
  CONSTRAINT `t_invite_detail_ibfk_1` FOREIGN KEY (`invite_id`) REFERENCES `t_invite_user` (`invite_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_invite_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_invite_user
-- ----------------------------
DROP TABLE IF EXISTS `t_invite_user`;
CREATE TABLE `t_invite_user` (
  `invite_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `invite_phone` char(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ip` char(128) DEFAULT NULL,
  PRIMARY KEY (`invite_id`),
  UNIQUE KEY `invite_id` (`invite_id`),
  UNIQUE KEY `invite_phone` (`invite_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_invite_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_key_text
-- ----------------------------
DROP TABLE IF EXISTS `t_key_text`;
CREATE TABLE `t_key_text` (
  `key_name` int NOT NULL,
  `text` text,
  PRIMARY KEY (`key_name`),
  UNIQUE KEY `key_name` (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_key_text
-- ----------------------------

-- ----------------------------
-- Table structure for t_key_value
-- ----------------------------
DROP TABLE IF EXISTS `t_key_value`;
CREATE TABLE `t_key_value` (
  `key_name` int NOT NULL,
  `value` char(255) DEFAULT NULL,
  PRIMARY KEY (`key_name`),
  UNIQUE KEY `key_name` (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_key_value
-- ----------------------------
INSERT INTO `t_key_value` VALUES ('1', '4');
INSERT INTO `t_key_value` VALUES ('2', 'https://mp.weixin.qq.com/s/an1ZP4Zop1ifr2Xbh4pD7Q');
INSERT INTO `t_key_value` VALUES ('10', '12');
INSERT INTO `t_key_value` VALUES ('11', '0nUArI5-H92DDqDeiU6uPSv0pWs9m2QF');
INSERT INTO `t_key_value` VALUES ('12', '砖助智能助手客服群1');
INSERT INTO `t_key_value` VALUES ('13', 'wx11243454634');
INSERT INTO `t_key_value` VALUES ('14', 'http://share.com.cn');
INSERT INTO `t_key_value` VALUES ('15', 'http://39.97.253.38/smartproblem/index.html');
INSERT INTO `t_key_value` VALUES ('16', 'http://39.97.253.38/share/v1.0/freemember');
INSERT INTO `t_key_value` VALUES ('17', 'http://39.97.253.38/share/v1.0/memberprotocol');
INSERT INTO `t_key_value` VALUES ('18', 'http://39.97.253.38/share/v1.0/protocol');
INSERT INTO `t_key_value` VALUES ('19', 'http://39.97.253.38/share/v1.0/memberrights');
INSERT INTO `t_key_value` VALUES ('20', 'http://39.97.253.38/share/v1.0/clearfans');
INSERT INTO `t_key_value` VALUES ('21', 'http://39.97.253.38/share/v1.0/privacy_protocol');

-- ----------------------------
-- Table structure for t_new_user_record
-- ----------------------------
DROP TABLE IF EXISTS `t_new_user_record`;
CREATE TABLE `t_new_user_record` (
  `nur_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `user_device_id` int DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `nug_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`nur_id`),
  UNIQUE KEY `nur_id` (`nur_id`),
  KEY `nug_id` (`nug_id`),
  CONSTRAINT `t_new_user_record_ibfk_1` FOREIGN KEY (`nug_id`) REFERENCES `t_user_gifts` (`nug_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_new_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '1文本 2 图片 3 文本加图片',
  `show_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `url` text,
  `status` int DEFAULT NULL COMMENT '1 未发布 2 发布',
  `end_time` datetime DEFAULT NULL,
  `picurl` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  PRIMARY KEY (`notice_id`),
  UNIQUE KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_number` char(32) NOT NULL,
  `user_device_id` int NOT NULL,
  `cmdy_id` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '1 微信 2支付宝',
  `days` int DEFAULT NULL,
  `pay` bigint DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 未支付  2 支付成功',
  `revenue` tinyint DEFAULT '1' COMMENT '1 未分成  2 已经计算过分成',
  PRIMARY KEY (`order_id`,`order_number`),
  UNIQUE KEY `order_id` (`order_id`),
  UNIQUE KEY `order_number` (`order_number`),
  KEY `user_device_id` (`user_device_id`),
  KEY `cmdy_id` (`cmdy_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_device_id`) REFERENCES `t_user_device` (`user_device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`cmdy_id`) REFERENCES `t_vipcommodity` (`cmdy_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=462 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_other_app
-- ----------------------------
DROP TABLE IF EXISTS `t_other_app`;
CREATE TABLE `t_other_app` (
  `o_id` int NOT NULL AUTO_INCREMENT,
  `o_name` char(64) DEFAULT NULL,
  `icon_url` char(255) DEFAULT NULL,
  `app_url` char(255) DEFAULT NULL,
  `download_type` tinyint DEFAULT NULL COMMENT '1 直接下载  2应用市场',
  `a_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id` (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_other_app
-- ----------------------------

-- ----------------------------
-- Table structure for t_phone_info
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_info`;
CREATE TABLE `t_phone_info` (
  `m_re_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `model_id` bigint unsigned NOT NULL,
  `product_model` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `product_device` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `buildid` char(32) DEFAULT NULL,
  `product_board` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `product_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `manufacturer` char(32) DEFAULT NULL,
  `product_cuptsm` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `proc_version` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `board_plat_form` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lcd_density` int DEFAULT NULL,
  `versdk` char(64) DEFAULT NULL,
  `fingerprint` char(64) DEFAULT NULL,
  `pinpai` char(32) DEFAULT NULL,
  `cpu_process_num` bigint DEFAULT NULL,
  `xinghao` char(32) DEFAULT NULL,
  `net_host_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`m_re_id`),
  KEY `model_id` (`product_model`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_phone_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_phone_model
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_model`;
CREATE TABLE `t_phone_model` (
  `model_id` bigint NOT NULL AUTO_INCREMENT,
  `type_id` int NOT NULL,
  `name` char(128) DEFAULT NULL,
  `picture` char(255) DEFAULT NULL,
  `dr` tinyint DEFAULT NULL COMMENT '1 未删除  2删除',
  PRIMARY KEY (`model_id`),
  UNIQUE KEY `model_id` (`model_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `t_phone_model_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_phone_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_phone_model
-- ----------------------------
INSERT INTO `t_phone_model` VALUES ('1', '1', 'Mate 10', null, '1');
INSERT INTO `t_phone_model` VALUES ('2', '1', 'Mate 10 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('3', '1', 'Mate 10 保时捷版', null, '1');
INSERT INTO `t_phone_model` VALUES ('4', '1', 'Mate 20', null, '1');
INSERT INTO `t_phone_model` VALUES ('5', '1', 'Mate 20 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('6', '1', 'Mate 20 X', null, '1');
INSERT INTO `t_phone_model` VALUES ('7', '1', 'mate 30', null, '1');
INSERT INTO `t_phone_model` VALUES ('8', '1', 'mate 30 pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('9', '1', 'Mate 30 Pro 5G', null, '1');
INSERT INTO `t_phone_model` VALUES ('10', '1', 'Mate 9 保时捷版', null, '1');
INSERT INTO `t_phone_model` VALUES ('11', '1', 'Mate20 RS保时捷设计', null, '1');
INSERT INTO `t_phone_model` VALUES ('12', '1', 'Mate30 RS 保时捷设计', null, '1');
INSERT INTO `t_phone_model` VALUES ('13', '1', 'nova 4', null, '1');
INSERT INTO `t_phone_model` VALUES ('14', '1', 'nova 5 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('15', '1', 'nova6 5G', null, '1');
INSERT INTO `t_phone_model` VALUES ('16', '1', 'P10', null, '1');
INSERT INTO `t_phone_model` VALUES ('17', '1', 'P10 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('18', '1', 'P20', null, '1');
INSERT INTO `t_phone_model` VALUES ('19', '1', 'P20 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('20', '1', 'P30', null, '1');
INSERT INTO `t_phone_model` VALUES ('21', '1', 'P30 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('22', '1', '华为RS 保时捷版', null, '1');
INSERT INTO `t_phone_model` VALUES ('23', '1', '畅享9 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('24', '1', '荣耀 V10', null, '1');
INSERT INTO `t_phone_model` VALUES ('25', '1', '荣耀10 GT', null, '1');
INSERT INTO `t_phone_model` VALUES ('26', '1', '荣耀10青春版', null, '1');
INSERT INTO `t_phone_model` VALUES ('27', '1', '荣耀20', null, '1');
INSERT INTO `t_phone_model` VALUES ('28', '1', '荣耀20 PRO', null, '1');
INSERT INTO `t_phone_model` VALUES ('29', '1', '荣耀20S', null, '1');
INSERT INTO `t_phone_model` VALUES ('30', '1', '荣耀8X', null, '1');
INSERT INTO `t_phone_model` VALUES ('31', '1', '荣耀9', null, '1');
INSERT INTO `t_phone_model` VALUES ('32', '1', '荣耀9X', null, '1');
INSERT INTO `t_phone_model` VALUES ('33', '1', '荣耀9XPro', null, '1');
INSERT INTO `t_phone_model` VALUES ('34', '1', '荣耀Magic', null, '1');
INSERT INTO `t_phone_model` VALUES ('35', '1', '荣耀Magic2', null, '1');
INSERT INTO `t_phone_model` VALUES ('36', '1', '荣耀Note10', null, '1');
INSERT INTO `t_phone_model` VALUES ('37', '1', '荣耀Play', null, '1');
INSERT INTO `t_phone_model` VALUES ('38', '1', '荣耀V20', null, '1');
INSERT INTO `t_phone_model` VALUES ('39', '1', '荣耀V9', null, '1');
INSERT INTO `t_phone_model` VALUES ('40', '1', '麦芒7', null, '1');
INSERT INTO `t_phone_model` VALUES ('41', '2', 'A5', null, '1');
INSERT INTO `t_phone_model` VALUES ('42', '2', 'A7', null, '1');
INSERT INTO `t_phone_model` VALUES ('43', '2', 'A77', null, '1');
INSERT INTO `t_phone_model` VALUES ('44', '2', 'A7x', null, '1');
INSERT INTO `t_phone_model` VALUES ('45', '2', 'Find X', null, '1');
INSERT INTO `t_phone_model` VALUES ('46', '2', 'Find X 兰博基尼版', null, '1');
INSERT INTO `t_phone_model` VALUES ('47', '2', 'K1', null, '1');
INSERT INTO `t_phone_model` VALUES ('48', '2', 'OPPO A11x', null, '1');
INSERT INTO `t_phone_model` VALUES ('49', '2', 'OPPO Reno 10倍变焦', null, '1');
INSERT INTO `t_phone_model` VALUES ('50', '2', 'OPPO Reno Ace', null, '1');
INSERT INTO `t_phone_model` VALUES ('51', '2', 'OPPO Reno3', null, '1');
INSERT INTO `t_phone_model` VALUES ('52', '2', 'R11', null, '1');
INSERT INTO `t_phone_model` VALUES ('53', '2', 'R11 plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('54', '2', 'R11s', null, '1');
INSERT INTO `t_phone_model` VALUES ('55', '2', 'R15x', null, '1');
INSERT INTO `t_phone_model` VALUES ('56', '2', 'R17', null, '1');
INSERT INTO `t_phone_model` VALUES ('57', '2', 'R17 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('58', '2', 'Reno', null, '1');
INSERT INTO `t_phone_model` VALUES ('59', '3', 'iQOO', null, '1');
INSERT INTO `t_phone_model` VALUES ('60', '3', 'iQOO Monster', null, '1');
INSERT INTO `t_phone_model` VALUES ('61', '3', 'NEX 2', null, '1');
INSERT INTO `t_phone_model` VALUES ('62', '3', 'NEX双屏版', null, '1');
INSERT INTO `t_phone_model` VALUES ('63', '3', 'U1', null, '1');
INSERT INTO `t_phone_model` VALUES ('64', '3', 'VIVO iQOO Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('65', '3', 'VIVO NEX3', null, '1');
INSERT INTO `t_phone_model` VALUES ('66', '3', 'X20', null, '1');
INSERT INTO `t_phone_model` VALUES ('67', '3', 'X20 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('68', '3', 'X20A', null, '1');
INSERT INTO `t_phone_model` VALUES ('69', '3', 'X21A', null, '1');
INSERT INTO `t_phone_model` VALUES ('70', '3', 'X23', null, '1');
INSERT INTO `t_phone_model` VALUES ('71', '3', 'X27', null, '1');
INSERT INTO `t_phone_model` VALUES ('72', '3', 'X30 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('73', '3', 'X9', null, '1');
INSERT INTO `t_phone_model` VALUES ('74', '3', 'X9 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('75', '3', 'X9i', null, '1');
INSERT INTO `t_phone_model` VALUES ('76', '3', 'X9L', null, '1');
INSERT INTO `t_phone_model` VALUES ('77', '3', 'X9s', null, '1');
INSERT INTO `t_phone_model` VALUES ('78', '3', 'Xplay6', null, '1');
INSERT INTO `t_phone_model` VALUES ('79', '3', 'Y66', null, '1');
INSERT INTO `t_phone_model` VALUES ('80', '3', 'Y81s', null, '1');
INSERT INTO `t_phone_model` VALUES ('81', '3', 'Z1', null, '1');
INSERT INTO `t_phone_model` VALUES ('82', '3', 'Z3', null, '1');
INSERT INTO `t_phone_model` VALUES ('83', '4', 'Redmi K20pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('84', '4', '小米5X', null, '1');
INSERT INTO `t_phone_model` VALUES ('85', '4', '小米6', null, '1');
INSERT INTO `t_phone_model` VALUES ('86', '4', '小米6X', null, '1');
INSERT INTO `t_phone_model` VALUES ('87', '4', '小米8 SE', null, '1');
INSERT INTO `t_phone_model` VALUES ('88', '4', '小米8屏幕指纹版', null, '1');
INSERT INTO `t_phone_model` VALUES ('89', '4', '小米9', null, '1');
INSERT INTO `t_phone_model` VALUES ('90', '4', '小米9 pro 5G', null, '1');
INSERT INTO `t_phone_model` VALUES ('91', '4', '小米9 透明尊享版', null, '1');
INSERT INTO `t_phone_model` VALUES ('92', '4', '小米CC', null, '1');
INSERT INTO `t_phone_model` VALUES ('93', '4', '小米MIX 2S', null, '1');
INSERT INTO `t_phone_model` VALUES ('94', '4', '小米MIX Alpha', null, '1');
INSERT INTO `t_phone_model` VALUES ('95', '4', '小米MIX2', null, '1');
INSERT INTO `t_phone_model` VALUES ('96', '4', '小米MIX3', null, '1');
INSERT INTO `t_phone_model` VALUES ('97', '4', '红米4X', null, '1');
INSERT INTO `t_phone_model` VALUES ('98', '4', '红米Note 4X', null, '1');
INSERT INTO `t_phone_model` VALUES ('99', '4', '红米Note7', null, '1');
INSERT INTO `t_phone_model` VALUES ('100', '5', 'Galaxy A8s', null, '1');
INSERT INTO `t_phone_model` VALUES ('101', '5', 'Galaxy A9 Star', null, '1');
INSERT INTO `t_phone_model` VALUES ('102', '5', 'Galaxy Note10', null, '1');
INSERT INTO `t_phone_model` VALUES ('103', '5', 'Galaxy Note8', null, '1');
INSERT INTO `t_phone_model` VALUES ('104', '5', 'Galaxy Note9', null, '1');
INSERT INTO `t_phone_model` VALUES ('105', '5', 'Galaxy S10+', null, '1');
INSERT INTO `t_phone_model` VALUES ('106', '5', 'Galaxy S9+', null, '1');
INSERT INTO `t_phone_model` VALUES ('107', '5', 'W2019', null, '1');
INSERT INTO `t_phone_model` VALUES ('108', '5', '三星Note 8', null, '1');
INSERT INTO `t_phone_model` VALUES ('109', '5', '三星S10e', null, '1');
INSERT INTO `t_phone_model` VALUES ('110', '5', '三星领世旗舰8', null, '1');
INSERT INTO `t_phone_model` VALUES ('111', '6', '15', null, '1');
INSERT INTO `t_phone_model` VALUES ('112', '6', '16s', null, '1');
INSERT INTO `t_phone_model` VALUES ('113', '6', 'm6 note', null, '1');
INSERT INTO `t_phone_model` VALUES ('114', '6', 'Note9', null, '1');
INSERT INTO `t_phone_model` VALUES ('115', '6', 'PRO6 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('116', '6', 'PRO7', null, '1');
INSERT INTO `t_phone_model` VALUES ('117', '6', 'PRO7 Plus', null, '1');
INSERT INTO `t_phone_model` VALUES ('118', '6', '魅族 16th', null, '1');
INSERT INTO `t_phone_model` VALUES ('119', '6', '魅族 16X', null, '1');
INSERT INTO `t_phone_model` VALUES ('120', '6', '魅族 Note8', null, '1');
INSERT INTO `t_phone_model` VALUES ('121', '6', '魅族16s Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('122', '6', '魅族V8', null, '1');
INSERT INTO `t_phone_model` VALUES ('123', '6', '魅族X8', null, '1');
INSERT INTO `t_phone_model` VALUES ('124', '7', '6s Ρlus', null, '1');
INSERT INTO `t_phone_model` VALUES ('125', '7', 'iPhone 11', null, '1');
INSERT INTO `t_phone_model` VALUES ('126', '7', 'iPhone 11 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('127', '7', 'iPhone 11 Pro Max', null, '1');
INSERT INTO `t_phone_model` VALUES ('128', '7', 'iPhone X', null, '1');
INSERT INTO `t_phone_model` VALUES ('129', '7', 'iPhone XR', null, '1');
INSERT INTO `t_phone_model` VALUES ('130', '7', 'iPhone XS', null, '1');
INSERT INTO `t_phone_model` VALUES ('131', '7', 'iPhone XS Max', null, '1');
INSERT INTO `t_phone_model` VALUES ('132', '7', 'iΡhone 8', null, '1');
INSERT INTO `t_phone_model` VALUES ('133', '7', 'iΡhone 8 Ρlus', null, '1');
INSERT INTO `t_phone_model` VALUES ('134', '8', 'OnePlus 3', null, '1');
INSERT INTO `t_phone_model` VALUES ('135', '8', 'OnePlus 3T', null, '1');
INSERT INTO `t_phone_model` VALUES ('136', '8', 'OnePlus 5', null, '1');
INSERT INTO `t_phone_model` VALUES ('137', '8', 'OnePlus 5T', null, '1');
INSERT INTO `t_phone_model` VALUES ('138', '8', 'ONEPLUS 6', null, '1');
INSERT INTO `t_phone_model` VALUES ('139', '8', 'OnePlus 6T', null, '1');
INSERT INTO `t_phone_model` VALUES ('140', '8', 'OnePlus 7 Pro', null, '1');
INSERT INTO `t_phone_model` VALUES ('141', '8', 'OnePlus 7T Pro迈凯伦限定版', null, '1');
INSERT INTO `t_phone_model` VALUES ('142', '8', '一加7T', null, '1');
INSERT INTO `t_phone_model` VALUES ('143', '8', '一加7T Pro', null, '1');

-- ----------------------------
-- Table structure for t_phone_type
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_type`;
CREATE TABLE `t_phone_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `ename` char(32) DEFAULT NULL,
  `name` char(32) DEFAULT NULL,
  `picture` char(255) DEFAULT NULL,
  `dr` tinyint DEFAULT NULL COMMENT '1 未删除  2删除',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id` (`type_id`),
  UNIQUE KEY `ename` (`ename`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_phone_type
-- ----------------------------
INSERT INTO `t_phone_type` VALUES ('1', 'HUAWEI', '华为', null, '1');
INSERT INTO `t_phone_type` VALUES ('2', 'OPPO', 'OPPO', null, '1');
INSERT INTO `t_phone_type` VALUES ('3', 'VIVO', 'VIVO', null, '1');
INSERT INTO `t_phone_type` VALUES ('4', 'Xiaomi', '小米', null, '1');
INSERT INTO `t_phone_type` VALUES ('5', 'SAMSUNG', '三星', null, '1');
INSERT INTO `t_phone_type` VALUES ('6', 'MEIZU', '魅族', null, '1');
INSERT INTO `t_phone_type` VALUES ('7', 'APPLE', 'iPhone', null, '1');
INSERT INTO `t_phone_type` VALUES ('8', 'OnePlus', '一加', null, '1');

-- ----------------------------
-- Table structure for t_plugin
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin`;
CREATE TABLE `t_plugin` (
  `plugin_id` int NOT NULL AUTO_INCREMENT,
  `status` tinyint DEFAULT NULL COMMENT '1 未发布 2 发布',
  `context` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `size` int DEFAULT NULL,
  `md5` char(32) DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  `url` char(255) DEFAULT NULL,
  PRIMARY KEY (`plugin_id`),
  UNIQUE KEY `plugin_id` (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for t_promoter
-- ----------------------------
DROP TABLE IF EXISTS `t_promoter`;
CREATE TABLE `t_promoter` (
  `pro_id` int NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(64) DEFAULT NULL,
  `phone` char(12) DEFAULT NULL,
  `extra` varchar(128) DEFAULT NULL,
  `a_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`pro_id`),
  UNIQUE KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_promoter
-- ----------------------------

-- ----------------------------
-- Table structure for t_revenue_user
-- ----------------------------
DROP TABLE IF EXISTS `t_revenue_user`;
CREATE TABLE `t_revenue_user` (
  `user_id` bigint NOT NULL,
  `invite_count` int DEFAULT NULL,
  `pay_count` int DEFAULT NULL,
  `register_count` bigint DEFAULT NULL,
  `total_revenue` bigint DEFAULT NULL,
  `withdraw` bigint DEFAULT NULL,
  `withdraw_time` int DEFAULT NULL,
  `remaining` bigint DEFAULT NULL,
  `sharecode` char(32) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `shorturl` char(125) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `sharecode` (`sharecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_revenue_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `role_num` char(6) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', null, null, null, null);
INSERT INTO `t_role` VALUES ('2', '项目经理', null, null, null, null);
INSERT INTO `t_role` VALUES ('3', '产品', null, null, null, null);
INSERT INTO `t_role` VALUES ('4', '运营', null, null, null, null);

-- ----------------------------
-- Table structure for t_service_number
-- ----------------------------
DROP TABLE IF EXISTS `t_service_number`;
CREATE TABLE `t_service_number` (
  `svnm_id` bigint NOT NULL AUTO_INCREMENT,
  `kf` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` bigint unsigned DEFAULT NULL,
  `username` char(32) DEFAULT NULL,
  `dr` tinyint DEFAULT NULL COMMENT '1����ɾ����2��ɾ��',
  PRIMARY KEY (`svnm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_service_number
-- ----------------------------

-- ----------------------------
-- Table structure for t_share_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_share_activity`;
CREATE TABLE `t_share_activity` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `type` tinyint DEFAULT NULL COMMENT '1 文字 2 图片',
  `content` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int NOT NULL,
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `material_id` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_share_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_soft_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_soft_channel`;
CREATE TABLE `t_soft_channel` (
  `soft_channel_id` int NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`soft_channel_id`),
  UNIQUE KEY `soft_channel_id` (`soft_channel_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_soft_channel
-- ----------------------------
INSERT INTO `t_soft_channel` VALUES ('1', '所有', '所有', null, null);
INSERT INTO `t_soft_channel` VALUES ('2', 'vbooster', '官网', null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` char(32) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `ip` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `soft_channel_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_user_activity`;
CREATE TABLE `t_user_activity` (
  `u_a_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `time` time DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '当活动存在多个执行状态时，默认为1     10 通过  30 领取  20 驳回',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_device_id` int DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `a_id` int DEFAULT '0',
  `url` char(255) DEFAULT NULL,
  PRIMARY KEY (`u_a_id`),
  UNIQUE KEY `u_a_id` (`u_a_id`),
  KEY `activity_id` (`activity_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_user_activity_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_activity_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_device
-- ----------------------------
DROP TABLE IF EXISTS `t_user_device`;
CREATE TABLE `t_user_device` (
  `user_device_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `device_id` bigint NOT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 登录  2 登出',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_device_id`),
  UNIQUE KEY `user_device_id` (`user_device_id`),
  KEY `device_id` (`device_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_user_device_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_device_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_device
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_gifts
-- ----------------------------
DROP TABLE IF EXISTS `t_user_gifts`;
CREATE TABLE `t_user_gifts` (
  `nug_id` int NOT NULL AUTO_INCREMENT,
  `com_type_id` int NOT NULL,
  `com_type_name` char(128) DEFAULT NULL,
  `days` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 未开启 2 开启  3 删除',
  `a_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`nug_id`),
  UNIQUE KEY `nug_id` (`nug_id`),
  KEY `com_type_id` (`com_type_id`),
  CONSTRAINT `t_user_gifts_ibfk_1` FOREIGN KEY (`com_type_id`) REFERENCES `t_com_type` (`com_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_gifts
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_history
-- ----------------------------
DROP TABLE IF EXISTS `t_user_history`;
CREATE TABLE `t_user_history` (
  `user_device_id` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `updatetime` time DEFAULT NULL,
  PRIMARY KEY (`user_device_id`),
  UNIQUE KEY `user_device_id` (`user_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_user_notice`;
CREATE TABLE `t_user_notice` (
  `u_notice_id` bigint NOT NULL AUTO_INCREMENT,
  `user_device_id` int DEFAULT NULL COMMENT '允许为null',
  `notice_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `show_time` time DEFAULT NULL,
  PRIMARY KEY (`u_notice_id`),
  UNIQUE KEY `u_notice_id` (`u_notice_id`),
  KEY `notice_id` (`notice_id`),
  KEY `user_device_id` (`user_device_id`),
  CONSTRAINT `t_user_notice_ibfk_1` FOREIGN KEY (`notice_id`) REFERENCES `t_notice` (`notice_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_notice_ibfk_2` FOREIGN KEY (`user_device_id`) REFERENCES `t_user_device` (`user_device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_user_vip`;
CREATE TABLE `t_user_vip` (
  `user_id` bigint NOT NULL,
  `viptype_id` int NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 正常 2 过期',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `vcreate_time` datetime DEFAULT NULL,
  `vend_time` datetime DEFAULT NULL,
  `first_time` datetime DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  KEY `viptype_id` (`viptype_id`),
  CONSTRAINT `t_user_vip_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_vip_ibfk_2` FOREIGN KEY (`viptype_id`) REFERENCES `t_viptype` (`viptype_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user_vip
-- ----------------------------

-- ----------------------------
-- Table structure for t_vipcommodity
-- ----------------------------
DROP TABLE IF EXISTS `t_vipcommodity`;
CREATE TABLE `t_vipcommodity` (
  `cmdy_id` int NOT NULL AUTO_INCREMENT,
  `viptype_id` int NOT NULL,
  `com_type_id` int NOT NULL,
  `com_name` char(64) DEFAULT NULL,
  `price` char(16) DEFAULT NULL,
  `discount` bigint DEFAULT NULL,
  `position` tinyint DEFAULT NULL COMMENT '1 会员中心 ',
  `create_time` datetime DEFAULT NULL,
  `soft_channel_id` int NOT NULL,
  `name` char(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `days` int DEFAULT NULL,
  `a_id` int NOT NULL,
  `description` char(128) DEFAULT NULL,
  `show_discount` char(32) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 不上架 2 上架',
  `com_type_name` char(128) DEFAULT NULL,
  `istop` tinyint DEFAULT NULL COMMENT '1 未置顶  2 置顶',
  `comm_attr` tinyint DEFAULT NULL,
  PRIMARY KEY (`cmdy_id`),
  UNIQUE KEY `cmdy_id` (`cmdy_id`),
  KEY `a_id` (`a_id`),
  KEY `com_type_id` (`com_type_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  KEY `viptype_id` (`viptype_id`),
  CONSTRAINT `t_vipcommodity_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_vipcommodity_ibfk_2` FOREIGN KEY (`com_type_id`) REFERENCES `t_com_type` (`com_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_vipcommodity_ibfk_3` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_vipcommodity_ibfk_4` FOREIGN KEY (`viptype_id`) REFERENCES `t_viptype` (`viptype_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_vipcommodity
-- ----------------------------

-- ----------------------------
-- Table structure for t_viptype
-- ----------------------------
DROP TABLE IF EXISTS `t_viptype`;
CREATE TABLE `t_viptype` (
  `viptype_id` int NOT NULL,
  `vipname` char(32) DEFAULT NULL COMMENT '不同vip和年费vip',
  PRIMARY KEY (`viptype_id`),
  UNIQUE KEY `viptype_id` (`viptype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_viptype
-- ----------------------------
INSERT INTO `t_viptype` VALUES ('1', '非会员');
INSERT INTO `t_viptype` VALUES ('10', '会员');
INSERT INTO `t_viptype` VALUES ('20', '年费会员');

-- ----------------------------
-- Table structure for t_voice_share
-- ----------------------------
DROP TABLE IF EXISTS `t_voice_share`;
CREATE TABLE `t_voice_share` (
  `voice_id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `user_device_id` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `status` tinyint DEFAULT '1' COMMENT '1 初始阶段，未上传 2 上传文件中  3 完成  4 失败',
  `path` char(128) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `title` char(128) DEFAULT NULL,
  PRIMARY KEY (`voice_id`),
  UNIQUE KEY `voice_id` (`voice_id`),
  KEY `device_id` (`device_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_voice_share_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_voice_share_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_voice_share
-- ----------------------------

-- ----------------------------
-- Table structure for t_white_device
-- ----------------------------
DROP TABLE IF EXISTS `t_white_device`;
CREATE TABLE `t_white_device` (
  `device_id` bigint NOT NULL,
  `extra` char(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  UNIQUE KEY `device_id` (`device_id`),
  CONSTRAINT `t_white_device_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_white_device
-- ----------------------------

-- ----------------------------
-- Table structure for t_white_user
-- ----------------------------
DROP TABLE IF EXISTS `t_white_user`;
CREATE TABLE `t_white_user` (
  `user_id` bigint NOT NULL,
  `username` char(32) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `t_white_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_white_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_withdraw_user
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw_user`;
CREATE TABLE `t_withdraw_user` (
  `withdraw_id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `device_id` bigint DEFAULT NULL,
  `user_device_id` int DEFAULT NULL,
  `withdraw` bigint DEFAULT '1',
  `remaining` bigint DEFAULT NULL,
  `ali_account` char(64) DEFAULT NULL,
  `ali_name` char(64) DEFAULT NULL,
  `withdraw_time` int DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1 待审核  2 运营驳回 3 打款中  4 支付宝驳回  5 完成',
  `a_id` int DEFAULT NULL,
  PRIMARY KEY (`withdraw_id`),
  UNIQUE KEY `withdraw_id` (`withdraw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_withdraw_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_wx_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_feedback`;
CREATE TABLE `t_wx_feedback` (
  `wxpayid` bigint NOT NULL AUTO_INCREMENT,
  `return_code` char(16) DEFAULT NULL,
  `return_msg` char(128) DEFAULT NULL,
  `appid` char(32) DEFAULT NULL,
  `mch_id` char(32) DEFAULT NULL,
  `device_info` char(32) DEFAULT NULL,
  `nonce_str` char(32) DEFAULT NULL,
  `sign` char(32) DEFAULT NULL,
  `result_code` char(16) DEFAULT NULL,
  `err_code` char(32) DEFAULT NULL,
  `err_code_des` char(128) DEFAULT NULL,
  `openid` char(128) DEFAULT NULL,
  `is_subscribe` char(1) DEFAULT NULL,
  `trade_type` char(16) DEFAULT NULL,
  `bank_type` char(16) DEFAULT NULL,
  `total_fee` int DEFAULT NULL,
  `fee_type` char(8) DEFAULT NULL,
  `cash_fee` int DEFAULT NULL,
  `cash_fee_type` char(16) DEFAULT NULL,
  `coupon_fee` int DEFAULT NULL,
  `coupon_count` int DEFAULT NULL,
  `transaction_id` char(32) DEFAULT NULL,
  `out_trade_no` char(32) DEFAULT NULL,
  `attach` char(128) DEFAULT NULL,
  `time_end` char(14) DEFAULT NULL,
  PRIMARY KEY (`wxpayid`),
  UNIQUE KEY `wxpayid` (`wxpayid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_wx_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for t_wxsupport
-- ----------------------------
DROP TABLE IF EXISTS `t_wxsupport`;
CREATE TABLE `t_wxsupport` (
  `w_id` int NOT NULL AUTO_INCREMENT,
  `package_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  PRIMARY KEY (`w_id`),
  UNIQUE KEY `w_id` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_wxsupport
-- ----------------------------
