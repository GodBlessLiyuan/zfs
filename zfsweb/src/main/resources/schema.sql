use smartzfs;

/* Drop Tables */

DROP TABLE IF EXISTS t_user_activity;
DROP TABLE IF EXISTS t_activity;
DROP TABLE IF EXISTS t_ad_channel;
DROP TABLE IF EXISTS t_adconfig;
DROP TABLE IF EXISTS t_admin_log;
DROP TABLE IF EXISTS t_app_ava_ch;
DROP TABLE IF EXISTS t_avatar;
DROP TABLE IF EXISTS t_black_app;
DROP TABLE IF EXISTS t_new_user_record;
DROP TABLE IF EXISTS t_user_gifts;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_vipcommodity;
DROP TABLE IF EXISTS t_com_type;
DROP TABLE IF EXISTS t_admin_user;
DROP TABLE IF EXISTS t_ali_feedback;
DROP TABLE IF EXISTS t_app_ch;
DROP TABLE IF EXISTS t_app_plu_ch;
DROP TABLE IF EXISTS t_app;
DROP TABLE IF EXISTS t_bannerconfig;
DROP TABLE IF EXISTS t_batch_info;
DROP TABLE IF EXISTS t_ch_batch;
DROP TABLE IF EXISTS t_channel;
DROP TABLE IF EXISTS t_device_imei;
DROP TABLE IF EXISTS t_device_statistics;
DROP TABLE IF EXISTS t_exception;
DROP TABLE IF EXISTS t_user_notice;
DROP TABLE IF EXISTS t_user_device;
DROP TABLE IF EXISTS t_voice_share;
DROP TABLE IF EXISTS t_white_device;
DROP TABLE IF EXISTS t_device;
DROP TABLE IF EXISTS t_feedback;
DROP TABLE IF EXISTS t_functionvideo;
DROP TABLE IF EXISTS t_godinsec_user;
DROP TABLE IF EXISTS t_invite_detail;
DROP TABLE IF EXISTS t_invite_user;
DROP TABLE IF EXISTS t_key_text;
DROP TABLE IF EXISTS t_key_value;
DROP TABLE IF EXISTS t_notice;
DROP TABLE IF EXISTS t_other_app;
DROP TABLE IF EXISTS t_phone_model;
DROP TABLE IF EXISTS t_phone_type;
DROP TABLE IF EXISTS t_plugin;
DROP TABLE IF EXISTS t_promoter;
DROP TABLE IF EXISTS t_revenue_user;
DROP TABLE IF EXISTS t_role;
DROP TABLE IF EXISTS t_share_activity;
DROP TABLE IF EXISTS t_soft_channel;
DROP TABLE IF EXISTS t_user_vip;
DROP TABLE IF EXISTS t_white_user;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_user_history;
DROP TABLE IF EXISTS t_viptype;
DROP TABLE IF EXISTS t_withdraw_user;
DROP TABLE IF EXISTS t_wxsupport;
DROP TABLE IF EXISTS t_wx_feedback;




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
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
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
	username char(32),
	password char(32),
	email char(128),
	is_lock tinyint,
	create_time datetime,
	last_time datetime,
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
	role_id int NOT NULL,
	relation_a_id int,
	name char(64),
	extra char(255),
	phone char(20),
	PRIMARY KEY (a_id),
	UNIQUE (username)
);


CREATE TABLE t_ad_channel
(
	ad_id int NOT NULL,
	soft_channel_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 开始 2 关闭
	type tinyint DEFAULT 1 COMMENT '1 开始 2 关闭',
	app_id int NOT NULL,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除'
);


CREATE TABLE t_ali_feedback
(
	notify_time datetime,
	notify_type char(64),
	notify_id char(128),
	app_id char(32),
	version char(3),
	sign_type char(10),
	trade_no char(64),
	out_trade_no char(64),
	out_biz_no char(64),
	buyer_id char(16),
	buyer_logon_id char(100),
	seller_id char(30),
	seller_email char(100),
	trade_status char(32),
	total_amount float(9,2),
	receipt_amount float(9,2),
	invoice_amount float(9,2),
	buyer_pay_amount float(9,2),
	point_amount float(9,2),
	refund_fee float(9,2),
	subject char(255),
	body char(255),
	gmt_create datetime,
	gmt_payment datetime,
	gmt_refund datetime,
	gmt_close datetime,
	fund_bill_list char(255)
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
	-- 1 普通更新 2 强制更新
	update_type tinyint DEFAULT 1 COMMENT '1 普通更新 2 强制更新',
	size int,
	extra char(255),
	context char(255),
	publish_time datetime,
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
	md5 char(32),
	PRIMARY KEY (app_id),
	UNIQUE (app_id)
);


