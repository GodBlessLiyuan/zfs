insert into t_viptype(viptype_id,vipname) values(1,'非会员');
insert into t_viptype(viptype_id,vipname) values(10,'会员');
insert into t_viptype(viptype_id,vipname) values(20,'年费会员');
insert into t_soft_channel(soft_channel_id,name,extra) values(1,"所有","所有");

INSERT INTO smartzfs.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (1, '管理员', null, null, null, null);
INSERT INTO smartzfs.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (2, '项目经理', null, null, null, null);
INSERT INTO smartzfs.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (3, '产品', null, null, null, null);
INSERT INTO smartzfs.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (4, '运营', null, null, null, null);



insert into t_admin_user(username, password,role_id) values('admin','b34232933f4816d8415b8d66379e28a4',1);

INSERT INTO smartzfs.t_com_type (com_type_id, name, days, extra, a_id, create_time, update_time) VALUES (1, '周卡', 7, null, 1, '2019-11-06 12:30:02', '2019-11-06 12:30:04');

INSERT INTO smartzfs.t_activity (activity_id, activityname, position, status, a_id, create_time, update_time, dr, com_type_id, days, com_type_name, source) VALUES (1, null, null, 1, 1, null, null, null, 1, 7, '周卡', 1);

INSERT INTO smartzfs.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (2, 'vbooster', '官网', null, null);

INSERT INTO smartzfs.t_wxsupport (w_id, package_name, create_time, update_time, a_id, extra) VALUES (1, 'com.tencent.mm', '2019-10-29 18:25:55', null, 1, '微信');

INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (1, '2');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (2, 'https://mp.weixin.qq.com/s/an1ZP4Zop1ifr2Xbh4pD7Q');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (10, '1');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (11, '0nUArI5-H92DDqDeiU6uPSv0pWs9m2QF');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (12, '砖助智能助手客服群1');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (13, 'zzznzs12345');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (14, 'http://share.com.cn');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (15, 'http://vbooster.cn/smartproblem/index.html');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (16, 'http://vbooster.cn/share/v1.0/freemember');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (17, 'http://vbooster.cn/share/v1.0/memberprotocol');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (18, 'http://vbooster.cn/share/v1.0/protocol');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (19, 'http://vbooster.cn/share/v1.0/memberrights');
INSERT INTO smartzfs.t_key_value (key_name, value) VALUES (20, 'http://www.baidu.com');