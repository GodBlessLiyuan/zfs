insert into t_viptype(viptype_id,vipname) values(1,'非会员');
insert into t_viptype(viptype_id,vipname) values(10,'会员');
insert into t_viptype(viptype_id,vipname) values(20,'年费会员');
insert into t_soft_channel(soft_channel_id,name,extra) values(1,"所有","所有");

INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (1, '管理员', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (2, '项目经理', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (3, '产品', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (4, '运营', null, null, null, null);



insert into t_admin_user(username, password,role_id) values('admin','b34232933f4816d8415b8d66379e28a4',1);

INSERT INTO smarthelper.t_com_type (com_type_id, name, days, extra, a_id, create_time, update_time) VALUES (1, '周卡', 7, null, 1, '2019-11-06 12:30:02', '2019-11-06 12:30:04');

INSERT INTO smarthelper.t_activity (activity_id, activityname, position, status, a_id, create_time, update_time, dr, com_type_id, days, com_type_name, source) VALUES (1, null, null, 1, 1, null, null, null, 1, 7, '周卡', 1);

INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (2, 'vbooster', '官网', null, null);

INSERT INTO smarthelper.t_wxsupport (w_id, package_name, create_time, update_time, a_id, extra) VALUES (1, 'com.tencent.mm', '2019-10-29 18:25:55', null, 1, '微信');

INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (1, '4');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (2, 'https://mp.weixin.qq.com/s/an1ZP4Zop1ifr2Xbh4pD7Q');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (10, '12');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (11, '0nUArI5-H92DDqDeiU6uPSv0pWs9m2QF');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (12, '砖助智能助手客服群1');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (13, 'wx11243454634');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (14, 'http://share.com.cn');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (15, 'http://39.97.253.38/smartproblem/index.html');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (16, 'http://39.97.253.38/share/v1.0/freemember');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (17, 'http://39.97.253.38/share/v1.0/memberprotocol');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (18, 'http://39.97.253.38/share/v1.0/protocol');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (19, 'http://39.97.253.38/share/v1.0/memberrights');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (20, 'http://39.97.253.38/share/v1.0/clearfans');