CREATE TABLE t_app_ava_ch
(
	aac_id bigint NOT NULL AUTO_INCREMENT,
	app_id int NOT NULL,
	soft_channel_id int NOT NULL,
	avatar_id bigint NOT NULL,
	-- 1 未发布 2 发布
	status tinyint DEFAULT 1 COMMENT '1 未发布 2 发布',
	create_time datetime,
	update_time datetime,
	PRIMARY KEY (aac_id),
	UNIQUE (aac_id)
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


CREATE TABLE t_avatar
(
	avatar_id bigint NOT NULL AUTO_INCREMENT,
	version_name char(64),
	version_code int,
	create_time datetime,
	update_time datetime,
	url char(255),
	-- 1 未发布  2 发布
	status tinyint DEFAULT 1 COMMENT '1 未发布  2 发布',
	-- 1 不同更新 2 强制更新
	update_type tinyint DEFAULT 1 COMMENT '1 不同更新 2 强制更新',
	size int,
	extra char(255),
	context char(255),
	publish_time datetime,
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	md5 char(32),
	a_id int NOT NULL,
	PRIMARY KEY (avatar_id),
	UNIQUE (avatar_id)
);


CREATE TABLE t_bannerconfig
(
	banner_id int NOT NULL AUTO_INCREMENT,
	a_id int NOT NULL,
	name char(32),
	create_time datetime,
	update_time datetime,
	-- 1关闭 2开启 3 删除
	status tinyint DEFAULT 1 COMMENT '1关闭 2开启 3 删除',
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
	start_time datetime,
	url char(255),
	pic_path char(255),
	PRIMARY KEY (banner_id),
	UNIQUE (banner_id)
);


CREATE TABLE t_batch_info
(
	id int NOT NULL AUTO_INCREMENT,
	vipkey char(16),
	batch_id int NOT NULL,
	-- 1 未激活 2 激活  3 冻结  4 失效
	status tinyint DEFAULT 1 COMMENT '1 未激活 2 激活  3 冻结  4 失效',
	days int,
	update_time datetime,
	user_id bigint DEFAULT 0,
	PRIMARY KEY (id),
	UNIQUE (id)
);


CREATE TABLE t_black_app
(
	black_id bigint unsigned zerofill NOT NULL AUTO_INCREMENT,
	package_name char(32),
	app_name char(32),
	create_time datetime,
	extra char(255),
	a_id int NOT NULL,
	PRIMARY KEY (black_id),
	UNIQUE (black_id)
);


CREATE TABLE t_channel
(
	chan_id int NOT NULL AUTO_INCREMENT,
	chan_nickname char(20),
	chan_name char(64),
	pro_id int NOT NULL,
	a_id int NOT NULL,
	create_time datetime,
	update_time datetime,
	extra char(255),
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
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
	days int,
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
	utdid char(32),
	androidid char(32),
	-- android系统的版本号
	buildversion tinyint COMMENT 'android系统的版本号',
	soft_channel_id int,
	chan_name char(64),
	create_time datetime,
	update_time datetime,
	versioncode int,
	manufacturer char(128),
	androidmodel char(64),
	uuid char(32),
	buildrelease char(16),
	versionname char(16),
	PRIMARY KEY (device_id),
	UNIQUE (device_id),
	UNIQUE (uuid)
);


CREATE TABLE t_device_imei
(
	device_id bigint NOT NULL,
	imei char(32) NOT NULL,
	PRIMARY KEY (imei)
);


CREATE TABLE t_device_statistics
(
	sid int NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	visit_time datetime,
	ip char(128),
	PRIMARY KEY (sid),
	UNIQUE (sid)
);


CREATE TABLE t_exception
(
	exceptionid int NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	error text,
	-- android系统的版本号
	buildversion tinyint COMMENT 'android系统的版本号',
	versioncode int,
	androidmodel char(64),
	pkg char(32),
	buildrelease char(16),
	create_time datetime,
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
	manufacturer char(128),
	androidmodel char(64),
	-- android系统的版本号
	buildversion tinyint COMMENT 'android系统的版本号',
	versioncode int,
	buildrelease char(16),
	url1 char(255),
	url2 char(255),
	url3 char(255),
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
	UNIQUE (function_id),
	UNIQUE (fun_name)
);


CREATE TABLE t_godinsec_user
(
	phone char(11) NOT NULL,
	create_time datetime,
	update_time datetime,
	-- 1 未赠送  2 赠送
	status tinyint DEFAULT 1 COMMENT '1 未赠送  2 赠送',
	-- 产品名称
	name char(64) NOT NULL COMMENT '产品名称',
	-- vip到期时间
	end_time datetime COMMENT 'vip到期时间',
	-- 赠送天数
	days int DEFAULT 0 COMMENT '赠送天数',
	PRIMARY KEY (phone),
	UNIQUE (phone)
);


CREATE TABLE t_invite_detail
(
	inde_id bigint NOT NULL AUTO_INCREMENT,
	order_id int,
	com_type_id int,
	com_type_name char(128),
	pay bigint,
	earnings bigint,
	proportion tinyint,
	user_id bigint NOT NULL,
	invitee_id bigint NOT NULL,
	viptype_id int DEFAULT 0,
	pay_time datetime,
	invite_id int NOT NULL,
	PRIMARY KEY (inde_id),
	UNIQUE (inde_id)
);


CREATE TABLE t_invite_user
(
	invite_id int NOT NULL AUTO_INCREMENT,
	user_id bigint,
	invite_phone char(16),
	create_time datetime,
	update_time datetime,
	ip char(128),
	PRIMARY KEY (invite_id),
	UNIQUE (invite_id),
	UNIQUE (invite_phone)
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
	value char(255),
	PRIMARY KEY (key_name),
	UNIQUE (key_name)
);


CREATE TABLE t_new_user_record
(
	nur_id int NOT NULL AUTO_INCREMENT,
	user_id bigint,
	user_device_id int,
	device_id bigint,
	nug_id int,
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
	picurl char(255),
	create_time datetime,
	update_time datetime,
	a_id int,
	PRIMARY KEY (notice_id),
	UNIQUE (notice_id)
);


CREATE TABLE t_order
(
	order_id int NOT NULL AUTO_INCREMENT,
	order_number char(32) NOT NULL,
	user_device_id int NOT NULL,
	cmdy_id int NOT NULL,
	user_id bigint,
	device_id bigint,
	create_time datetime,
	starttime datetime,
	endtime datetime,
	pay_time datetime,
	-- 1 微信 2支付宝
	type int COMMENT '1 微信 2支付宝',
	days int,
	pay bigint,
	-- 1 未支付  2 支付成功
	status tinyint DEFAULT 1 COMMENT '1 未支付  2 支付成功',
	-- 1 未分成  2 已经计算过分成
	revenue tinyint DEFAULT 1 COMMENT '1 未分成  2 已经计算过分成',
	PRIMARY KEY (order_id, order_number),
	UNIQUE (order_id),
	UNIQUE (order_number)
);


CREATE TABLE t_other_app
(
	o_id int NOT NULL AUTO_INCREMENT,
	o_name char(64),
	icon_url char(255),
	app_url char(255),
	-- 1 直接下载  2应用市场
	download_type tinyint COMMENT '1 直接下载  2应用市场',
	a_id int,
	create_time datetime,
	update_time datetime,
	extra char(255),
	PRIMARY KEY (o_id),
	UNIQUE (o_id)
);


CREATE TABLE t_phone_model
(
	model_id bigint NOT NULL AUTO_INCREMENT,
	type_id int NOT NULL,
	name char(128),
	picture char(255),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (model_id),
	UNIQUE (model_id)
);


CREATE TABLE t_phone_type
(
	type_id int NOT NULL AUTO_INCREMENT,
	ename char(32),
	name char(32),
	picture char(255),
	-- 1 未删除  2删除
	dr tinyint COMMENT '1 未删除  2删除',
	PRIMARY KEY (type_id),
	UNIQUE (type_id),
	UNIQUE (ename),
	UNIQUE (name)
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
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
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
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
	PRIMARY KEY (pro_id),
	UNIQUE (pro_id)
);


CREATE TABLE t_revenue_user
(
	user_id bigint NOT NULL,
	invite_count int,
	pay_count int,
	register_count bigint,
	total_revenue bigint,
	withdraw bigint,
	withdraw_time int,
	remaining bigint,
	sharecode char(32),
	end_time datetime,
	shorturl char(125),
	PRIMARY KEY (user_id),
	UNIQUE (user_id),
	UNIQUE (sharecode)
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
	UNIQUE (soft_channel_id),
	UNIQUE (name)
);


CREATE TABLE t_user
(
	user_id bigint NOT NULL AUTO_INCREMENT,
	username char(32),
	phone char(11),
	ip char(64),
	create_time datetime,
	update_time datetime,
	chan_name char(64),
	soft_channel_id int,
	PRIMARY KEY (user_id),
	UNIQUE (user_id)
);


CREATE TABLE t_user_activity
(
	u_a_id int NOT NULL AUTO_INCREMENT,
	activity_id int NOT NULL,
	user_id bigint NOT NULL,
	time time,
	-- 当活动存在多个执行状态时，默认为1     10 通过  30 领取  20 驳回
	status tinyint COMMENT '当活动存在多个执行状态时，默认为1     10 通过  30 领取  20 驳回',
	create_time datetime,
	update_time datetime,
	user_device_id int,
	device_id bigint,
	a_id int DEFAULT 0,
	url char(255),
	PRIMARY KEY (u_a_id),
	UNIQUE (u_a_id)
);


CREATE TABLE t_user_device
(
	user_device_id int NOT NULL AUTO_INCREMENT,
	user_id bigint NOT NULL,
	device_id bigint NOT NULL,
	-- 1 登录  2 登出
	status tinyint DEFAULT 1 COMMENT '1 登录  2 登出',
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
	-- 1 未删除  2删除
	dr tinyint DEFAULT 1 COMMENT '1 未删除  2删除',
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
	com_name char(64),
	price char(16),
	discount bigint,
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


CREATE TABLE t_voice_share
(
	voice_id bigint NOT NULL AUTO_INCREMENT,
	device_id bigint NOT NULL,
	user_id bigint NOT NULL,
	user_device_id int,
	total int,
	create_time datetime,
	url char(255),
	-- 1 初始阶段，未上传 2 上传文件中  3 完成  4 失败
	status tinyint DEFAULT 1 COMMENT '1 初始阶段，未上传 2 上传文件中  3 完成  4 失败',
	path char(128),
	extra char(255),
	title char(128),
	PRIMARY KEY (voice_id),
	UNIQUE (voice_id)
);


CREATE TABLE t_white_device
(
	device_id bigint NOT NULL,
	extra char(255),
	status tinyint,
	a_id int,
	create_time datetime,
	update_time datetime,
	UNIQUE (device_id)
);


CREATE TABLE t_white_user
(
	user_id bigint NOT NULL,
	username char(32),
	phone char(11),
	UNIQUE (user_id)
);


CREATE TABLE t_withdraw_user
(
	withdraw_id int NOT NULL AUTO_INCREMENT,
	create_time datetime,
	user_id bigint,
	device_id bigint,
	user_device_id int,
	withdraw bigint DEFAULT 1,
	remaining bigint,
	ali_account char(64),
	ali_name char(64),
	withdraw_time int,
	audit_time datetime,
	end_time datetime,
	-- 1 待审核  2 运营驳回 3 打款中  4 支付宝驳回  5 完成
	status tinyint COMMENT '1 待审核  2 运营驳回 3 打款中  4 支付宝驳回  5 完成',
	a_id int,
	PRIMARY KEY (withdraw_id),
	UNIQUE (withdraw_id)
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


CREATE TABLE t_wx_feedback
(
	wxpayid bigint NOT NULL AUTO_INCREMENT,
	return_code char(16),
	return_msg char(128),
	appid char(32),
	mch_id char(32),
	device_info char(32),
	nonce_str char(32),
	sign char(32),
	result_code char(16),
	err_code char(32),
	err_code_des char(128),
	openid char(128),
	is_subscribe char,
	trade_type char(16),
	bank_type char(16),
	total_fee int,
	fee_type char(8),
	cash_fee int,
	cash_fee_type char(16),
	coupon_fee int,
	coupon_count int,
	transaction_id char(32),
	out_trade_no char(32),
	attach char(128),
	time_end char(14),
	PRIMARY KEY (wxpayid),
	UNIQUE (wxpayid)
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


ALTER TABLE t_avatar
	ADD FOREIGN KEY (a_id)
	REFERENCES t_admin_user (a_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_black_app
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


ALTER TABLE t_app_ava_ch
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


ALTER TABLE t_app_ava_ch
	ADD FOREIGN KEY (avatar_id)
	REFERENCES t_avatar (avatar_id)
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


ALTER TABLE t_device_statistics
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


ALTER TABLE t_voice_share
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_white_device
	ADD FOREIGN KEY (device_id)
	REFERENCES t_device (device_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_invite_detail
	ADD FOREIGN KEY (invite_id)
	REFERENCES t_invite_user (invite_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_user_notice
	ADD FOREIGN KEY (notice_id)
	REFERENCES t_notice (notice_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_phone_model
	ADD FOREIGN KEY (type_id)
	REFERENCES t_phone_type (type_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_ava_plu
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


ALTER TABLE t_app_ava_ch
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


ALTER TABLE t_voice_share
	ADD FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE t_white_user
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



/* Create Indexes */

CREATE INDEX ix_invite_detail_userid USING BTREE ON t_invite_detail (user_id DESC);



