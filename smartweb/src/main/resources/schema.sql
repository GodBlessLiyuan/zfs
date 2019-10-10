use smarthelper;
SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t_user_activity;
DROP TABLE IF EXISTS t_activity;
DROP TABLE IF EXISTS t_ad_channel;
DROP TABLE IF EXISTS t_adconfig;
DROP TABLE IF EXISTS t_admin_log;
DROP TABLE IF EXISTS t_new_user_record;
DROP TABLE IF EXISTS t_user_gifts;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_vipcommodity;
DROP TABLE IF EXISTS t_com_type;
DROP TABLE IF EXISTS t_admin_user;
DROP TABLE IF EXISTS t_app_ch;
DROP TABLE IF EXISTS t_app_plu_ch;
DROP TABLE IF EXISTS t_app;
DROP TABLE IF EXISTS t_batch_info;
DROP TABLE IF EXISTS t_ch_batch;
DROP TABLE IF EXISTS t_channel;
DROP TABLE IF EXISTS t_device_imei;
DROP TABLE IF EXISTS t_exception;
DROP TABLE IF EXISTS t_user_notice;
DROP TABLE IF EXISTS t_user_device;
DROP TABLE IF EXISTS t_whilte_device;
DROP TABLE IF EXISTS t_device;
DROP TABLE IF EXISTS t_feedback;
DROP TABLE IF EXISTS t_functionvideo;
DROP TABLE IF EXISTS t_key_text;
DROP TABLE IF EXISTS t_key_value;
DROP TABLE IF EXISTS t_notice;
DROP TABLE IF EXISTS t_order_feedback;
DROP TABLE IF EXISTS t_other_app;
DROP TABLE IF EXISTS t_plugin;
DROP TABLE IF EXISTS t_promoter;
DROP TABLE IF EXISTS t_role;
DROP TABLE IF EXISTS t_share_activity;
DROP TABLE IF EXISTS t_soft_channel;
DROP TABLE IF EXISTS t_user_vip;
DROP TABLE IF EXISTS t_whilte_user;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_user_history;
DROP TABLE IF EXISTS t_viptype;
DROP TABLE IF EXISTS t_wxsupport;




/* Create Tables */

CREATE TABLE t_activity
(
	activity_id int NOT NULL,
	activityname varchar(128),
	-- 1 会员中心 
	position tinyint COMMENT '1 会员中心 ',
	status int,
	a_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	com_type_id int NOT NULL,
	days int,
	-- 日卡，周卡，月卡，年卡
	com_type_name char(128) COMMENT '日卡，周卡，月卡，年卡',
	-- 1 活动赠送
	source int COMMENT '1 活动赠送',
	PRIMARY KEY (activity_id),
	UNIQUE (activity_id)
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
	contacts char(20),
	total tinyint,
	-- 1关闭 2开启 3 删除
	status tinyint DEFAULT 1 COMMENT '1关闭 2开启 3 删除',
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
	PRIMARY KEY (ad_id),
	UNIQUE (ad_id)
);


CREATE TABLE t_admin_log
(
	v_id int NOT NULL AUTO_INCREMENT,
	a_id int NOT NULL,
	action varchar(64),
	action_time time,
	PRIMARY KEY (v_id),
	UNIQUE (v_id)
);


CREATE TABLE t_admin_user
(
	a_id int NOT NULL AUTO_INCREMENT,
	username varchar(32),
	password varchar(32),
	email varchar(32),
	is_lock tinyint,
	create_time time,
	last_time time,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	role_id int NOT NULL,
	PRIMARY KEY (a_id),
	UNIQUE (username)
);


CREATE TABLE t_ad_channel
(
	ad_id int NOT NULL,
	soft_channel_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 当前版本  2 历史版本 
	type tinyint COMMENT '1 当前版本  2 历史版本 ',
	app_id int NOT NULL,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除'
);


CREATE TABLE t_app
(
	app_id int NOT NULL AUTO_INCREMENT,
	versionname char(64),
	versioncode int,
	create_time datetime,
	update_time datetime,
	url varchar(256),
	a_id int,
	-- 1 未发布 2 发布
	status int COMMENT '1 未发布 2 发布',
	update_type tinyint,
	size int,
	extra char(255),
	context char(255),
	publish_time datetime,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (app_id),
	UNIQUE (app_id)
);


