SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS m_register_user;
DROP TABLE IF EXISTS t_service_number;




/* Create Tables */

CREATE TABLE m_register_user
(
	re_uid bigint NOT NULL AUTO_INCREMENT,
	phone char(11),
	create_time datetime,
	chan_name char(64),
	versionname char(16),
	buildrelease char(16),
	manufacturer char(128),
	androidmodel char(64),
	PRIMARY KEY (re_uid)
);


CREATE TABLE t_service_number
(
	svnm_id bigint NOT NULL AUTO_INCREMENT,
	kf char(32),
	create_time datetime,
	update_time datetime,
	a_id bigint unsigned,
	username char(32),
	-- 1：不删除，2：删除
	dr tinyint COMMENT '1：不删除，2：删除',
	PRIMARY KEY (svnm_id)
);



