use smarthelper;
SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t_user_activity;
DROP TABLE IF EXISTS t_activity;
DROP TABLE IF EXISTS t_ad_channel;
DROP TABLE IF EXISTS t_adconfig;
DROP TABLE IF EXISTS t_admin_log;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_vipcommodity;
DROP TABLE IF EXISTS t_user_vip;
DROP TABLE IF EXISTS t_viptype;
DROP TABLE IF EXISTS t_admin_user;
DROP TABLE IF EXISTS t_com_type;
DROP TABLE IF EXISTS t_device_imei;
DROP TABLE IF EXISTS t_exception;
DROP TABLE IF EXISTS t_user_notice;
DROP TABLE IF EXISTS t_notice;
DROP TABLE IF EXISTS t_user_device;
DROP TABLE IF EXISTS t_device;
DROP TABLE IF EXISTS t_key_text;
DROP TABLE IF EXISTS t_key_value;
DROP TABLE IF EXISTS t_order_feedback;
DROP TABLE IF EXISTS t_soft_channel;
DROP TABLE IF EXISTS t_whilte_user;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_user_history;




/* Create Tables */

CREATE TABLE t_activity
(
	activity_id int NOT NULL AUTO_INCREMENT,
	activityname varchar(128),
	positon int,
	state int,
	a_id int NOT NULL,
	position int,
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (activity_id),
	UNIQUE (activity_id),
	UNIQUE (a_id)
);


CREATE TABLE t_adconfig
(
	ad_id int NOT NULL AUTO_INCREMENT,
	a_id int NOT NULL,
	ad_number varchar(32),
	name varchar(32),
	priority tinyint,
	phone varchar(64),
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (ad_id),
	UNIQUE (ad_id),
	UNIQUE (a_id)
);


CREATE TABLE t_admin_log
(
	v_id int NOT NULL AUTO_INCREMENT,
	a_id int NOT NULL,
	action varchar(64),
	action_time time,
	PRIMARY KEY (v_id),
	UNIQUE (v_id),
	UNIQUE (a_id)
);


CREATE TABLE t_admin_user
(
	a_id int NOT NULL AUTO_INCREMENT,
	username varchar(32),
	password varchar(32),
	email varchar(32),
	role tinyint,
	is_lock tinyint,
	create_time time,
	last_time time,
	PRIMARY KEY (a_id),
	UNIQUE (a_id),
	UNIQUE (username)
);


CREATE TABLE t_ad_channel
(
	ad_id int NOT NULL,
	soft_channel_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	UNIQUE (ad_id),
	UNIQUE (soft_channel_id)
);


CREATE TABLE t_com_type
(
	com_type_id int NOT NULL AUTO_INCREMENT,
	-- 日卡，周卡，月卡，年卡
	name varchar(128) COMMENT '日卡，周卡，月卡，年卡',
	days int,
	PRIMARY KEY (com_type_id),
	UNIQUE (com_type_id)
);


CREATE TABLE t_device
(
	device_id bigint NOT NULL AUTO_INCREMENT,
	utdid varchar(32),
	androidid varchar(32),
	PRIMARY KEY (device_id),
	UNIQUE (device_id)
);


CREATE TABLE t_device_imei
(
	device_id bigint NOT NULL,
	imei char(32) NOT NULL,
	PRIMARY KEY (imei),
	UNIQUE (device_id)
);


CREATE TABLE t_exception
(
	exceptionid int NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	error text,
	PRIMARY KEY (exceptionid),
	UNIQUE (exceptionid),
	UNIQUE (device_id)
);


CREATE TABLE t_key_text
(
	key_name int NOT NULL,
	text text,
	PRIMARY KEY (key_name),
	UNIQUE (key_name)
);


CREATE TABLE t_key_value
(
	key_name int NOT NULL,
	value varchar(256),
	PRIMARY KEY (key_name),
	UNIQUE (key_name)
);


CREATE TABLE t_notice
(
	notice_id int NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	title varchar(128),
	text varchar(256),
	-- 1文本 2 图片 3 文本加图片
	type tinyint COMMENT '1文本 2 图片 3 文本加图片',
	show_time time,
	start_day time,
	url text,
	-- 1 关闭  2 开始 0 删除
	status int COMMENT '1 关闭  2 开始 0 删除',
	PRIMARY KEY (notice_id),
	UNIQUE (notice_id),
	UNIQUE (device_id)
);


CREATE TABLE t_order
(
	order_id int NOT NULL AUTO_INCREMENT,
	order_number varchar(32) NOT NULL,
	user_device_id int NOT NULL,
	cmdy_id int NOT NULL,
	user_id int,
	device_id bigint,
	createtime time,
	starttime time,
	endtime time,
	pay_time time,
	-- 1 微信 2支付宝
	-- 
	type int COMMENT '1 微信 2支付宝
',
	PRIMARY KEY (order_id, order_number),
	UNIQUE (order_id),
	UNIQUE (user_device_id),
	UNIQUE (cmdy_id)
);