CREATE TABLE t_app_ch
(
	ac_id int NOT NULL AUTO_INCREMENT,
	-- 1 未发布  2 发布
	status tinyint COMMENT '1 未发布  2 发布',
	app_id int NOT NULL,
	soft_channel_id int NOT NULL,
	PRIMARY KEY (ac_id),
	UNIQUE (ac_id)
);


CREATE TABLE t_app_plu_ch
(
	apc_id int NOT NULL AUTO_INCREMENT,
	app_id int NOT NULL,
	soft_channel_id int NOT NULL,
	plugin_id int NOT NULL,
	-- 1 未发布 2 发布
	status tinyint COMMENT '1 未发布 2 发布',
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (apc_id),
	UNIQUE (apc_id)
);


CREATE TABLE t_batch_info
(
	id int NOT NULL AUTO_INCREMENT,
	vipkey char(16),
	batch_id int NOT NULL,
	-- 1 激活  2 未激活 3 冻结  4 失效
	status tinyint COMMENT '1 激活  2 未激活 3 冻结  4 失效',
	days int,
	update_time datetime,
	user_id bigint NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (id)
);


CREATE TABLE t_channel
(
	chan_id int NOT NULL AUTO_INCREMENT,
	chan_nickname char(20),
	chan_name varchar(64),
	pro_id int NOT NULL,
	a_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	extra char(255),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (chan_id),
	UNIQUE (chan_id)
);


CREATE TABLE t_ch_batch
(
	batch_id int NOT NULL AUTO_INCREMENT,
	num int,
	a_id int NOT NULL,
	com_type_id int NOT NULL,
	chan_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 正常 2 未激活  3 冻结 4 失效 5 结束
	status tinyint COMMENT '1 正常 2 未激活  3 冻结 4 失效 5 结束',
	-- 日卡，周卡，月卡，年卡
	com_type_name char(128) COMMENT '日卡，周卡，月卡，年卡',
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	extra char(255),
	update_a_id int,
	PRIMARY KEY (batch_id),
	UNIQUE (batch_id)
);


CREATE TABLE t_com_type
(
	com_type_id int NOT NULL AUTO_INCREMENT,
	-- 日卡，周卡，月卡，年卡
	name char(128) COMMENT '日卡，周卡，月卡，年卡',
	days int,
	extra char(255),
	a_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (com_type_id),
	UNIQUE (com_type_id)
);


CREATE TABLE t_device
(
	device_id bigint NOT NULL AUTO_INCREMENT,
	utdid varchar(32),
	androidid varchar(32),
	-- android系统的版本号
	buildversion tinyint COMMENT 'android系统的版本号',
	soft_channel_id int,
	chan_name varchar(64),
	create_time datetime,
	update_time datetime,
	versioncode int,
	manufacturer char(128),
	androidmodel char(64),
	PRIMARY KEY (device_id),
	UNIQUE (device_id)
);


CREATE TABLE t_device_imei
(
	device_id bigint NOT NULL,
	imei char(32) NOT NULL,
	PRIMARY KEY (imei)
);


CREATE TABLE t_exception
(
	exceptionid int NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	error text,
	PRIMARY KEY (exceptionid),
	UNIQUE (exceptionid)
);


CREATE TABLE t_feedback
(
	feedback_id int NOT NULL AUTO_INCREMENT,
	user_id bigint,
	device_id bigint,
	user_device_id int,
	context char(255),
	contact char(32),
	create_time datetime,
	url char(255),
	manufacturer char(128),
	androidmodel char(64),
	-- android系统的版本号
	buildversion tinyint COMMENT 'android系统的版本号',
	versioncode int,
	PRIMARY KEY (feedback_id),
	UNIQUE (feedback_id)
);


