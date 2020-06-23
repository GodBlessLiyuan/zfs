SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS m_register_user;




/* Create Tables */

CREATE TABLE m_register_user
(
	re_uid bigint NOT NULL,
	phone char(11),
	create_time datetime,
	chan_name char(64),
	versionname char(16),
	buildrelease char(16),
	manufacturer char(128),
	androidmodel char(64),
	PRIMARY KEY (re_uid)
);



