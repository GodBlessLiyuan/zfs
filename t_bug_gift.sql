SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS t_buy_gift;




/* Create Tables */

CREATE TABLE t_buy_gift
(
	bg_id bigint NOT NULL AUTO_INCREMENT,
	user_id bigint,
	order_number char(64),
	-- 1：微信；2：支付宝
	type tinyint COMMENT '1：微信；2：支付宝',
	cmdy_name char(20),
	com_type_name char(128),
	com_name char(64),
	create_time datetime,
	starttime datetime,
	endtime datetime,
	days int,
	PRIMARY KEY (bg_id)
);



