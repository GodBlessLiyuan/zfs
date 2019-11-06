insert into t_viptype(viptype_id,vipname) values(1,'非会员');
insert into t_viptype(viptype_id,vipname) values(10,'会员');
insert into t_viptype(viptype_id,vipname) values(20,'年费会员');
insert into t_soft_channel(soft_channel_id,name,extra) values(1,"所有","所有");

INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (1, '管理员', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (2, '项目经理', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (3, '产品', null, null, null, null);
INSERT INTO smarthelper.t_role (role_id, role_name, create_time, update_time, a_id, role_num) VALUES (4, '运营', null, null, null, null);



insert into t_admin_user(username, password,role_id) values('xinyue','123456',1);

INSERT INTO smarthelper.t_com_type (com_type_id, name, days, extra, a_id, create_time, update_time) VALUES (1, '年卡', 380, null, 1, '2019-11-06 12:30:02', '2019-11-06 12:30:04');
INSERT INTO smarthelper.t_activity (activity_id, activityname, position, status, a_id, create_time, update_time, dr, com_type_id, days, com_type_name, source) VALUES (1, null, null, 1, 1, null, null, null, 1, 380, '年卡', 1);


INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (1, 'vbooster', '扩展', null, null);
INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (2, 'huawei', 'huawei', null, null);
INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (3, '应用宝', 'yingyongbao', '2019-10-24 10:15:57', null);
INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (4, 'vivo', 'vivo', '2019-10-24 10:16:33', null);
INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (6, 'OPPO', 'OPPO', '2019-10-24 11:27:47', null);
INSERT INTO smarthelper.t_soft_channel (soft_channel_id, name, extra, create_time, update_time) VALUES (7, 'xxx', 'xxxxxxxx', '2019-10-31 16:06:17', null);

INSERT INTO smarthelper.t_wxsupport (w_id, package_name, create_time, update_time, a_id, extra) VALUES (1, 'com.tencent.mm', '2019-10-29 18:25:55', null, 1, '微信');
INSERT INTO smarthelper.t_wxsupport (w_id, package_name, create_time, update_time, a_id, extra) VALUES (2, 'com.qihoo.magic', '2019-10-29 18:24:45', null, 1, '360分身大师');
INSERT INTO smarthelper.t_wxsupport (w_id, package_name, create_time, update_time, a_id, extra) VALUES (3, 'com.excelliance.dualaid', '2019-10-29 18:25:12', null, 1, '双开助手');

INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (1, '4');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (2, 'http://www.google.com');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (10, '1');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (11, 'http://www.baidu.com');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (12, '4285794651');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (13, 'wx11243454634');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (14, 'http://share.com.cn');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (15, 'http://problem.com.cn');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (16, 'http://free.vip.com.cn');
INSERT INTO smarthelper.t_key_value (key_name, value) VALUES (17, 'http://protocol.com.cn');