CREATE TABLE t_order_feedback
(
	wxpayid bigint NOT NULL AUTO_INCREMENT,
	appid char(20),
	mch_id char(20),
	nonce_str char(32),
	sigin char(32),
	result_code char(16),
	err_code char(32),
	err_code_des char(128),
	openid char(128),
	is_subscribe char,
	trade_type char(16),
	bank_type char(32),
	total_fee int,
	fee_type char(16),
	cash_fee int,
	cash_fee_type char(16),
	-- 
	-- 
	-- 
	coupon_fee int COMMENT '

',
	coupon_count int,
	transaction_id char(32),
	out_trade_no char(32),
	attach char(64),
	time_end char(16),
	PRIMARY KEY (wxpayid),
	UNIQUE (wxpayid)
);


CREATE TABLE t_soft_channel
(
	soft_channel_id int NOT NULL,
	name char(20),
	extra varchar(120),
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (soft_channel_id),
	UNIQUE (soft_channel_id)
);


CREATE TABLE t_user
(
	username varchar(32),
	user_id bigint NOT NULL AUTO_INCREMENT,
	phone char(11),
	ip varchar(128),
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (user_id),
	UNIQUE (user_id)
);


CREATE TABLE t_user_activity
(
	u_a_id int NOT NULL AUTO_INCREMENT,
	activity_id int NOT NULL,
	user_id bigint NOT NULL,
	time time,
	-- 当活动存在多个执行状态时，默认为0
	status tinyint COMMENT '当活动存在多个执行状态时，默认为0',
	create_time datetime,
	update_time datetime,
	user_device_id int,
	device_id bigint,
	PRIMARY KEY (u_a_id),
	UNIQUE (u_a_id),
	UNIQUE (activity_id),
	UNIQUE (user_id)
);


CREATE TABLE t_user_device
(
	user_device_id int NOT NULL AUTO_INCREMENT,
	user_id bigint NOT NULL,
	device_id bigint NOT NULL,
	status int,
	create_time datetime,
	PRIMARY KEY (user_device_id),
	UNIQUE (user_device_id),
	UNIQUE (user_id),
	UNIQUE (device_id)
);


CREATE TABLE t_user_history
(
	user_device_id int NOT NULL,
	user_id bigint,
	device_id bigint,
	updatetime time,
	PRIMARY KEY (user_device_id),
	UNIQUE (user_device_id)
);


CREATE TABLE t_user_notice
(
	user_device_id int NOT NULL,
	notice_id int NOT NULL,
	user_id bigint,
	device_id bigint,
	time time,
	UNIQUE (user_device_id),
	UNIQUE (notice_id)
);


CREATE TABLE t_user_vip
(
	user_id bigint NOT NULL,
	viptype_id int NOT NULL,
	start_time datetime,
	end_time datetime,
	-- 1 正常 2 过期
	status tinyint COMMENT '1 正常 2 过期',
	create_time datetime,
	update_time datetime,
	UNIQUE (user_id),
	UNIQUE (viptype_id)
);


CREATE TABLE t_vipcommodity
(
	cmdy_id int NOT NULL AUTO_INCREMENT,
	viptype_id int NOT NULL,
	com_type_id int NOT NULL,
	com_name varchar(64),
	price int,
	discount float,
	positon int,
	create_time time,
	a_id int NOT NULL,
	soft_channel_id int NOT NULL,
	name char(20),
	update_time datetime,
	PRIMARY KEY (cmdy_id),
	UNIQUE (cmdy_id),
	UNIQUE (viptype_id),
	UNIQUE (com_type_id),
	UNIQUE (a_id),
	UNIQUE (soft_channel_id)
);


CREATE TABLE t_viptype
(
	viptype_id int NOT NULL,
	-- 不同vip和年费vip
	vipname varchar(32) COMMENT '不同vip和年费vip',
	a_id int NOT NULL,
	PRIMARY KEY (viptype_id),
	UNIQUE (viptype_id),
	UNIQUE (a_id)
);


CREATE TABLE t_whilte_user
(
	user_id bigint NOT NULL,
	username varchar(32),
	phone char(11),
	UNIQUE (user_id)
);



/* Create Foreign Keys */

ALTER TABLE t_user_activity
	ADD FOREIGN KEY (activity_id)
	REFERENCES t_activity (activity_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_ad_channel
	ADD FOREIGN KEY (ad_id)
	REFERENCES t_adconfig (ad_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_activity
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_adconfig
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_admin_log
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_vipcommodity
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_viptype
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_vipcommodity
	ADD FOREIGN KEY (com_type_id)
	REFERENCES t_com_type (com_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_device_imei
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_exception
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_notice
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_device
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_notice
	ADD FOREIGN KEY (notice_id)
	REFERENCES t_notice (notice_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_ad_channel
	ADD FOREIGN KEY (soft_channel_id)
	REFERENCES t_soft_channel (soft_channel_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_vipcommodity
	ADD FOREIGN KEY (soft_channel_id)
	REFERENCES t_soft_channel (soft_channel_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_activity
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_device
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_vip
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_whilte_user
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_order
	ADD FOREIGN KEY (user_device_id)
	REFERENCES t_user_device (user_device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_notice
	ADD FOREIGN KEY (user_device_id)
	REFERENCES t_user_device (user_device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_order
	ADD FOREIGN KEY (cmdy_id)
	REFERENCES t_vipcommodity (cmdy_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_vip
	ADD FOREIGN KEY (viptype_id)
	REFERENCES t_viptype (viptype_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_vipcommodity
	ADD FOREIGN KEY (viptype_id)
	REFERENCES t_viptype (viptype_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



