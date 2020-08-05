/*
Navicat MySQL Data Transfer

Source Server         : 39.97.253.38
Source Server Version : 80018
Source Host           : 39.97.253.38:3306
Source Database       : smartdkfs

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-08-05 16:32:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_register_user
-- ----------------------------
DROP TABLE IF EXISTS `m_register_user`;
CREATE TABLE `m_register_user` (
  `re_uid` bigint(20) NOT NULL AUTO_INCREMENT,
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
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `activity_id` int(11) NOT NULL,
  `activityname` varchar(128) DEFAULT NULL,
  `position` tinyint(4) DEFAULT NULL COMMENT '1 会员中心 ',
  `status` int(11) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  `com_type_id` int(11) NOT NULL,
  `days` int(11) DEFAULT NULL,
  `com_type_name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `source` int(11) DEFAULT NULL COMMENT '1 活动赠送',
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `activity_id` (`activity_id`),
  KEY `a_id` (`a_id`),
  KEY `com_type_id` (`com_type_id`),
  CONSTRAINT `t_activity_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_activity_ibfk_2` FOREIGN KEY (`com_type_id`) REFERENCES `t_com_type` (`com_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_ad_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_ad_channel`;
CREATE TABLE `t_ad_channel` (
  `ad_id` int(11) NOT NULL,
  `soft_channel_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '1 开始 2 关闭',
  `app_id` int(11) NOT NULL,
  `dr` tinyint(4) DEFAULT NULL COMMENT '1 未删除  2删除',
  KEY `ad_id` (`ad_id`),
  KEY `app_id` (`app_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  CONSTRAINT `t_ad_channel_ibfk_1` FOREIGN KEY (`ad_id`) REFERENCES `t_adconfig` (`ad_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_ad_channel_ibfk_2` FOREIGN KEY (`app_id`) REFERENCES `t_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_ad_channel_ibfk_3` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_adconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_adconfig`;
CREATE TABLE `t_adconfig` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) NOT NULL,
  `ad_number` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `priority` tinyint(4) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `contacts` char(20) DEFAULT NULL,
  `total` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1关闭 2开启 3 删除',
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`ad_id`),
  UNIQUE KEY `ad_id` (`ad_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_adconfig_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_log`;
CREATE TABLE `t_admin_log` (
  `v_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) NOT NULL,
  `action` varchar(64) DEFAULT NULL,
  `action_time` time DEFAULT NULL,
  PRIMARY KEY (`v_id`),
  UNIQUE KEY `v_id` (`v_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_admin_log_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(32) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `email` char(128) DEFAULT NULL,
  `is_lock` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  `role_id` int(11) NOT NULL,
  `relation_a_id` int(11) DEFAULT NULL,
  `name` char(64) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_admin_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `versionname` char(64) DEFAULT NULL,
  `versioncode` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1 未发布 2 发布',
  `update_type` tinyint(4) DEFAULT '1' COMMENT '1 普通更新 2 强制更新',
  `size` int(11) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  `md5` char(32) DEFAULT NULL,
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_app_ava_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_ava_ch`;
CREATE TABLE `t_app_ava_ch` (
  `aac_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL,
  `soft_channel_id` int(11) NOT NULL,
  `avatar_id` bigint(20) NOT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 未发布 2 发布',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`aac_id`),
  UNIQUE KEY `aac_id` (`aac_id`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `t_app_ava_ch_ibfk_1` FOREIGN KEY (`avatar_id`) REFERENCES `t_avatar` (`avatar_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_app_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_ch`;
CREATE TABLE `t_app_ch` (
  `ac_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 未发布  2 发布',
  `app_id` int(11) NOT NULL,
  `soft_channel_id` int(11) NOT NULL,
  PRIMARY KEY (`ac_id`),
  UNIQUE KEY `ac_id` (`ac_id`),
  KEY `app_id` (`app_id`),
  KEY `soft_channel_id` (`soft_channel_id`),
  CONSTRAINT `t_app_ch_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `t_app` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_app_ch_ibfk_2` FOREIGN KEY (`soft_channel_id`) REFERENCES `t_soft_channel` (`soft_channel_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_app_plu_ch
-- ----------------------------
DROP TABLE IF EXISTS `t_app_plu_ch`;
CREATE TABLE `t_app_plu_ch` (
  `apc_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL,
  `soft_channel_id` int(11) NOT NULL,
  `plugin_id` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 未发布 2 发布',
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
-- Table structure for t_avatar
-- ----------------------------
DROP TABLE IF EXISTS `t_avatar`;
CREATE TABLE `t_avatar` (
  `avatar_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version_name` char(64) DEFAULT NULL,
  `version_code` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 未发布  2 发布',
  `update_type` tinyint(4) DEFAULT '1' COMMENT '1 不同更新 2 强制更新',
  `size` int(11) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT NULL COMMENT '1 未删除  2删除',
  `md5` char(32) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `os_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`avatar_id`),
  UNIQUE KEY `avatar_id` (`avatar_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_avatar_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_bannerconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_bannerconfig`;
CREATE TABLE `t_bannerconfig` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) NOT NULL,
  `name` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1关闭 2开启 3 删除',
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  `start_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `pic_path` char(255) DEFAULT NULL,
  PRIMARY KEY (`banner_id`),
  UNIQUE KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_batch_info
-- ----------------------------
DROP TABLE IF EXISTS `t_batch_info`;
CREATE TABLE `t_batch_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vipkey` char(16) DEFAULT NULL,
  `batch_id` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 未激活 2 激活  3 冻结  4 失效',
  `days` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `t_batch_info_ibfk_1` FOREIGN KEY (`batch_id`) REFERENCES `t_ch_batch` (`batch_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`black_id`),
  UNIQUE KEY `black_id` (`black_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_black_app_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`blank_id`),
  UNIQUE KEY `blank_id` (`blank_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_blank_app_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for t_ch_batch
-- ----------------------------
DROP TABLE IF EXISTS `t_ch_batch`;
CREATE TABLE `t_ch_batch` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `com_type_id` int(11) NOT NULL,
  `chan_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 正常 2 未激活  3 冻结 4 失效 5 结束',
  `com_type_name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `dr` tinyint(4) DEFAULT NULL COMMENT '1 未删除  2删除',
  `extra` char(255) DEFAULT NULL,
  `update_a_id` int(11) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `active_sync` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_id` (`batch_id`),
  KEY `chan_id` (`chan_id`),
  CONSTRAINT `t_ch_batch_ibfk_1` FOREIGN KEY (`chan_id`) REFERENCES `t_channel` (`chan_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `chan_id` int(11) NOT NULL AUTO_INCREMENT,
  `chan_nickname` char(20) DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `pro_id` int(11) NOT NULL,
  `a_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`chan_id`),
  UNIQUE KEY `chan_id` (`chan_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `t_channel_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `t_promoter` (`pro_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_com_type
-- ----------------------------
DROP TABLE IF EXISTS `t_com_type`;
CREATE TABLE `t_com_type` (
  `com_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(128) DEFAULT NULL COMMENT '日卡，周卡，月卡，年卡',
  `days` int(11) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`com_type_id`),
  UNIQUE KEY `com_type_id` (`com_type_id`),
  KEY `a_id` (`a_id`),
  CONSTRAINT `t_com_type_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `t_admin_user` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `device_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `utdid` char(32) DEFAULT NULL,
  `androidid` char(32) DEFAULT NULL,
  `buildversion` tinyint(4) DEFAULT NULL COMMENT 'android系统的版本号',
  `soft_channel_id` int(11) DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `versioncode` int(11) DEFAULT NULL,
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
-- Table structure for t_device_imei
-- ----------------------------
DROP TABLE IF EXISTS `t_device_imei`;
CREATE TABLE `t_device_imei` (
  `device_id` bigint(20) NOT NULL,
  `imei` char(32) NOT NULL,
  PRIMARY KEY (`imei`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `t_device_imei_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_device_statistics
-- ----------------------------
DROP TABLE IF EXISTS `t_device_statistics`;
CREATE TABLE `t_device_statistics` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL,
  `visit_time` datetime DEFAULT NULL,
  `ip` char(128) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sid` (`sid`),
  KEY `device_id` (`device_id`),
  CONSTRAINT `t_device_statistics_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_exception`;
CREATE TABLE `t_exception` (
  `exceptionid` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL,
  `error` text,
  `buildversion` tinyint(4) DEFAULT NULL COMMENT 'android系统的版本号',
  `versioncode` int(11) DEFAULT NULL,
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
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `user_device_id` int(11) DEFAULT NULL,
  `context` char(255) DEFAULT NULL,
  `contact` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `manufacturer` char(128) DEFAULT NULL,
  `androidmodel` char(64) DEFAULT NULL,
  `buildversion` tinyint(4) DEFAULT NULL COMMENT 'android系统的版本号',
  `versioncode` int(11) DEFAULT NULL,
  `buildrelease` char(16) DEFAULT NULL,
  `url1` char(255) DEFAULT NULL,
  `url2` char(255) DEFAULT NULL,
  `url3` char(255) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `feedback_id` (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_functionvideo
-- ----------------------------
DROP TABLE IF EXISTS `t_functionvideo`;
CREATE TABLE `t_functionvideo` (
  `function_id` int(11) NOT NULL AUTO_INCREMENT,
  `fun_name` char(32) DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`function_id`),
  UNIQUE KEY `function_id` (`function_id`),
  UNIQUE KEY `fun_name` (`fun_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_godinsec_user
-- ----------------------------
DROP TABLE IF EXISTS `t_godinsec_user`;
CREATE TABLE `t_godinsec_user` (
  `phone` char(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 未赠送  2 赠送',
  `name` char(64) NOT NULL COMMENT '产品名称',
  `end_time` datetime DEFAULT NULL COMMENT 'vip到期时间',
  `days` int(11) DEFAULT '0' COMMENT '赠送天数',
  PRIMARY KEY (`phone`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_invite_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_invite_detail`;
CREATE TABLE `t_invite_detail` (
  `inde_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `com_type_id` int(11) DEFAULT NULL,
  `com_type_name` char(128) DEFAULT NULL,
  `pay` bigint(20) DEFAULT NULL,
  `earnings` bigint(20) DEFAULT NULL,
  `proportion` tinyint(4) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `invitee_id` bigint(20) NOT NULL,
  `viptype_id` int(11) DEFAULT '0',
  `pay_time` datetime DEFAULT NULL,
  `invite_id` int(11) NOT NULL,
  PRIMARY KEY (`inde_id`),
  UNIQUE KEY `inde_id` (`inde_id`),
  KEY `invite_id` (`invite_id`),
  KEY `ix_invite_detail_userid` (`user_id` DESC) USING BTREE,
  CONSTRAINT `t_invite_detail_ibfk_1` FOREIGN KEY (`invite_id`) REFERENCES `t_invite_user` (`invite_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_invite_user
-- ----------------------------
DROP TABLE IF EXISTS `t_invite_user`;
CREATE TABLE `t_invite_user` (
  `invite_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `invite_phone` char(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ip` char(128) DEFAULT NULL,
  PRIMARY KEY (`invite_id`),
  UNIQUE KEY `invite_id` (`invite_id`),
  UNIQUE KEY `invite_phone` (`invite_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_key_text
-- ----------------------------
DROP TABLE IF EXISTS `t_key_text`;
CREATE TABLE `t_key_text` (
  `key_name` int(11) NOT NULL,
  `text` text,
  PRIMARY KEY (`key_name`),
  UNIQUE KEY `key_name` (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_key_value
-- ----------------------------
DROP TABLE IF EXISTS `t_key_value`;
CREATE TABLE `t_key_value` (
  `key_name` int(11) NOT NULL,
  `value` char(255) DEFAULT NULL,
  PRIMARY KEY (`key_name`),
  UNIQUE KEY `key_name` (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_new_user_record
-- ----------------------------
DROP TABLE IF EXISTS `t_new_user_record`;
CREATE TABLE `t_new_user_record` (
  `nur_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `user_device_id` int(11) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `nug_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`nur_id`),
  UNIQUE KEY `nur_id` (`nur_id`),
  KEY `nug_id` (`nug_id`),
  CONSTRAINT `t_new_user_record_ibfk_1` FOREIGN KEY (`nug_id`) REFERENCES `t_user_gifts` (`nug_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1文本 2 图片 3 文本加图片',
  `show_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `url` text,
  `status` int(11) DEFAULT NULL COMMENT '1 未发布 2 发布',
  `end_time` datetime DEFAULT NULL,
  `picurl` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`notice_id`),
  UNIQUE KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` char(32) NOT NULL,
  `user_device_id` int(11) NOT NULL,
  `cmdy_id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 微信 2支付宝',
  `days` int(11) DEFAULT NULL,
  `pay` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 未支付  2 支付成功',
  `revenue` tinyint(4) DEFAULT '1' COMMENT '1 未分成  2 已经计算过分成',
  PRIMARY KEY (`order_id`,`order_number`),
  UNIQUE KEY `order_id` (`order_id`),
  UNIQUE KEY `order_number` (`order_number`),
  KEY `user_device_id` (`user_device_id`),
  KEY `cmdy_id` (`cmdy_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_device_id`) REFERENCES `t_user_device` (`user_device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`cmdy_id`) REFERENCES `t_vipcommodity` (`cmdy_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=462 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_other_app
-- ----------------------------
DROP TABLE IF EXISTS `t_other_app`;
CREATE TABLE `t_other_app` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `o_name` char(64) DEFAULT NULL,
  `icon_url` char(255) DEFAULT NULL,
  `app_url` char(255) DEFAULT NULL,
  `download_type` tinyint(4) DEFAULT NULL COMMENT '1 直接下载  2应用市场',
  `a_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id` (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_phone_info
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_info`;
CREATE TABLE `t_phone_info` (
  `m_re_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `model_id` bigint(20) unsigned NOT NULL,
  `product_model` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `product_device` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `buildid` char(32) DEFAULT NULL,
  `product_board` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `product_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `manufacturer` char(32) DEFAULT NULL,
  `product_cuptsm` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `proc_version` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `board_plat_form` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lcd_density` int(11) DEFAULT NULL,
  `versdk` char(64) DEFAULT NULL,
  `fingerprint` char(64) DEFAULT NULL,
  `pinpai` char(32) DEFAULT NULL,
  `cpu_process_num` bigint(20) DEFAULT NULL,
  `xinghao` char(32) DEFAULT NULL,
  `net_host_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`m_re_id`),
  KEY `model_id` (`product_model`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_phone_model
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_model`;
CREATE TABLE `t_phone_model` (
  `model_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `name` char(128) DEFAULT NULL,
  `picture` char(255) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT NULL COMMENT '1 未删除  2删除',
  PRIMARY KEY (`model_id`),
  UNIQUE KEY `model_id` (`model_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `t_phone_model_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_phone_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_phone_type
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_type`;
CREATE TABLE `t_phone_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `ename` char(32) DEFAULT NULL,
  `name` char(32) DEFAULT NULL,
  `picture` char(255) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT NULL COMMENT '1 未删除  2删除',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id` (`type_id`),
  UNIQUE KEY `ename` (`ename`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_plugin
-- ----------------------------
DROP TABLE IF EXISTS `t_plugin`;
CREATE TABLE `t_plugin` (
  `plugin_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 未发布 2 发布',
  `context` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `md5` char(32) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  `url` char(255) DEFAULT NULL,
  PRIMARY KEY (`plugin_id`),
  UNIQUE KEY `plugin_id` (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_promoter
-- ----------------------------
DROP TABLE IF EXISTS `t_promoter`;
CREATE TABLE `t_promoter` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(64) DEFAULT NULL,
  `phone` char(12) DEFAULT NULL,
  `extra` varchar(128) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`pro_id`),
  UNIQUE KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_revenue_user
-- ----------------------------
DROP TABLE IF EXISTS `t_revenue_user`;
CREATE TABLE `t_revenue_user` (
  `user_id` bigint(20) NOT NULL,
  `invite_count` int(11) DEFAULT NULL,
  `pay_count` int(11) DEFAULT NULL,
  `register_count` bigint(20) DEFAULT NULL,
  `total_revenue` bigint(20) DEFAULT NULL,
  `withdraw` bigint(20) DEFAULT NULL,
  `withdraw_time` int(11) DEFAULT NULL,
  `remaining` bigint(20) DEFAULT NULL,
  `sharecode` char(32) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `shorturl` char(125) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `sharecode` (`sharecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `role_num` char(6) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_share_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_share_activity`;
CREATE TABLE `t_share_activity` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '1 文字 2 图片',
  `content` char(255) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `material_id` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_soft_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_soft_channel`;
CREATE TABLE `t_soft_channel` (
  `soft_channel_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`soft_channel_id`),
  UNIQUE KEY `soft_channel_id` (`soft_channel_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(32) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `ip` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `chan_name` char(64) DEFAULT NULL,
  `soft_channel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_user_activity`;
CREATE TABLE `t_user_activity` (
  `u_a_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `time` time DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '当活动存在多个执行状态时，默认为1     10 通过  30 领取  20 驳回',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_device_id` int(11) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `a_id` int(11) DEFAULT '0',
  `url` char(255) DEFAULT NULL,
  PRIMARY KEY (`u_a_id`),
  UNIQUE KEY `u_a_id` (`u_a_id`),
  KEY `activity_id` (`activity_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_user_activity_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_activity_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_device
-- ----------------------------
DROP TABLE IF EXISTS `t_user_device`;
CREATE TABLE `t_user_device` (
  `user_device_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 登录  2 登出',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_device_id`),
  UNIQUE KEY `user_device_id` (`user_device_id`),
  KEY `device_id` (`device_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_user_device_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_device_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_gifts
-- ----------------------------
DROP TABLE IF EXISTS `t_user_gifts`;
CREATE TABLE `t_user_gifts` (
  `nug_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_type_id` int(11) NOT NULL,
  `com_type_name` char(128) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 未开启 2 开启  3 删除',
  `a_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `dr` tinyint(4) DEFAULT '1' COMMENT '1 未删除  2删除',
  PRIMARY KEY (`nug_id`),
  UNIQUE KEY `nug_id` (`nug_id`),
  KEY `com_type_id` (`com_type_id`),
  CONSTRAINT `t_user_gifts_ibfk_1` FOREIGN KEY (`com_type_id`) REFERENCES `t_com_type` (`com_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_history
-- ----------------------------
DROP TABLE IF EXISTS `t_user_history`;
CREATE TABLE `t_user_history` (
  `user_device_id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `updatetime` time DEFAULT NULL,
  PRIMARY KEY (`user_device_id`),
  UNIQUE KEY `user_device_id` (`user_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_user_notice`;
CREATE TABLE `t_user_notice` (
  `u_notice_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_device_id` int(11) DEFAULT NULL COMMENT '允许为null',
  `notice_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `show_time` time DEFAULT NULL,
  PRIMARY KEY (`u_notice_id`),
  UNIQUE KEY `u_notice_id` (`u_notice_id`),
  KEY `notice_id` (`notice_id`),
  KEY `user_device_id` (`user_device_id`),
  CONSTRAINT `t_user_notice_ibfk_1` FOREIGN KEY (`notice_id`) REFERENCES `t_notice` (`notice_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_user_notice_ibfk_2` FOREIGN KEY (`user_device_id`) REFERENCES `t_user_device` (`user_device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user_vip
-- ----------------------------
DROP TABLE IF EXISTS `t_user_vip`;
CREATE TABLE `t_user_vip` (
  `user_id` bigint(20) NOT NULL,
  `viptype_id` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 正常 2 过期',
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
-- Table structure for t_vipcommodity
-- ----------------------------
DROP TABLE IF EXISTS `t_vipcommodity`;
CREATE TABLE `t_vipcommodity` (
  `cmdy_id` int(11) NOT NULL AUTO_INCREMENT,
  `viptype_id` int(11) NOT NULL,
  `com_type_id` int(11) NOT NULL,
  `com_name` char(64) DEFAULT NULL,
  `price` char(16) DEFAULT NULL,
  `discount` bigint(20) DEFAULT NULL,
  `position` tinyint(4) DEFAULT NULL COMMENT '1 会员中心 ',
  `create_time` datetime DEFAULT NULL,
  `soft_channel_id` int(11) NOT NULL,
  `name` char(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `a_id` int(11) NOT NULL,
  `description` char(128) DEFAULT NULL,
  `show_discount` char(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 不上架 2 上架',
  `com_type_name` char(128) DEFAULT NULL,
  `istop` tinyint(4) DEFAULT NULL COMMENT '1 未置顶  2 置顶',
  `comm_attr` tinyint(4) DEFAULT NULL,
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
-- Table structure for t_viptype
-- ----------------------------
DROP TABLE IF EXISTS `t_viptype`;
CREATE TABLE `t_viptype` (
  `viptype_id` int(11) NOT NULL,
  `vipname` char(32) DEFAULT NULL COMMENT '不同vip和年费vip',
  PRIMARY KEY (`viptype_id`),
  UNIQUE KEY `viptype_id` (`viptype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_voice_share
-- ----------------------------
DROP TABLE IF EXISTS `t_voice_share`;
CREATE TABLE `t_voice_share` (
  `voice_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_device_id` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `url` char(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '1 初始阶段，未上传 2 上传文件中  3 完成  4 失败',
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
-- Table structure for t_white_device
-- ----------------------------
DROP TABLE IF EXISTS `t_white_device`;
CREATE TABLE `t_white_device` (
  `device_id` bigint(20) NOT NULL,
  `extra` char(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  UNIQUE KEY `device_id` (`device_id`),
  CONSTRAINT `t_white_device_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `t_device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_white_user
-- ----------------------------
DROP TABLE IF EXISTS `t_white_user`;
CREATE TABLE `t_white_user` (
  `user_id` bigint(20) NOT NULL,
  `username` char(32) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `t_white_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_withdraw_user
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw_user`;
CREATE TABLE `t_withdraw_user` (
  `withdraw_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `user_device_id` int(11) DEFAULT NULL,
  `withdraw` bigint(20) DEFAULT '1',
  `remaining` bigint(20) DEFAULT NULL,
  `ali_account` char(64) DEFAULT NULL,
  `ali_name` char(64) DEFAULT NULL,
  `withdraw_time` int(11) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 待审核  2 运营驳回 3 打款中  4 支付宝驳回  5 完成',
  `a_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`withdraw_id`),
  UNIQUE KEY `withdraw_id` (`withdraw_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_wx_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_feedback`;
CREATE TABLE `t_wx_feedback` (
  `wxpayid` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `total_fee` int(11) DEFAULT NULL,
  `fee_type` char(8) DEFAULT NULL,
  `cash_fee` int(11) DEFAULT NULL,
  `cash_fee_type` char(16) DEFAULT NULL,
  `coupon_fee` int(11) DEFAULT NULL,
  `coupon_count` int(11) DEFAULT NULL,
  `transaction_id` char(32) DEFAULT NULL,
  `out_trade_no` char(32) DEFAULT NULL,
  `attach` char(128) DEFAULT NULL,
  `time_end` char(14) DEFAULT NULL,
  PRIMARY KEY (`wxpayid`),
  UNIQUE KEY `wxpayid` (`wxpayid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_wxsupport
-- ----------------------------
DROP TABLE IF EXISTS `t_wxsupport`;
CREATE TABLE `t_wxsupport` (
  `w_id` int(11) NOT NULL AUTO_INCREMENT,
  `package_name` char(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `a_id` int(11) DEFAULT NULL,
  `extra` char(255) DEFAULT NULL,
  PRIMARY KEY (`w_id`),
  UNIQUE KEY `w_id` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