CREATE TABLE t_functionvideo
(
	function_id int NOT NULL AUTO_INCREMENT,
	fun_name char(32),
	url char(255),
	extra char(255),
	a_id int,
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (function_id),
	UNIQUE (function_id)
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


CREATE TABLE t_new_user_record
(
	nur_id int NOT NULL AUTO_INCREMENT,
	user_id bigint,
	user_device_id int,
	device_id bigint,
	nug_id int NOT NULL,
	create_time datetime,
	PRIMARY KEY (nur_id),
	UNIQUE (nur_id)
);


CREATE TABLE t_notice
(
	notice_id int NOT NULL AUTO_INCREMENT,
	title varchar(128),
	text varchar(256),
	-- 1文本 2 图片 3 文本加图片
	type tinyint COMMENT '1文本 2 图片 3 文本加图片',
	show_time datetime,
	start_time datetime,
	url text,
	-- 1 未发布 2 发布
	status int COMMENT '1 未发布 2 发布',
	end_time datetime,
	PRIMARY KEY (notice_id),
	UNIQUE (notice_id)
);


CREATE TABLE t_order
(
	order_id int NOT NULL AUTO_INCREMENT,
	order_number varchar(32) NOT NULL,
	user_device_id int NOT NULL,
	cmdy_id int NOT NULL,
	user_id int,
	device_id bigint,
	create_time datetime,
	starttime datetime,
	endtime datetime,
	pay_time datetime,
	-- 1 微信 2支付宝
	type int COMMENT '1 微信 2支付宝',
	PRIMARY KEY (order_id, order_number),
	UNIQUE (order_id)
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
	coupon_fee int,
	coupon_count int,
	transaction_id char(32),
	out_trade_no char(32),
	attach char(64),
	time_end char(16),
	PRIMARY KEY (wxpayid),
	UNIQUE (wxpayid)
);
CREATE TABLE t_other_app
(
	o_id int NOT NULL AUTO_INCREMENT,
	o_name char(64),
	icon_url char(128),
	app_url char(128),
	-- 1 直接下载  2应用市场
	download_type tinyint COMMENT '1 直接下载  2应用市场',
	a_id int,
	create_time datetime,
	update_time datetime,
	extra char(128),
	PRIMARY KEY (o_id),
	UNIQUE (o_id)
);


CREATE TABLE t_plugin
(
	plugin_id int NOT NULL AUTO_INCREMENT,
	-- 1 未发布 2 发布
	status tinyint COMMENT '1 未发布 2 发布',
	context char(255),
	extra char(255),
	a_id int,
	create_time datetime,
	update_time datetime,
	publish_time datetime,
	size int,
	md5 char(32),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	url char(255),
	PRIMARY KEY (plugin_id),
	UNIQUE (plugin_id)
);


CREATE TABLE t_promoter
(
	pro_id int NOT NULL AUTO_INCREMENT,
	pro_name varchar(64),
	phone char(12),
	extra varchar(128),
	a_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (pro_id),
	UNIQUE (pro_id)
);


CREATE TABLE t_role
(
	role_id int NOT NULL AUTO_INCREMENT,
	role_name char(64),
	create_time datetime,
	update_time datetime,
	a_id int,
	role_num char(6),
	PRIMARY KEY (role_id),
	UNIQUE (role_id)
);


CREATE TABLE t_share_activity
(
	material_id int NOT NULL AUTO_INCREMENT,
	-- 1 文字 2 图片
	type tinyint COMMENT '1 文字 2 图片',
	content char(255),
	extra char(255),
	create_time datetime,
	update_time datetime,
	a_id int NOT NULL,
	PRIMARY KEY (material_id),
	UNIQUE (material_id)
);


CREATE TABLE t_soft_channel
(
	soft_channel_id int NOT NULL AUTO_INCREMENT,
	name char(20),
	extra char(255),
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (soft_channel_id),
	UNIQUE (soft_channel_id)
);


CREATE TABLE t_user
(
	user_id bigint NOT NULL AUTO_INCREMENT,
	username varchar(32),
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
	-- 当活动存在多个执行状态时，默认为1     10 通过   20 驳回
	status tinyint COMMENT '当活动存在多个执行状态时，默认为1     10 通过   20 驳回',
	create_time datetime,
	update_time datetime,
	user_device_id int,
	device_id bigint,
	a_id int,
	PRIMARY KEY (u_a_id),
	UNIQUE (u_a_id)
);


CREATE TABLE t_user_device
(
	user_device_id int NOT NULL AUTO_INCREMENT,
	user_id bigint NOT NULL,
	device_id bigint NOT NULL,
	status int,
	create_time datetime,
	PRIMARY KEY (user_device_id),
	UNIQUE (user_device_id)
);


CREATE TABLE t_user_gifts
(
	nug_id int NOT NULL AUTO_INCREMENT,
	com_type_id int NOT NULL,
	com_type_name char(128),
	days int,
	-- 1 未开启 2 开启  3 删除
	status tinyint COMMENT '1 未开启 2 开启  3 删除',
	a_id int,
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (nug_id),
	UNIQUE (nug_id)
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
	u_notice_id bigint NOT NULL AUTO_INCREMENT,
	-- 允许为null
	user_device_id int COMMENT '允许为null',
	notice_id int,
	user_id bigint,
	device_id bigint,
	show_time time,
	PRIMARY KEY (u_notice_id),
	UNIQUE (u_notice_id)
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
	vcreate_time datetime,
	vend_time datetime,
	first_time datetime,
	last_time datetime,
	UNIQUE (user_id)
);


CREATE TABLE t_vipcommodity
(
	cmdy_id int NOT NULL AUTO_INCREMENT,
	viptype_id int NOT NULL,
	com_type_id int NOT NULL,
	com_name varchar(64),
	price int,
	discount float,
	-- 1 会员中心 
	position tinyint COMMENT '1 会员中心 ',
	create_time datetime,
	soft_channel_id int NOT NULL,
	name char(20),
	update_time datetime,
	days int,
	a_id int NOT NULL,
	description char(128),
	show_discount char(32),
	-- 1 不上架 2 上架
	status tinyint COMMENT '1 不上架 2 上架',
	com_type_name char(128),
	-- 1 未置顶  2 置顶
	istop tinyint COMMENT '1 未置顶  2 置顶',
	PRIMARY KEY (cmdy_id),
	UNIQUE (cmdy_id)
);


CREATE TABLE t_viptype
(
	viptype_id int NOT NULL,
	-- 不同vip和年费vip
	vipname char(32) COMMENT '不同vip和年费vip',
	PRIMARY KEY (viptype_id),
	UNIQUE (viptype_id)
);


CREATE TABLE t_whilte_device
(
	-- 允许为null
	device_id bigint NOT NULL COMMENT '允许为null',
	extra char(255),
	status tinyint,
	a_id int,
	create_time datetime,
	update_time datetime,
	UNIQUE (device_id)
);


CREATE TABLE t_whilte_user
(
	-- 允许为null
	user_id bigint NOT NULL COMMENT '允许为null',
	username varchar(32),
	phone char(11),
	UNIQUE (user_id)
);


CREATE TABLE t_wxsupport
(
	w_id int NOT NULL AUTO_INCREMENT,
	package_name char(64),
	create_time datetime,
	update_time datetime,
	a_id int,
	extra char(255),
	PRIMARY KEY (w_id),
	UNIQUE (w_id)
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


ALTER TABLE t_com_type
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


ALTER TABLE t_ad_channel
	ADD FOREIGN KEY (app_id)
	REFERENCES t_app (app_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_app_ch
	ADD FOREIGN KEY (app_id)
	REFERENCES t_app (app_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_app_plu_ch
	ADD FOREIGN KEY (app_id)
	REFERENCES t_app (app_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_ch_batch
    ADD FOREIGN KEY (chan_id)
        REFERENCES t_channel (chan_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE t_batch_info
    ADD FOREIGN KEY (batch_id)
        REFERENCES t_ch_batch (batch_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE t_activity
	ADD FOREIGN KEY (com_type_id)
	REFERENCES t_com_type (com_type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_gifts
	ADD FOREIGN KEY (com_type_id)
	REFERENCES t_com_type (com_type_id)
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


ALTER TABLE t_user_device
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_whilte_device
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


ALTER TABLE t_app_plu_ch
	ADD FOREIGN KEY (plugin_id)
	REFERENCES t_plugin (plugin_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_channel
    ADD FOREIGN KEY (pro_id)
        REFERENCES t_promoter (pro_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE t_admin_user
	ADD FOREIGN KEY (role_id)
	REFERENCES t_role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_ad_channel
	ADD FOREIGN KEY (soft_channel_id)
	REFERENCES t_soft_channel (soft_channel_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_app_ch
	ADD FOREIGN KEY (soft_channel_id)
	REFERENCES t_soft_channel (soft_channel_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_app_plu_ch
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


ALTER TABLE t_batch_info
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
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


ALTER TABLE t_new_user_record
	ADD FOREIGN KEY (nug_id)
	REFERENCES t_user_gifts (nug_id)
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



